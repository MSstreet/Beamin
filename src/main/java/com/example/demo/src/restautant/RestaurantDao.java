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

    public List<GetRestaurantRes> getRestaurantsBySearch(String search){

        String getRestaurantQuery = "select * from restaurant where name like ?";

        System.out.println(getRestaurantQuery);
        System.out.println(search);
        String Param = "%"+search+"%";
        System.out.println(Param);

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
                        rs.getString("payment_method")),Param);
    }


    public GetRestaurantRes getRestaurant(int restaurantId){
        String getRestaurantQuery = "select * from restaurant where restaurant_Id = ?";

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

    public int modifyRestaurant(PatchRestaurantReq patchRestaurantReq, int restaurantId){
        String modifyUserNameQuery = "update restaurant set name = ? ,number = ?, address = ?, operation_time = ?, introduction_board = ?, tip_delivery = ?, time_delivery = ?,company_registration_number = ?," +
                " categories = ?, type = ?,  restaurant_image = ?,  min_delivery_price = ?, closed_day = ?, possible_delivery = ?, status = ?, facilities = ?, favorite_num = ?,payment_method = ? where restaurant_Id = ? ";

        Object[] modifyUserNameParams = new Object[]{patchRestaurantReq.getName(),patchRestaurantReq.getNumber(),patchRestaurantReq.getAddress(),patchRestaurantReq.getOperationTime()
                , patchRestaurantReq.getIntroductionBoard(),patchRestaurantReq.getTipDelivery(),patchRestaurantReq.getTimeDelivery(),patchRestaurantReq.getCompanyRegistrationNumber(),patchRestaurantReq.getCategories()
                , patchRestaurantReq.getType(), patchRestaurantReq.getRestaurantImage(),patchRestaurantReq.getMinDeliveryPrice(),patchRestaurantReq.getClosedDay(),patchRestaurantReq.getPossibleDelivery(),patchRestaurantReq.getStatus()
                , patchRestaurantReq.getFacilities(), patchRestaurantReq.getFavoriteNum(),patchRestaurantReq.getPaymentMethod(),patchRestaurantReq.getRestaurantId()};

        for(int i = 0; i < modifyUserNameParams.length; i++){
            System.out.println(modifyUserNameParams[i]);
        }

            return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }


    public int deleteRestaurant(GetRestaurantRes getRestaurantRes){
        String deleteRestaurantQuery = "delete from restaurant where restaurant_Id = ?";

        int getRestaurantParams = getRestaurantRes.getRestaurantId();

        return this.jdbcTemplate.update(deleteRestaurantQuery,getRestaurantParams);
    }
}


