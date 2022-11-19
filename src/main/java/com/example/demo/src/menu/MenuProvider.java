package com.example.demo.src.menu;


import com.example.demo.config.BaseException;

import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.utils.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class MenuProvider {

    private final MenuDao menuDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuProvider(MenuDao menuDao, JwtService jwtService) {
        this.menuDao = menuDao;
        this.jwtService = jwtService;
    }

    public List<GetMenuRes> getMenus() throws BaseException {

        try{
            List<GetMenuRes> getMenuRes = menuDao.getMenus();
            return getMenuRes;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetMenuRes getMenuByMenuId(int menu_Id) throws BaseException{

        try{
            GetMenuRes getMenuRes = menuDao.getMenuByMenuId(menu_Id);
            return getMenuRes;
        }catch (Exception exception){
            throw  new BaseException(DATABASE_ERROR);
        }
    }

    public GetMenuRes getRestaurantMenu(int restaurantId) throws  BaseException{

        try{
            GetMenuRes getMenuRes = menuDao.getRestaurantMenu(restaurantId);
            return getMenuRes;

        }catch (Exception exception){
            throw  new BaseException(DATABASE_ERROR);
        }
    }


}
