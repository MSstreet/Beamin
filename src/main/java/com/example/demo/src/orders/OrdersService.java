package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.OrderInfo;
import com.example.demo.src.orders.model.OrderPageItemDTO;
import com.example.demo.src.orders.model.Orders;
import com.example.demo.src.restautant.RestaurantDao;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class OrdersService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrdersDao ordersDao;

    private final JwtService jwtService;

    @Autowired
    public OrdersService(OrdersDao ordersDao, JwtService jwtService) {
        this.ordersDao = ordersDao;
        this.jwtService = jwtService;
    }

    public List<OrderPageItemDTO> getMenusInfo(OrderPageItemDTO orders){
        List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();



        //for(OrderPageItemDTO ord : orders){
            OrderPageItemDTO menusInfo = ordersDao.getMenusInfo(orders.getMenuId());

            menusInfo.setMenuCount(orders.getMenuCount());
            System.out.println("=================================================================");
            System.out.println("menuInfoPrice:" + menusInfo.getMenuPrice());
            System.out.println(menusInfo.getMenuName());


            menusInfo.initTotalPrice();

            result.add(menusInfo);
       // }

        return result;
    }


    public OrderInfo createOrders(Orders orders) throws BaseException {
        try{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
            int ordersIdx = ordersDao.createOrders(orders);
            System.out.println("******************************************************************************************8");
            //jwt 발급.
            //String jwt = jwtService.createJwt(restaurantIdx);

            return new OrderInfo(ordersIdx);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}


