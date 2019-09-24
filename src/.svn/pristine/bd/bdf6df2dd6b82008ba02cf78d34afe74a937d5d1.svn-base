package com.c1801.spring.dzy.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * user：少
 * dateTime: 2019/9/5 11:31
 * 邀请记录
 */
@Data
public class InviteRecord {

    /**
     * 邀请人
     */
    private User inviterUser;

    /**
     * 被邀请人
     */
    private User inviteeUser;

    /**
     * 邀请状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
