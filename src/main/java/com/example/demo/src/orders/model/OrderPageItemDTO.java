package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageItemDTO {

//    public OrderPageItemDTO(){
//
//    }

    public OrderPageItemDTO(String menuName, int menuPrice){
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

/////////////////////////////////////////////////////////////////////

    private Integer menuId;
    private int menuCount;

    private String menuName;

    private int menuPrice;

    private int totalPrice;

    public int initTotalPrice(){
        this.totalPrice = this.menuPrice * this.menuCount;
        return totalPrice;
    }

}
