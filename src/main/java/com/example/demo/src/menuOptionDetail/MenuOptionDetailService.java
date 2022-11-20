package com.example.demo.src.menuOptionDetail;

import com.example.demo.config.BaseException;
import com.example.demo.src.menuOptionDetail.model.GetMenuOptionDetailRes;
import com.example.demo.src.menuOptionDetail.model.PostMenuOptionDetailReq;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class MenuOptionDetailService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuOptionDetailDao menuOptionDetailDao;

    private final JwtService jwtService;

    @Autowired
    public MenuOptionDetailService(MenuOptionDetailDao menuOptionDetailDao, JwtService jwtService) {
        this.menuOptionDetailDao = menuOptionDetailDao;
        this.jwtService = jwtService;
    }

    public GetMenuOptionDetailRes createMenuOptionDetail(PostMenuOptionDetailReq postMenuOptionDetailReq) throws BaseException {

        try{

            int restaurantIdx = menuOptionDetailDao.createMenuOptionDetail(postMenuOptionDetailReq);

            //jwt 발급.
            //String jwt = jwtService.createJwt(restaurantIdx);

            return new GetMenuOptionDetailRes(restaurantIdx);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
