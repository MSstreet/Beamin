package com.example.demo.src.shoppingBasket;

import com.example.demo.config.BaseException;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketDto;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ShoppingBasketService {

    private final ShoppingBasketDao shoppingBasketDao;
    private final ShoppingBasketProvider shoppingBasketProvider;
    private final JwtService jwtService;


    @Autowired
    public ShoppingBasketService(ShoppingBasketDao shoppingBasketDao, ShoppingBasketProvider shoppingBasketProvider, JwtService jwtService) {
        this.shoppingBasketDao = shoppingBasketDao;
        this.shoppingBasketProvider = shoppingBasketProvider;
        this.jwtService = jwtService;
    }

//    public ShoppingBasketRes addShoppingBasket(ShoppingBasketDto shoppingBasketDto) {
//
//        if (shoppingBasketDao.checkShoppingBasket(shoppingBasketDto) == 1) {
//
//            List<ShoppingBasketRes> shoppingBasket = shoppingBasketDao.getShoppingBasket();
//
//            if (shoppingBasket.get(0).getRestaurantId() != shoppingBasketDto.getRestaurantId()) {
//                //장바구니 전부 삭제 후 새로운거 추가
//                shoppingBasketDao.deleteAllShoppingBasket();
//                 shoppingBasketDao.createShoppingBasket(shoppingBasketDto);
//
//            } else {
//                //같을 때는 메뉴가 같은지 확인하고 같으면 메뉴 수량만 하나 올려주고 아니면 새로운거 추가
//                if (shoppingBasket.get(0).getMenuId() == shoppingBasketDto.getMenuId()) {
//                     shoppingBasketDao.modifyShoppingBasket(shoppingBasketDto);
//                } else {
//                     shoppingBasketDao.modifyShoppingBasket1(shoppingBasketDto);
//                }
//            }
//        }
//    }
}
