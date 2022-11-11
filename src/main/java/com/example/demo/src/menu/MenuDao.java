package com.example.demo.src.menu;

import com.example.demo.src.menu.model.PostMenuReq;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MenuDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createMenu(PostMenuReq postMenuReq){

        String createMenuQuery = "insert into restaurant (restaurant_Id,name,price,image_menu,popularity,content)" +
                " VALUES (?,?,?,?,?,?,)";

        Object[] createMenuParams = new Object[]{postMenuReq.getRestaurantId().getRestaurantId(),postMenuReq.getName(), postMenuReq.getPrice()
        ,postMenuReq.getImageMenu(),postMenuReq.getPopularity(), postMenuReq.getContent()};

        for(int i = 0; i < createMenuParams.length; i++){
            System.out.println(createMenuParams[i]);
        }

        this.jdbcTemplate.update(createMenuQuery, createMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";

        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

}
