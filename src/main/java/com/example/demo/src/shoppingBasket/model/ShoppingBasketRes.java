package com.example.demo.src.shoppingBasket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasketRes {

    // 장바구니ID
    private Integer shoppingBasketId;

    //private Integer restaurantId;

    private Integer menuId;

    private int count;

}