package com.example.demo.src.shoppingBasket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketDto;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/shoppingbasket")
public class ShoppingBasketController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ShoppingBasketProvider shoppingBasketProvider;
    @Autowired
    private final ShoppingBasketService shoppingBasketService;
    @Autowired
    private final JwtService jwtService;

    public ShoppingBasketController(ShoppingBasketProvider shoppingBasketProvider, ShoppingBasketService shoppingBasketService, JwtService jwtService) {
        this.shoppingBasketProvider = shoppingBasketProvider;
        this.shoppingBasketService = shoppingBasketService;
        this.jwtService = jwtService;
    }

//    @ResponseBody
//    @PostMapping("/join")
//    public BaseResponse<ShoppingBasketRes> createRestaurant(@RequestBody ShoppingBasketDto shoppingBasketDto) {
//        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//        //전화번호형식, 사업자번호형식, 소개글제한,
//
//        //전화번호형식
//
//        try{
//
//
//            ShoppingBasketRes shoppingBasketRes = shoppingBasketService.addShoppingBasket(shoppingBasketDto);
//
//            return new BaseResponse<>(shoppingBasketRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}

