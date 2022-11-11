package com.example.demo.src.menu;

import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.PostMenuReq;
import com.example.demo.src.menu.model.PostMenuRes;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class MenuService {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuDao menuDao;
    private final MenuProvider menuProvider;
    private final JwtService jwtService;

    @Autowired
    public MenuService(MenuDao menuDao, MenuProvider menuProvider, JwtService jwtService) {
        this.menuDao = menuDao;
        this.menuProvider = menuProvider;
        this.jwtService = jwtService;
    }

    public PostMenuRes createMenu(PostMenuReq postMenuReq) throws BaseException {

        //중복
//        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }

        try{

            int restaurantIdx = menuDao.createMenu(postMenuReq);

            //jwt 발급.
            String jwt = jwtService.createJwt(restaurantIdx);

            return new PostMenuRes(jwt,restaurantIdx);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }


}
