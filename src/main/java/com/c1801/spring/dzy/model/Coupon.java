package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Coupon {
    private Integer id;
	private String  name;

    //类型，0：邀请劵，1：运费劵
    private Integer type;
    //卷面值
    private double amount;
    //使用门槛
    private double perLimit;


    //有效期(天)
    private Integer expiresIn;
    //创建日期

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //修改日期

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}