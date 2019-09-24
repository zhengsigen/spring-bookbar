package com.c1801.spring.dzy.model;

import lombok.Data;

@Data
public class CouponRule {
    private Integer id;
    private Integer couponId;
    private double sendCondition;
    private Integer count;
}
