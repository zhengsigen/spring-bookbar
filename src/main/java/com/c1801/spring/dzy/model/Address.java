package com.c1801.spring.dzy.model;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/7 15:00
 */
@Data

public class Address {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$",message = "手机号不正确")
    private String phone;

    @NotEmpty
    private String province;

    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String street;

    private Date createDate;

    private Date updateDate;

}
