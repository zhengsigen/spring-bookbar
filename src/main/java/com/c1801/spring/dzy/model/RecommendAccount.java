package com.c1801.spring.dzy.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @郑思根
 * 实体类   ->  推荐统计
 * 根据推荐id获取到 书单详情(书单推荐用户，书单推荐书籍)，书籍详情，用户详情，推荐详情
 */
@Data
public class RecommendAccount {

    /**
     * 推荐id
     */
    private Integer id;

    /**
     * 书籍详情
     */
    private Book book;

    /**
     * 书单详情
     */
    private BookCollectionPack bookCollectionPack;

    /**
     * 用户详情
     */
    private User user;

    /**
     * 推荐详情
     */
    private RecommendBook recommendBook;


    /**
     * 点赞数量
     */
    private Integer applaud;

    
    /**
     * 是否点赞
     */
    private boolean applauded;


    /**
     * 评语
     */
    private String comment;


    /**
     * 推荐时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    /**
     * 修改时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;



}
