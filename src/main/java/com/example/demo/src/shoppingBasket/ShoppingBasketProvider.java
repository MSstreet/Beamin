package com.example.demo.src.shoppingBasket;

import com.example.demo.config.BaseException;
import com.example.demo.src.restautant.RestaurantDao;
import com.example.demo.src.restautant.model.GetRestaurantRes;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ShoppingBasketProvider {

    private final ShoppingBasketDao shoppingBasketDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ShoppingBasketProvider(ShoppingBasketDao shoppingBasketDao, JwtService jwtService) {
        this.shoppingBasketDao = shoppingBasketDao;
        this.jwtService = jwtService;
    }

    public List<ShoppingBasketRes> getShoppingBasketsByUserId(int userId) throws BaseException {

        try{
            List<ShoppingBasketRes> shoppingBasketRes = shoppingBasketDao.getShoppingBasketByUserId(userId);

            return shoppingBasketRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
