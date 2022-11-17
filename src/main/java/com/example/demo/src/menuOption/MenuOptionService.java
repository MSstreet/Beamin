package com.example.demo.src.menuOption;

import com.example.demo.config.BaseException;
import com.example.demo.src.menuOption.model.PostMenuOptionReq;
import com.example.demo.src.menuOption.model.PostMenuOptionRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_RESTAURANT;

@Service
public class MenuOptionService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final MenuOptionDao menuOptionDao;

    private final JwtService jwtService;

    @Autowired
    public MenuOptionService(MenuOptionDao menuOptionDao, JwtService jwtService){
        this.menuOptionDao = menuOptionDao;
        this.jwtService = jwtService;
    }

    public PostMenuOptionRes createMenuOption(PostMenuOptionReq postMenuOptionReq) throws BaseException {

        try {

            int menuOptionId = menuOptionDao.createMenuOption(postMenuOptionReq);

            //jwt 발급.
            String jwt = jwtService.createJwt(menuOptionId);

            return new PostMenuOptionRes(jwt, menuOptionId);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyMenuOption(PostMenuOptionReq postMenuOptionReq) throws BaseException {
        try{
            int result = menuOptionDao.modifyMenuOption(postMenuOptionReq);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_RESTAURANT);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }
}


//    public PostRestaurantRes createRestaurant(PostRestaurantReq postRestaurantReq) throws BaseException {
//
////        //중복
////        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
////            throw new BaseException(POST_USERS_EXISTS_EMAIL);
////        }
////        //Id 중복
////        if(userProvider.checkId(postUserReq.getLoginId()) ==1){
////            throw new BaseException(POST_USERS_EXISTS_ID);
////        }
//        try{
//
//            int restaurantIdx = restaurantDao.createRestaurant(postRestaurantReq);
//
//            //jwt 발급.
//            String jwt = jwtService.createJwt(restaurantIdx);
//
//            return new PostRestaurantRes(jwt,restaurantIdx);
//
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
