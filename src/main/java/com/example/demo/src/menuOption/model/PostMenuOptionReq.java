package com.example.demo.src.menuOption.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PostMenuOptionReq {
    // 메뉴옵션ID
    private Integer menuOptionId;

    // 메뉴ID
    private Integer menuId;

    // 옵션
    private String option;
}
