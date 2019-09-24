package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookMessage {
    //二级评论id
    private Integer id;
    //二级评论内容
    private String message;
    //用户id
    private Integer userId;
    //用户名
    private String  name;
    //用户微信名
    private String  wxName;
    //用户头像
    private String  cover;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
