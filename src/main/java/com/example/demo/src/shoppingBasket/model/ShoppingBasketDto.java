package com.example.demo.src.shoppingBasket.model;

import com.example.demo.src.menu.model.GetMenuRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasketDto {

    // 장바구니ID
    private Integer shoppingBasketId;

    // 유저 고유 번호
    private Integer userId;

    // 메뉴 고유 번호
    private Integer menuId;

    //private Integer restaurantId;

    // 물품 수량
    private int count;

   private String menuName;

   //private int menuPrice;

   private int totalPrice;


   public void initSaleTotal(GetMenuRes getMenuRes){

       this.totalPrice = getMenuRes.getPrice() * this.count;
   }
}
