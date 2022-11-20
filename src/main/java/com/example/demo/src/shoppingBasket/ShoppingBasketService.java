package com.example.demo.src.shoppingBasket;

import com.example.demo.config.BaseException;
import com.example.demo.src.menu.MenuProvider;
import com.example.demo.src.menu.model.GetMenuRes;

import com.example.demo.src.shoppingBasket.model.ShoppingBasketDto;
import com.example.demo.src.shoppingBasket.model.ShoppingBasketRes;

import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Service
public class ShoppingBasketService {

    private final ShoppingBasketDao shoppingBasketDao;
    private final ShoppingBasketProvider shoppingBasketProvider;

    private final MenuProvider menuProvider;
    private final JwtService jwtService;


    @Autowired
    public ShoppingBasketService(ShoppingBasketDao shoppingBasketDao, ShoppingBasketProvider shoppingBasketProvider,MenuProvider menuProvider, JwtService jwtService) {
        this.shoppingBasketDao = shoppingBasketDao;
        this.shoppingBasketProvider = shoppingBasketProvider;
        this.menuProvider = menuProvider;
        this.jwtService = jwtService;
    }

    public ShoppingBasketRes addShoppingBasket(ShoppingBasketDto shoppingBasketDto) throws BaseException {
        boolean check = false;
        int index = 0;

        try {
            if (shoppingBasketDao.checkShoppingBasket(shoppingBasketDto) == 1) {

                List<ShoppingBasketRes> shoppingBasket = shoppingBasketDao.getShoppingBasketByUserId(shoppingBasketDto.getUserId());

                GetMenuRes getMenuRes1 = menuProvider.getMenuByMenuId(shoppingBasket.get(0).getMenuId());
                GetMenuRes getMenuRes = menuProvider.getMenuByMenuId(shoppingBasketDto.getMenuId());

                if (!(getMenuRes1.getRestaurantId().equals(getMenuRes.getRestaurantId()))) {
                    //장바구니 전부 삭제 후 새로운거 추가

                    shoppingBasketDao.deleteAllShoppingBasket();
                    shoppingBasketDao.createShoppingBasket(shoppingBasketDto);

                }else {

                    //레스토랑 같을 때는 메뉴가 같은지 확인하고 같으면 메뉴 수량만 하나 올려주고 아니면 새로운거 추가
                    //생각해보니 장바구니에 담긴 메뉴 빼올때 하나가 아니라 전부 꺼내는게 맞다 전부 있는 메뉴중에서 같은게 있는지 없는지를 확인해야하니까

                    for(int i = 0; i < shoppingBasket.size(); i++) {

                        if (shoppingBasket.get(i).getMenuId().equals(shoppingBasketDto.getMenuId())) {
                            //ShoppingBasketRes shoppingBasket1 = shoppingBasketDao.getShoppingBasketByMenuID(shoppingBasket.get(i).getMenuId());
                            //shoppingBasketDao.modifyShoppingBasket(shoppingBasketDto, shoppingBasket.get(i).getCount());
                            check = true;
                            index = i;
                            break;
                        }
                    }

                    if(check){
                        shoppingBasketDao.modifyShoppingBasket(shoppingBasketDto, shoppingBasket.get(index).getCount());
                    }else{
                        shoppingBasketDao.createShoppingBasket(shoppingBasketDto);
                    }
                }
            }

            else{
                shoppingBasketDao.createShoppingBasket(shoppingBasketDto);
            }

            ShoppingBasketRes shoppingBasketRes1 = shoppingBasketDao.getShoppingBasket(shoppingBasketDto.getUserId());

            return shoppingBasketRes1;
        }catch (Exception exception){
            return null;
        }
    }

    public void deleteShoppingBasket(int ShoppingBasketId) throws BaseException{
        try{
            int result = shoppingBasketDao.deleteShoppingBasket(ShoppingBasketId);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyShoppingBasket(ShoppingBasketDto shoppingBasketDto) throws BaseException{
        try{
            int result = shoppingBasketDao.modifyShoppingBasket1(shoppingBasketDto);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
