package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/26 20:56
 * 购物车
 */
@Data
public class Cart{

    /**
     * 购物车id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 书籍信息
     */
    private Book book;


    /**
     * 品相
     */
    private Integer condition;


    /**
     * 折扣
     */
    private Double discount;

    /**
     * 是否有其他单品
     */
    private boolean hasOtherCondition;


    /**
     * 是否选中
     */
    private boolean hasSelected;


    /**
     * 是否在订单中
     */
    private boolean inOrder;

    /**
     * 是否有库存
     */
    private  boolean hasStock;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
