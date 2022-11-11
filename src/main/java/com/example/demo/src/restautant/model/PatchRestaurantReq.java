package com.example.demo.src.restautant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRestaurantReq {

    private int restaurantId;
    private String restaurantName;
}

