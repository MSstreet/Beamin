package com.example.demo.src.restautant;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restautant.model.GetRestaurantRes;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.Restaurant;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.USERS_EMPTY_USER_ADDRESS;
import static com.example.demo.utils.ValidationRegex.*;
import static com.example.demo.utils.ValidationRegex.isRegexNumber;

@RestController
@RequestMapping("/app/restaurant")
public class RestaurantController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private final RestaurantProvider restaurantProvider;
    @Autowired
    private final RestaurantService restaurantService;

    @Autowired
    private final RestaurantProvider restaurantProvider;
    @Autowired
    private final JwtService jwtService;

    public RestaurantController(RestaurantService restaurantService,RestaurantProvider restaurantProvider,JwtService jwtService){
        this.restaurantService = restaurantService;
        this.restaurantProvider = restaurantProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<PostRestaurantReq> createUser(@RequestBody PostRestaurantReq postRestaurantReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        try{
            PostUserRes postUserRes = restaurantService.createRestaurant(postRestaurantReq);
            return new BaseResponse<>(postRestaurantReq);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetRestaurantRes>> getRestaurant() {
        try{
                List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants();
                return new BaseResponse<>(getRestaurantRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @PutMapping("/{restaurantId}")
    public BaseResponse<String> modifyUserName(@PathVariable("restaurantId") int restaurantId, @RequestBody Restaurant restaurant){

        try {
            //jwt에서 idx 추출.
            //int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

            if(restaurantId != restaurant.getRestaurantId()){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            //같다면 유저네임 변경
            PatchUserReq patchUserReq = new PatchUserReq(,.getNickName());
            .modifyUserName(patchUserReq);

            String result = "닉네임을 변경했습니다.";


            return new BaseResponse<>(result);


        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
