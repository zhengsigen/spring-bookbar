package com.c1801.spring.dzy.model;

import lombok.Data;

import java.util.Date;

/***
 * 一级评论实体类
 */
@Data
public class Comment {

    //评论id
    private Integer id;
    //用户id
    private Integer userId;
    //书籍id
    private Integer bookId;
    //评语
    private String comment;
    private Date createDate;
    private Date updateDate;
}
