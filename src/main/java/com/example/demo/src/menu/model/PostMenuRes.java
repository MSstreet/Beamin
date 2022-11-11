package com.example.demo.src.menu.model;

import com.example.demo.src.restautant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMenuRes {
    private String jwt;
    // 메뉴 고유 번호
    private Integer menuId;

}
