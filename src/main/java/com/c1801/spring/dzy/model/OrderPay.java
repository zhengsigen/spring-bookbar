package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 订单支付类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPay {
    //订单id
    private Integer orderId;


    //支付类型
    private Integer payType;

    //最终价格
    private double finalPrice;

    //余额付款
    private double feePay;

    //微信付款
    private double wxPay;

    //自动取消时长
    private long autoConfirmTime;

    //优惠券
    private Integer couponId;

    //运费劵
    private Integer freightId;

    //优惠券金额
    private double couponAmount;

    //运费劵金额
    private double freightAmount;

    //备注
    private String remark;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
