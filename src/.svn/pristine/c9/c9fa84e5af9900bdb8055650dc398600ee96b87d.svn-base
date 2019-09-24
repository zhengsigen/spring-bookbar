package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/6 19:21
 */
@Data
public class User {

    private Integer id;

    @NotNull(message = "用户名不能为null")
    @NotEmpty(message = "用户名不能未空")
    @Size(min = 5, max = 20, message = "用户名应在5-20位之间")
    private String name;

    private String wxName;

    private String openId;

    private String cover;

    private Double balance;

    private Integer addrId;

    private Integer enabled;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date loginDate;

}
