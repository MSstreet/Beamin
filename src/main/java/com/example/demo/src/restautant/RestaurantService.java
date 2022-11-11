package com.example.demo.src.restautant;



import com.example.demo.config.BaseException;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class RestaurantService {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantDao restaurantDao;

    private final JwtService jwtService;

    @Autowired
    public RestaurantService(RestaurantDao restaurantDao, JwtService jwtService) {
        this.restaurantDao = restaurantDao;
        this.jwtService = jwtService;
    }

    public PostUserRes createRestaurant(PostRestaurantReq postRestaurantReq) throws BaseException {

//        //중복
//        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }
//        //Id 중복
//        if(userProvider.checkId(postUserReq.getLoginId()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_ID);
//        }

        try{
            int restaurantIdx = restaurantDao.createRestaurant(postRestaurantReq);

            //jwt 발급.
            String jwt = jwtService.createJwt(restaurantIdx);

            return new PostUserRes(jwt,restaurantIdx);


        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
