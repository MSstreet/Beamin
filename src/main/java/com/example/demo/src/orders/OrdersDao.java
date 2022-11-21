package com.example.demo.src.orders;


import com.example.demo.src.orders.model.OrderPageItemDTO;
import com.example.demo.src.orders.model.Orders;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class OrdersDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public OrderPageItemDTO getMenusInfo(int menuId){

        String getMenuInfoQuery = "select name, price from menu where menu_Id = ?";

        int getMenuInfoParam = menuId;

        return this.jdbcTemplate.queryForObject(getMenuInfoQuery,
                (rs, rowNum) -> new OrderPageItemDTO(
                        rs.getString("name"),
                        rs.getInt("price")
                ),getMenuInfoParam);
    }


    public int createOrders(Orders orders){

        String createUserQuery = "insert into orders (user_Id,restaurant_Id,method_of_payment,status,prices,to_owner,to_deleveryman,disposable, cash_receipts, pickup_method) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Object[] createUserParams = new Object[]{orders.getUserId(), orders.getRestaurantId(),orders.getMethodOfPayment()
                ,orders.getStatus(),orders.getPrices(),orders.getToOwner(),orders.getToDeliveryman(), orders.getDisposable(),orders.getCashReceipts(),orders.getPickupMethod()};

        for(int i = 0; i < createUserParams.length; i++){
            System.out.println(createUserParams[i]);
        }

        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";

//        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

}
