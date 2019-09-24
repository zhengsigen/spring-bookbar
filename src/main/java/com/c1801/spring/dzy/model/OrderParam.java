package com.c1801.spring.dzy.model;

import lombok.Data;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/9/5 23:01
 * 购买订单生成参数
 */
@Data
public class OrderParam {

    /**
     * 订单地址
     */
    private Address address;

    /**
     * 使用的优惠券id
     */
    private Integer couponId;


    /**
     * 使用的运费券id
     */
    private Integer freightCouponId;

    /**
     * 运费模板id
     */
    private Integer freightId;

    /**
     * 生成订单的购物商品
     */
    private List<Integer> cartIds;
}


