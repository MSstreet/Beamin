package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class User {
    // 회원 고유 번호
    private Integer id;

    //로그인ID
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

//    // 생성일
//    private LocalDateTime creationDate;
//
//    // 수정일
//    private LocalDateTime modificationDate;

    // 메일 수신 여부
    private Boolean mailStatus;

    // SMS 수신 여부
    private Boolean smsStatus;

    // 프로필 사진
    private String profileImage;


}
