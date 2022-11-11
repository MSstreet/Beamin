package com.example.demo.src.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

//    // 생성일
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime creationDate;
//
//    // 수정일
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime modificationDate;

    // 메일 수신 여부
    private Boolean mailStatus;

    // SMS 수신 여부
    private Boolean smsStatus;


}
