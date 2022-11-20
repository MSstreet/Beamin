package com.example.demo.src.restautant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRestaurantReq {

//    // 가게 고유 번호
//    private Integer restaurantId;

    // 가게상호
    private String name;

    // 가게 전화번호
    private String number;

    // 주소
    private String address;

    // 운영시간
    private String operationTime;

    // 가게소게글
    private String introductionBoard;

    // 배달비
    private String tipDelivery;

    // 배달시간
    private String timeDelivery;

    // 사업자번호
    private String companyRegistrationNumber;

    // 카테고리
    private String categories;

    // 배달,배민1,포장
    private Integer type;

    // 가게 사진
    private String restaurantImage;

    // 최소 주문 비용
    private String minDeliveryPrice;

    // 휴무일
    private String closedDay;

    // 배달 지역
    private String possibleDelivery;

    // 상태
    private String status;

    // 편의시설
    private String facilities;

    // 찜수
    private Integer favoriteNum;

    // 결제 방법
    private String paymentMethod;

    private String search;
}
