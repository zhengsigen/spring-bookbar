package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/8 15:47
 * 书籍Sku信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    /**
     * id
     */
    private Integer id;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 品相
     */
    private Integer condition;

    /**
     * 上下架状态
     */
    private Integer status;

    /**
     * 是否免费
     */
    private Boolean isFree;

    /**
     * 折扣
     */
    @Range(min = 0,max = 10, message = "折扣应在0-10之间")
    private Double discount;

    /**
     * 库存
     */
    @Range(min = 0, message = "库存不能为负数")
    private Integer stock;

    /**
     * 销量
     */
    @Range(min = 0, message = "销量不能为负数")
    private Integer sale;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date createDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date updateDate;

}
