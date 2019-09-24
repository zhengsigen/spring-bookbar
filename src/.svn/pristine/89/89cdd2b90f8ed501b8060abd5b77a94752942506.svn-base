package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserCoupon {
    private Integer id;
    private Integer userId;
    private Integer couponId;
    private String  name;
    private String  wxName;
    private String  cover;

    //类型，0：邀请劵，1：运费劵
    private Integer type;
    //卷面值
    private double amount;
    //使用门槛
    private double perLimit;

    //备注
    private String remark;

    //截止日期
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    //创建日期
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //修改日期
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    public UserCoupon(){}
    public UserCoupon(Integer userId,Integer couponID,String remarks,Date expirationDate){
        this.userId=userId;
        this.couponId=couponID;
        this.remark=remarks;
        this.expirationDate=expirationDate;
    }
}