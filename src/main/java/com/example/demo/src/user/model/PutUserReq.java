package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutUserReq {

    // 회원 고유 번호
    private Integer id;

    // 비밀번호
    private String password;

    // 별칭
    private String nickName;

    // 핸드폰번호
    private String mobilePhone;

    // 메일
    private String email;

    // 메일 수신 여부
    private Boolean mailStatus;

    // SMS 수신 여부
    private Boolean smsStatus;

    // 프로필 사진
    private String profileImage;

}
