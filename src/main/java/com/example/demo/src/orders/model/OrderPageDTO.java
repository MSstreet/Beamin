package com.example.demo.src.orders.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderPageDTO {

    private List<OrderPageItemDTO> orders;

    public List<OrderPageItemDTO> getOrders(){
        return orders;
    }

    public void setOrders(List<OrderPageItemDTO> orders){
        this.orders = orders;
    }
}
