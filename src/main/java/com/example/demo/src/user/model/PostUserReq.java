package com.example.demo.src.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    // 로그인ID
    private String loginId;
    // 비밀번호
    private String password;

    // 별칭
    private String nickName;

    // 핸드폰번호
    private String mobilePhone;

    // 현 주소
    private String address;

    // 메일
    private String email;

    // 등급
    private String grade;


    // 메일 수신 여부
    private Boolean mailStatus;

    // SMS 수신 여부
    private Boolean smsStatus;

    private String profileImage;

}
