package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {

    private Integer UserId;

    private String name;

    private String userNum;

    private String email;

    private String addr;
}
