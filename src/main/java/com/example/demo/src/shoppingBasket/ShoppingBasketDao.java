package com.example.demo.src.shoppingBasket;

import com.example.demo.src.shoppingBasket.model.ShoppingBasketDto;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ShoppingBasketDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createShoppingBasket(ShoppingBasketDto shoppingBasketDto){

        String createShoppingBasketQuery = "insert into shopping_basket (user_Id, menu_Id, count) VALUES(?,?,?)";



        Object[] createShoppingBasketParams = new Object[]{shoppingBasketDto.getUserId(),shoppingBasketDto.getMenuId()
        ,shoppingBasketDto.getCount()};

        this.jdbcTemplate.update(createShoppingBasketQuery,createShoppingBasketParams);

        String lastInsertIdQuery = "select last_insert_id()";

        System.out.println((lastInsertIdQuery));

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int deleteShoppingBasket(int shoppingBasketId){

        String deleteShoppingBasketQuery = "delete from shopping_basket where shopping_basket_id = ?";

        int getShoppingBasketParams = shoppingBasketId;

        return this.jdbcTemplate.update(deleteShoppingBasketQuery,getShoppingBasketParams);

    }

    public int deleteAllShoppingBasket(){

        String deleteShoppingBasketQuery = "delete from shopping_basket";

        return this.jdbcTemplate.update(deleteShoppingBasketQuery);

    }

    public int modifyShoppingBasket(ShoppingBasketDto shoppingBasketDto, int count){

        String modifyShoppingBasketQuery = "update shopping_basket set count = ? where menu_Id = ?";
        int j = shoppingBasketDto.getCount();
        int k = count + j;

        System.out.println(shoppingBasketDto.getMenuId());

        Object[] modifyShoppingBasketParams = new Object[]{k,shoppingBasketDto.getMenuId()};

        return this.jdbcTemplate.update(modifyShoppingBasketQuery,modifyShoppingBasketParams);
    }

    public int modifyShoppingBasket1(ShoppingBasketDto shoppingBasketDto){

        String modifyShoppingBasketQuery = "update shopping_basket set coutn = ? where shopping_basket_id = ?";

        Object[] modifyShoppingBasketParams = new Object[]{shoppingBasketDto.getCount(),shoppingBasketDto.getShoppingBasketId()};

        return this.jdbcTemplate.update(modifyShoppingBasketQuery,modifyShoppingBasketParams);
    }

    public ShoppingBasketRes getShoppingBasket(int userID){

        String getShoppingBasketsQuery = "select * from shopping_basket where user_Id = ? limit 1";

        int getShoppingBasketParams = userID;

        System.out.println("=========================================================================");

        return this.jdbcTemplate.queryForObject(getShoppingBasketsQuery,
                (rs, rowNum) -> new ShoppingBasketRes(
                        rs.getInt("shopping_basket_id"),
                        rs.getInt("menu_Id"),
                        rs.getInt("count")),getShoppingBasketParams);
    }

    public ShoppingBasketRes getShoppingBasketByMenuID(int menuId){

        String getShoppingBasketsQuery = "select * from shopping_basket where menu_Id = ?";

        int getShoppingBasketParams = menuId;

        System.out.println("=========================================================================");

        return this.jdbcTemplate.queryForObject(getShoppingBasketsQuery,
                (rs, rowNum) -> new ShoppingBasketRes(
                        rs.getInt("shopping_basket_id"),
                        rs.getInt("menu_Id"),
                        rs.getInt("count")) ,getShoppingBasketParams);
    }

    public int checkShoppingBasket(ShoppingBasketDto shoppingBasketDto){

        String checkShoppingBasketQuery = "select exists(select * from shopping_basket where user_Id = ?)";

        return this.jdbcTemplate.queryForObject(checkShoppingBasketQuery,int.class,shoppingBasketDto.getUserId());

    }
    public List<ShoppingBasketRes> getShoppingBasketByUserId(int userID){

        String getShoppingBasketsQuery = "select * from shopping_basket where user_Id = ?";

        int getShoppingBasketParams = userID;

        return this.jdbcTemplate.query(getShoppingBasketsQuery,
                (rs, rowNum) -> new ShoppingBasketRes(
                        rs.getInt("shopping_basket_id"),
                        rs.getInt("menu_Id"),
                        rs.getInt("count")) ,getShoppingBasketParams);
    }

}




