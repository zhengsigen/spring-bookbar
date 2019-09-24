package com.c1801.spring.dzy.model;

import lombok.Data;

/**
 * user：少
 * dateTime: 2019/8/11 22:56
 * 卖书成功记录
 */
@Data
public class OrderBookRecord {


    /**
     * 卖书用户
     */
    private Integer userId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 品相 0：全新、良好 1：中等 2：不合格
     */
    private Integer condition;
}
