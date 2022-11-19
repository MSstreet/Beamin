package com.example.demo.src.restautant;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restautant.model.*;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


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
    public BaseResponse<PostRestaurantRes> createRestaurant(@RequestBody PostRestaurantReq postRestaurantReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        //전화번호형식, 사업자번호형식, 소개글제한,

        //전화번호형식

        try{
            
            PostRestaurantRes postRestaurantRes = restaurantService.createRestaurant(postRestaurantReq);

            return new BaseResponse<>(postRestaurantRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetRestaurantRes>> getRestaurants() {
        try{
                List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants();
                return new BaseResponse<>(getRestaurantRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @PutMapping("/{restaurantId}")
    public BaseResponse<String> modifyRestaurant(@PathVariable("restaurantId") int restaurantId, @RequestBody Restaurant restaurant){


        try {
            //jwt에서 idx 추출.
            //int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

//            if(restaurantId != restaurant.getRestaurantId()){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }

            PatchRestaurantReq patchRestaurantReq = new PatchRestaurantReq(restaurant.getRestaurantId(),restaurant.getName(),restaurant.getNumber(),restaurant.getAddress(),restaurant.getOperationTime()
                    , restaurant.getIntroductionBoard(),restaurant.getTipDelivery(),restaurant.getTimeDelivery(),restaurant.getCompanyRegistrationNumber(),restaurant.getCategories()
                    , restaurant.getType(), restaurant.getRestaurantImage(),restaurant.getMinDeliveryPrice(),restaurant.getClosedDay(),restaurant.getPossibleDelivery(),restaurant.getStatus()
                    , restaurant.getFacilities(), restaurant.getFavoriteNum(),restaurant.getPaymentMethod());


            restaurantService.modifyRestaurant(patchRestaurantReq, restaurantId);

            String result = "";

            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{restaurantId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public GetRestaurantRes getRestaurant(@PathVariable("restaurantId") int restaurantId) {

        try{
            GetRestaurantRes getRestaurantRes = restaurantProvider.getRestaurant(restaurantId);
            return getRestaurantRes;
        } catch(BaseException exception){
            return null;
        }

    }

    @ResponseBody
    @PatchMapping("/{restaurantId}")
    public BaseResponse<String> deleteRestaurant(@PathVariable("restaurantId") int restaurantId){

        try {

            GetRestaurantRes getRestaurantRes = getRestaurant(restaurantId);

            System.out.println(getRestaurantRes.getRestaurantId());

            restaurantService.deleteRestaurant(getRestaurantRes);

            String result = "레스토랑을 삭제하였습니다.";

            return new BaseResponse<>(result);


        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
