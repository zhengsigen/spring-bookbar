package com.c1801.spring.dzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

/**
 * user：少
 * dateTime: 2019/8/7 15:24
 * 账户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer userId;
    private User user;
    private Address address;
}
