package com.example.demo.src.shoppingBasket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketDto;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

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
    @ResponseBody
    @PostMapping("/{userId}/join")
    public BaseResponse<ShoppingBasketRes> createRestaurant(@PathVariable("userId") int userId,@RequestBody ShoppingBasketDto shoppingBasketDto) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        try {

            int userIdxByJwt = jwtService.getUserIdx();

            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

        try{
            ShoppingBasketRes shoppingBasketRes = shoppingBasketService.addShoppingBasket(shoppingBasketDto);

            return new BaseResponse<>(shoppingBasketRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{userId}/list")
    public BaseResponse<List<ShoppingBasketRes>> getShoppingBaskets(@PathVariable("userId") int userId){

        try {
            int userIdxByJwt = jwtService.getUserIdx();

            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<ShoppingBasketRes> shoppingBasketRes = shoppingBasketProvider.getShoppingBasketsByUserId(userId);
            return new BaseResponse<>(shoppingBasketRes);

        }catch (BaseException exception) {
                return new BaseResponse<>((exception.getStatus()));
            }
    }

    @ResponseBody
    @PatchMapping("/{userId}/{shoppingBasketId}")
    public BaseResponse<String> deleteRestaurant(@PathVariable("userId") int userId, @PathVariable("shoppingBasketId") int shoppingBasketId){

        try {
            int userIdxByJwt = jwtService.getUserIdx();

            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            shoppingBasketService.deleteShoppingBasket(shoppingBasketId);

            String result = "요청하신 메뉴를 삭제하였습니다.";

            return new BaseResponse<>(result);


        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PutMapping("/{userId}/{shoppingBasketId}")
    public BaseResponse<String> modifyShoppingBasket(@PathVariable("userId") int userId, @PathVariable("shoppingBasketId") int shoppingBasketId, @RequestBody ShoppingBasketDto shoppingBasketDto){

        try {
            int userIdxByJwt = jwtService.getUserIdx();

            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            shoppingBasketService.modifyShoppingBasket(shoppingBasketDto);

            String result = "요청하신 메뉴를 삭제하였습니다.";

            return new BaseResponse<>(result);


        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }
}

