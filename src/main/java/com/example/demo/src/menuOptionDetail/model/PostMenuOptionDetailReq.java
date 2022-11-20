package com.example.demo.src.menuOptionDetail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostMenuOptionDetailReq {

    // 메뉴옵션디테일ID
    private Integer menuOptionDetailId;

    // 메뉴옵션ID
    private Integer menuOptionId;

    // 디테일옵션이름
    private String optionName;

    // 상태 0:비활성 1:활성
    private Boolean status;

}
