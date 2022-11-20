package com.example.demo.src.restautant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRestaurantRes {

    private String jwt;
    private int restaurant_id;

}
