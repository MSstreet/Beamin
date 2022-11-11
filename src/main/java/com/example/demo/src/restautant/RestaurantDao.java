package com.example.demo.src.restautant;

import com.example.demo.src.restautant.model.GetRestaurantRes;
import com.example.demo.src.restautant.model.PatchRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantReq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createRestaurant(PostRestaurantReq postRestaurantReq){

        String createUserQuery = "insert into restaurant (name,number,address,operation_time,introduction_board,tip_delivery,time_delivery,company_registration_number" +
                ",categories,type,restaurant_image,min_delivery_price,closed_day,possible_delivery,status,facilities,favorite_num,payment_method) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        System.out.println(createUserQuery);


        Object[] createRestaurantParams = new Object[]{postRestaurantReq.getName(), postRestaurantReq.getNumber(),postRestaurantReq.getAddress(),postRestaurantReq.getOperationTime()
                ,postRestaurantReq.getIntroductionBoard(),postRestaurantReq.getTipDelivery(),postRestaurantReq.getTimeDelivery(),postRestaurantReq.getCompanyRegistrationNumber(),postRestaurantReq.getCategories()
                ,postRestaurantReq.getType(),postRestaurantReq.getRestaurantImage(),postRestaurantReq.getMinDeliveryPrice(),postRestaurantReq.getClosedDay()
                ,postRestaurantReq.getPossibleDelivery(),postRestaurantReq.getStatus(),postRestaurantReq.getFacilities(),postRestaurantReq.getFavoriteNum()
                ,postRestaurantReq.getPaymentMethod()};

        for(int i = 0; i < createRestaurantParams.length; i++){
            System.out.println(createRestaurantParams[i]);
        }
        this.jdbcTemplate.update(createUserQuery, createRestaurantParams);

        String lastInsertIdQuery = "select last_insert_id()";

        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public List<GetRestaurantRes> getRestaurants(){

        String getRestaurantQuery = "select * from restaurant";


        return this.jdbcTemplate.query(getRestaurantQuery,
                (rs,rowNum) -> new GetRestaurantRes(
                        rs.getInt("restaurant_Id"),
                        rs.getString("name"),
                        rs.getString("number"),
                        rs.getString("address"),
                        rs.getString("operation_time"),
                        rs.getString("introduction_board"),
                        rs.getString("tip_delivery"),
                        rs.getString("time_delivery"),
                        rs.getString("company_registration_number"),
                        rs.getString("categories"),
                        rs.getInt("type"),
                        rs.getString("restaurant_image"),
                        rs.getString("min_delivery_price"),
                        rs.getString("closed_day"),
                        rs.getString("possible_delivery"),
                        rs.getString("status"),
                        rs.getString("facilities"),
                        rs.getInt("favorite_num"),
                        rs.getString("payment_method")));
        }

    public GetRestaurantRes getRestaurant(int restaurantId){
        String getRestaurantQuery = "select * from restaurant where ID = ?";

        int getRestaurantParams = restaurantId;

        return this.jdbcTemplate.queryForObject(getRestaurantQuery,
                (rs, rowNum) -> new GetRestaurantRes(
                        rs.getInt("restaurant_Id"),
                        rs.getString("name"),
                        rs.getString("number"),
                        rs.getString("address"),
                        rs.getString("operation_time"),
                        rs.getString("introduction_board"),
                        rs.getString("tip_delivery"),
                        rs.getString("time_delivery"),
                        rs.getString("company_registration_number"),
                        rs.getString("categories"),
                        rs.getInt("type"),
                        rs.getString("restaurant_image"),
                        rs.getString("min_delivery_price"),
                        rs.getString("closed_day"),
                        rs.getString("possible_delivery"),
                        rs.getString("status"),
                        rs.getString("facilities"),
                        rs.getInt("favorite_num"),
                        rs.getString("payment_method")),
                getRestaurantParams);
    }

    public int modifyRestaurant(PatchRestaurantReq patchRestaurantReq){
        String modifyUserNameQuery = "update restaurant set name = ? where Id = ? ";

        Object[] modifyUserNameParams = new Object[]{patchRestaurantReq.getRestaurantId(), patchRestaurantReq.getRestaurantName()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }


}


