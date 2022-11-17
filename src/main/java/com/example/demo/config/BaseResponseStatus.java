package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users_ID
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 입력해주세요."),

    POST_USERS_INVALID_ID(false, 2011, "아이디 형식을 확인해주세요."),
    POST_USERS_EXISTS_ID(false, 2012,"중복된 ID 입니다."),

    //user_password
    USERS_EMPTY_USER_PASSWORD(false, 2013, "유저 비밀번호를 입력해주세요."),
    POST_USERS_INVALID_PASSWORD(false, 2014, "비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다."),

    //user_nickName
    USERS_EMPTY_USER_NICKNAME(false, 2015, "유저 닉네임을 입력해주세요."),
    //POST_USERS_EXISTS_NICKNAME(false, 2015,"중복된 ID 입니다."),

    //user_mobilePhone
    USERS_EMPTY_USER_MOBILEPHONE(false, 2017, "유저 번호를 입력해주세요."),
    POST_USERS_INVALID_MOBILEPHONE(false, 2018, "전화번호 형식을 확인해주세요."),

    POST_USERS_EXISTS_MOBILEPHONE(false, 2019,"중복된 핸드폰 번호 입니다."),

    //user_address
    USERS_EMPTY_USER_ADDRESS( false, 2020, "주소를 입력해주세요."),

    //user_eamil
    POST_USERS_EMPTY_EMAIL(false, 2021, "이메일을 입력해주세요."),

    POST_USERS_INVALID_EMAIL(false, 2022, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2023,"중복된 이메일입니다."),


    POST_RESTAURANT_EMPTY_NAME(false,2024,"가게 이름을 입력해주세요."),
    POST_RESTAURANT_EMPTY_NUMBER(false,2025,"전화번호를 입력해주세요."),
    POST_RESTAURANT_EMPTY_ADDRESS(false,2026,"주소를 입력해주세요."),
    POST_RESTAURANT_EMPTY_OPERATIONTIME(false,2027,"운영시간을 입력해주세요"),
    POST_RESTAURANT_EMPTY_INTRODUCTIONBOARD(false,2028,"가게 소개글을 입력해주세요"),
    POST_RESTAURANT_EMPTY_TIPDELIVERY(false,2029,"배달비를 입력해주세요"),
    POST_RESTAURANT_EMPTY_TIMEDELIVERY(false,2030,"배달시간을 입력해주세요"),
    POST_RESTAURANT_EMPTY_COMPANYREGISTRATIONNUMBER(false,2031,"사업자번호를 입력해주세요"),
    POST_RESTAURANT_EMPTY_CATEGORIES(false,2032,"카테고리를 입력해주세요"),
    POST_RESTAURANT_EMPTY_TYPE(false,2033,"배달,포장,배달1 중 종류를 입력해주세요"),
    POST_RESTAURANT_EMPTY_RESTAURANTIMAGE(false,2034,"가게 이미지를 입력해주세요"),
    POST_RESTAURANT_EMPTY_MINDELIVERYPRICE(false,2035,"최소 배달비를 입력해주세요"),
    POST_RESTAURANT_EMPTY_CLOSEDDAY(false,2036,"휴업일을 입력해주세요"),
    POST_RESTAURANT_EMPTY_POSSIBLEDELIVERY(false,2037,"배달 가능 지역을 입력해주세요"),
    POST_RESTAURANT_EMPTY_STATUS(false,2038,"상태를 입력해주세요"),
    POST_RESTAURANT_EMPTY_FACILITIES(false,2039,"편의시설을 입력해주세요"),
    POST_RESTAURANT_EMPTY_FAVORITENUM(false,2040," 입력해주세요"),
    POST_RESTAURANT_EMPTY_PAYMENTMETHOD(false,2041,"결제방법을 입력해주세요"),

    POST_MENU_INVALID_PRICE(false,2042,"숫자만 입력 가능합니다."),





    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),


    //[PATCH] /restaurant/{restaurantId}
    MODIFY_FAIL_RESTAURANT(false,4015,"레스토랑 정보 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
