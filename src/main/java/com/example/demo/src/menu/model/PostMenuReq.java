package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostMenuReq {

//    // 메뉴 고유 번호
    private Integer menuId;

    // 가게 고유 번호
    private Integer restaurantId;

    // 메뉴 이름
    private String name;

    // 메뉴 가격
    private Integer price;

//    // 메뉴 사진
//    private String imageMenu;

    // 인기 여부
    private Boolean popularity;

    // 메뉴 내용
    private String content;
}
