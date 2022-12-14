package com.example.demo.src.shoppingBasket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingBasket {

    // 장바구니ID
    private Integer shoppingBasketId;

    // 유저 고유 번호
    private Integer userId;

    // 메뉴 고유 번호
    private Integer menuId;

//    private Integer menuOptionId;
//
//    private Integer menuOptionDetailId;

    //private Integer restaurantId;

    // 물품 수량
    private int count;

    private String menuName;

    //private int menuPrice;

    private int totalPrice;
}
