package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * user：黄
 * dateTime: 2019/8/26 11:16
 * 点赞
 */
@Data
public class Applaud {
    private Integer id;
    private String  name;
    private String  wxName;
    private String  cover;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
