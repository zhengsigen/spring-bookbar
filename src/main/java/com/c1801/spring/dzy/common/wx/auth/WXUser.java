package com.c1801.spring.dzy.common.wx.auth;

import lombok.Data;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/24 20:25
 *微信授权 用户信息
 */
@Data
public class WXUser {

    /**
     * openid
     */
    private String openid;


    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家地区
     */
    private String country;

    /**
     * 头像地址
     */
    private String headimgurl;

    /**
     * privilege
     */
    private List<String> privilege;

    /**
     * unionid
     */
    private String unionid;
}
