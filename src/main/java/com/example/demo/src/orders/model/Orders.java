package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    // 주문 고유 번호
    private Integer orderId;

    // 유저 고유 번호
    private Integer userId;

//    private String addr;
//
//    private String userNum;

    // 가게 고유 번호
    private Integer restaurantId;

//    private String deliveryTip;
    // 결제 방법
    private String methodOfPayment;

    // 결제 상태
    private Boolean status;

    // 주문 가격
    private Integer prices;

    // 가게 사장님께
    private String toOwner;

    // 배달원님께
    private String toDeliveryman;

    // 일회용 여부
    private Boolean disposable;

    // 현금영수증
    private Boolean cashReceipts;

    // 픽업방법
    private String pickupMethod;

}
