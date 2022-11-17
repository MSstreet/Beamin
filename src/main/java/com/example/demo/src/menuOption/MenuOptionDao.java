package com.example.demo.src.menuOption;

import com.example.demo.config.BaseException;
import com.example.demo.src.menuOption.model.PostMenuOptionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MenuOptionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createMenuOption(PostMenuOptionReq postMenuOptionReq){

        String createMenuOptionQuery = "insert into menu_option (menu_id,options) VALUES (?,?)";

        Object[] createMenuParams = new Object[]{postMenuOptionReq.getMenuId(),postMenuOptionReq.getOption()};

        this.jdbcTemplate.update(createMenuOptionQuery, createMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";

        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyMenuOption(PostMenuOptionReq postMenuOptionReq){

        String modifyMenuOptionMenu = "update menu_option set options = ? where menu_option_id = ?";

        Object[] modifyMenuOptionParams = new Object[]{postMenuOptionReq.getOption(),postMenuOptionReq.getMenuOptionId()};


        return this.jdbcTemplate.update(modifyMenuOptionMenu,modifyMenuOptionParams);
    }

    public int deleteMenuOption(int menuOptionId) throws BaseException {
        String deleteMenuOptionQuery = "delete from menu_option where menu_option_Id = ?";

        int getMenuOptionParams = menuOptionId;

        return this.jdbcTemplate.update(deleteMenuOptionQuery,getMenuOptionParams);
    }







}
