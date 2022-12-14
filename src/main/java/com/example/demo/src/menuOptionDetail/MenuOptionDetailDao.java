package com.example.demo.src.menuOptionDetail;

import com.example.demo.config.BaseException;
import com.example.demo.src.menuOptionDetail.model.PostMenuOptionDetailReq;
import com.example.demo.src.restautant.model.GetRestaurantRes;
import com.example.demo.src.restautant.model.PatchRestaurantReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MenuOptionDetailDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createMenuOptionDetail(PostMenuOptionDetailReq postMenuOptionDetailReq){

        String createMenuOptionDetailQuery = "insert into menu_option_detail (menu_option_id,option_name,status) VALUES (?,?,?)";

        Object[] createMenuOptionDetailParams = new Object[]{postMenuOptionDetailReq.getMenuOptionId(),postMenuOptionDetailReq.getOptionName(),postMenuOptionDetailReq.getStatus()};

        this.jdbcTemplate.update(createMenuOptionDetailQuery, createMenuOptionDetailParams);

        String lastInsertIdQuery = "select last_insert_id()";

        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyMenuOptionDetail(PostMenuOptionDetailReq postMenuOptionDetailReq){

        String modifyMenuOptionMenu = "update menu_option_detail set option_name = ? where menu_option_detatil_id = ?";

        Object[] modifyMenuOptionParams = new Object[]{postMenuOptionDetailReq.getOptionName(),postMenuOptionDetailReq.getMenuOptionDetailId()};

        return this.jdbcTemplate.update(modifyMenuOptionMenu,modifyMenuOptionParams);
    }

    public int deleteMenuOptionDetail(int menuOptionDetailId){
        String deleteMenuOptionDetailQuery = "delete from menu_option_detail where menu_option_detatil_id = ?";


        return this.jdbcTemplate.update(deleteMenuOptionDetailQuery,menuOptionDetailId);
    }
}

