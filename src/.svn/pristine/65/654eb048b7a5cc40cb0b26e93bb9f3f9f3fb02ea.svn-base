package com.c1801.spring.dzy.common.wx.auth;

import lombok.Data;

/**
 * user：少
 * dateTime: 2019/9/1 14:35
 * 通过code从微信获取到的信息
 */
@Data
public class AccessToken {

    /**
     * accessToken 网页授权接口调用凭证,
     */
    private String accessToken;


    /**
     * 有效时长
     */
    private Integer expiresIn;


    /**
     * 用于刷新accessToken 有效期为30天
     */
    private String  refreshToken;


    /**
     * 用于获取用户信息 在此公众号唯一
     */
    private String openId;


    /**
     * 用户授权的作用域
     */
    private String scope;



}
