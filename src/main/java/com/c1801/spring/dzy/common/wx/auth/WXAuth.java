package com.c1801.spring.dzy.common.wx.auth;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * user：少
 * dateTime: 2019/9/1 14:19
 * 微信授权
 */
@Configuration
public class WXAuth {

    private RestTemplate restTemplate = new RestTemplate();

    //用于获取token 和openid
    private static final String ACCESS_TOKE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    //用户获取用户信息url
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";


    /**
     * 通过appid、appscret和code获取微信用户openid等信息
     * @param appId
     * @param secret
     * @param code
     * @return
     */
    public AccessToken auth(String appId, String secret, String code){

        //获取微信授权的用户信息
        String accessTokeUrl = String.format(ACCESS_TOKE_URL,appId,secret,code);

        ResponseEntity<String> tokenEntity = restTemplate.getForEntity(accessTokeUrl, String.class);

        System.out.println("tokenEntity = " + tokenEntity.getStatusCode());

        AccessToken access = JSON.parseObject(tokenEntity.getBody(), AccessToken.class);


        return access;

    }

    /**
     * 通过accessToken 和 openId 获取用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    public WXUser getWXUser(String accessToken, String openId){
        //设置请求响应编码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        //通过 access_token 和 openid获取用户信息
        String userInfoUrl = String.format(USER_INFO_URL, accessToken, openId);
        ResponseEntity<String> userEntity = restTemplate.getForEntity(userInfoUrl, String.class);

        WXUser user = JSON.parseObject(userEntity.getBody(), WXUser.class);

        return user;
    }





}
