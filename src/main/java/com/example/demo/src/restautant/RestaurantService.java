package com.example.demo.src.restautant;



import com.example.demo.config.BaseException;
import com.example.demo.src.restautant.model.GetRestaurantRes;
import com.example.demo.src.restautant.model.PatchRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.utils.JwtService;
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

    public PostRestaurantRes createRestaurant(PostRestaurantReq postRestaurantReq) throws BaseException {

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

            return new PostRestaurantRes(jwt,restaurantIdx);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyRestaurant(PatchRestaurantReq patchRestaurantReq, int restaurantId) throws BaseException {
        try{
            int result = restaurantDao.modifyRestaurant(patchRestaurantReq ,restaurantId);

            System.out.println(result);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_RESTAURANT);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public void deleteRestaurant(GetRestaurantRes getRestaurantRes) throws BaseException{
        try{
            int result = restaurantDao.deleteRestaurant(getRestaurantRes);

            System.out.println("ddsfsdasdfasdfasdfasdfasfasdfasdfasdfasdfasdf");

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}




