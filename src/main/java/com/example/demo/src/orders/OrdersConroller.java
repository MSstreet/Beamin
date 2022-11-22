package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;

import com.example.demo.src.orders.model.OrderInfo;

import com.example.demo.src.orders.model.Orders;
import com.example.demo.src.restautant.RestaurantProvider;

import com.example.demo.src.user.UserService;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@Controller
@RequestMapping("/app/orders")
public class OrdersConroller {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final OrdersService ordersService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final RestaurantProvider restaurantProvider;

    @Autowired
    private final OrdersProvider ordersProvider;

    @Autowired
    private final JwtService jwtService;

    public OrdersConroller(OrdersService ordersService, UserService userService,RestaurantProvider restaurantProvider, OrdersProvider ordersProvider, JwtService jwtService){
        this.ordersService = ordersService;
        this.userService = userService;
        this.restaurantProvider = restaurantProvider;
        this.ordersProvider = ordersProvider;
        this.jwtService = jwtService;
    }

//    @PostMapping("/{memberId}/{restaurantId}")
//    @ResponseBody
//    public BaseResponse<Orders> createOrder(@PathVariable("memberId") int memberId,@PathVariable("restaurantId") int restaurantId,@RequestBody OrderPageItemDTO ord) throws BaseException {

//        System.out.println("================================================");
//        System.out.println("odr:" + ord.getMenuId());
//
//        try {
//
//            int userIdxByJwt = jwtService.getUserIdx();
//
//            if(memberId != userIdxByJwt) {
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//        }catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//        Orders orders = new Orders();
//        UserInfo userInfo = userService.getUserInfo(memberId);
//        GetRestaurantRes getRestaurantRes = restaurantProvider.getRestaurant(restaurantId);
//        String tip = getRestaurantRes.getTipDelivery();
//        int total = 0;
//
//        orders.setAddr(userInfo.getAddr());
//        orders.setUserNum(userInfo.getUserNum());
//        orders.setDeliveryTip(tip);
//
//        List<OrderPageItemDTO> orderPageItemDTOS = ordersService.getMenusInfo(ord);
//        for(OrderPageItemDTO ord1 : orderPageItemDTOS){
//            total += ord1.initTotalPrice();
//        }
//        orders.setPrices(total);
//
//
//
//        return new BaseResponse<>(orders);
//    }


    @ResponseBody
    @PostMapping("/{memberId}/{restaurantId}")
    public BaseResponse<OrderInfo> createOrder(@PathVariable("memberId") int memberId,@PathVariable("restaurantId") int restaurantId, @RequestBody Orders orders) throws BaseException{

        try {

            int userIdxByJwt = jwtService.getUserIdx();

            if(memberId != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

        try{

            OrderInfo orderInfo = ordersService.createOrders(orders);

            return new BaseResponse<>(orderInfo);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}

