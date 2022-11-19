package com.example.demo.src.menu;

import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.menu.model.PostMenuReq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MenuDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createMenu(PostMenuReq postMenuReq) {

        String createMenuQuery = "insert into menu (restaurant_Id,name,price,popularity,content)" +
                " VALUES (?,?,?,?,?)";

        Object[] createMenuParams = new Object[]{postMenuReq.getRestaurantId(), postMenuReq.getName(), postMenuReq.getPrice()
                , postMenuReq.getPopularity(), postMenuReq.getContent()};


        for (int i = 0; i < createMenuParams.length; i++) {
            System.out.println(createMenuParams[i]);
            System.out.println(createMenuParams[i].getClass().getName());
        }

        this.jdbcTemplate.update(createMenuQuery, createMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

    }

    public List<GetMenuRes> getMenus() {

        String getMenusQuery = "select * from menu";

        return this.jdbcTemplate.query(getMenusQuery,
                (rs, rowNum) -> new GetMenuRes(
                        rs.getInt("menu_Id"),
                        rs.getInt("restaurant_Id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getBoolean("popularity"),
                        rs.getString("content")
                ));

    }

    public GetMenuRes getMenuByMenuId(int menuId){
        String getMenusQuery = "select * from menu where menu_Id = ?";

        int getMenuParam = menuId;

        return this.jdbcTemplate.queryForObject(getMenusQuery,
                (rs, rowNum) -> new GetMenuRes(
                        rs.getInt("menu_Id"),
                        rs.getInt("restaurant_Id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getBoolean("popularity"),
                        rs.getString("content")),
                getMenuParam);
    }

    public GetMenuRes getRestaurantMenu(int restaurantId) {

        String getMenusQuery = "select * from menu where restaurant_id = ?";

        int getRestaurantParam = restaurantId;

        return this.jdbcTemplate.queryForObject(getMenusQuery,
                (rs, rowNum) -> new GetMenuRes(
                        rs.getInt("menu_Id"),
                        rs.getInt("restaurant_Id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getBoolean("popularity"),
                        rs.getString("content")),
                getRestaurantParam);

    }

    public int modifyMenu(PostMenuReq postMenuReq) {

        String modifyMenuQuery = "update menu set name = ?, price = ?, popularity = ?, content = ? where menu_Id = ?";

        Object[] modifyMenuParams = new Object[]{postMenuReq.getName(), postMenuReq.getPrice(), postMenuReq.getPopularity()
                , postMenuReq.getContent(), postMenuReq.getMenuId()};

        return this.jdbcTemplate.update(modifyMenuQuery, modifyMenuParams);
    }

    public int deleteMenu(int menuId) throws BaseException {
        String deleteRestaurantQuery = "delete from menu where menu_Id = ?";

        int getRestaurantParams = menuId;

        return this.jdbcTemplate.update(deleteRestaurantQuery,getRestaurantParams);
    }

}







        //    String modifyUserNameQuery = "update restaurant set name = ? ,number = ?, address = ?, operation_time = ?, introduction_board = ?, tip_delivery = ?, time_delivery = ?,company_registration_number = ?," +
//            " categories = ?, type = ?,  restaurant_image = ?,  min_delivery_price = ?, closed_day = ?, possible_delivery = ?, status = ?, facilities = ?, favorite_num = ?,payment_method = ? where restaurant_Id = ? ";
//
//    Object[] modifyUserNameParams = new Object[]{patchRestaurantReq.getName(),patchRestaurantReq.getNumber(),patchRestaurantReq.getAddress(),patchRestaurantReq.getOperationTime()
//            , patchRestaurantReq.getIntroductionBoard(),patchRestaurantReq.getTipDelivery(),patchRestaurantReq.getTimeDelivery(),patchRestaurantReq.getCompanyRegistrationNumber(),patchRestaurantReq.getCategories()
//            , patchRestaurantReq.getType(), patchRestaurantReq.getRestaurantImage(),patchRestaurantReq.getMinDeliveryPrice(),patchRestaurantReq.getClosedDay(),patchRestaurantReq.getPossibleDelivery(),patchRestaurantReq.getStatus()
//            , patchRestaurantReq.getFacilities(), patchRestaurantReq.getFavoriteNum(),patchRestaurantReq.getPaymentMethod(),patchRestaurantReq.getRestaurantId()};
//
//        for(int i = 0; i < modifyUserNameParams.length; i++){
//        System.out.println(modifyUserNameParams[i]);
//        }
//
//        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);