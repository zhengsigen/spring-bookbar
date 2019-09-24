package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/7 20:24
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Admin {


    private Integer id;

    @NotNull(message = "用户名不能为null")
    @NotEmpty(message = "用户名不能未空")
    @Size(min = 5, max = 20, message = "用户名应在5-20位之间")
    private String name;

    @NotNull(message = "密码不能为null")
    @NotEmpty(message = "密码不能未空")
    @Size(min = 6, max = 20, message = "密码应在5-20位之间")
    private String password;

    @NotNull
    @NotEmpty
    private String cover;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

}
