package com.c1801.spring.dzy.common.wx.sdk;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.c1801.spring.dzy.common.wx.msg.WXMsg;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * user：少
 * dateTime: 2019/9/1 15:46
 *
 * js-sdk配置
 */
@Configuration
public class SDKConfig {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 公众号基础配置accessToken
     */
    private static String accessToken = null;

    /**
     * 有效时长
     */
    private static int accessExpiresIn = 0;

    /**
     *  access更新时间
     */
    private static long accessUpdateDate = 0;



    /**
     * jsapiticket
     */
    private static String jsapiTicket = null;


    /**
     * ticket有效时长
     */
    private static int ticketExpiresIn = 0;

    /**
     *  ticket更新时间
     */
    private static long ticketUpdateDate = 0;


    /**
     * 用户获取accessToken
     */
    private static final String ACCESS_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";


    private static final String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";


    /**
     * 通过appid 和secret 获取accessToken
     * @param appId
     * @param secret
     * @param refresh 是否主动刷新accessToken
     * @return
     */
    public String getAccessToken(String appId, String secret, boolean refresh){

        long updateDate = System.currentTimeMillis()/1000;

        //accessToken 没有过期而且主动不刷新，则直接返回 预留2分钟
        if(accessToken != null && updateDate - accessUpdateDate < accessExpiresIn + 2*60 && !refresh){
            return accessToken;
        }
        System.out.println("刷新access");
        accessUpdateDate = updateDate;
        String accessUrl = String.format(ACCESS_URL,appId, secret);
        //1. 获取accessToken
        ResponseEntity<String> accessEntity = restTemplate.getForEntity(accessUrl, String.class);
        
        System.out.println("\n\n accessEntity = " + accessEntity.getStatusCode());
        System.out.println("accessEntity.getBody() = " + accessEntity.getBody());

        JSONObject jsonObject = JSON.parseObject(accessEntity.getBody());
        accessToken = jsonObject.getString("access_token");
        accessExpiresIn = jsonObject.getInteger("expires_in");

        return accessToken;
    }


    /**
     * 通过accessToken url 获取wxconfig参数
     * @param appId
     * @param accessToken
     * @param url
     * @param refresh 是否主动刷新jsapiTicket
     * @return
     */
    public WXConfigParam getWXConfig(String appId, String accessToken, String url, boolean refresh){

        String jsApiTicketUrl = String.format(JSAPI_TICKET_URL, accessToken);
        long updateDate = System.currentTimeMillis()/1000;

        //ticket没有过期而且主动不刷新，则直接返回 预留2分钟
        if(jsapiTicket == null || (updateDate - ticketUpdateDate > ticketExpiresIn - 2 * 60)  || refresh){
            System.out.println("刷新 ticket");
            ticketUpdateDate = updateDate;

            //2.获取jsapiTicket
            String jsapiTicketObject = restTemplate.getForObject(jsApiTicketUrl, String.class);
            System.out.println("\n\n jsapiTicket = " + jsapiTicketObject);

            JSONObject jsonObject = JSON.parseObject(jsapiTicketObject);
            jsapiTicket = jsonObject.getString("ticket");
            ticketExpiresIn = jsonObject.getInteger("expires_in");
            System.out.println("\n\n jsapiTicketJsonObject = " + jsonObject);
        }

        Map<String, String> map = new TreeMap<>();

        //3. 签名
        Map<String, String> params = new TreeMap<>();
        //时间戳
        params.put("timestamp", System.currentTimeMillis() / 1000 + "");
        //随机字符串
        params.put("noncestr", UUID.randomUUID().toString().replaceAll("-", ""));
        //jsapiTicket
        params.put("jsapi_ticket", jsapiTicket);
        //当前网页的URL，不包含#及其后面部分
        params.put("url", url);

        String sign = sign(params);

        WXConfigParam wxConfig = new WXConfigParam();
        wxConfig.setAppId(appId);
        wxConfig.setTimestamp(params.get("timestamp"));
        wxConfig.setNonceStr(params.get("noncestr"));
        wxConfig.setSignature(sign);

        return wxConfig;
    }


    /**
     * 发送模板消息
     * @param appId
     * @param accessToken
     * @param templateId
     * @param openId
     * @param templateMsgs
     * @param url
     * @return
     */
    public String sendTemplateMsg(String appId, String accessToken, String templateId, String openId, Map<String, WXMsg> templateMsgs, String url){

        System.out.println("模板消息accessToken = " + accessToken);
        JSONObject params = new JSONObject();
        //openId
        params.put("touser", openId);
        params.put("appId",appId);
        params.put("template_id",templateId);
        params.put("url",url);

        params.put("data", templateMsgs);

        String templateObject = restTemplate.postForObject(String.format(TEMPLATE_URL , accessToken), params, String.class);
        return templateObject;
    }



    /**
     * 签名
     * @param map
     * @return
     */
    private String sign(Map map){

        Set<String> keySet = map.keySet();
        StringBuffer sign = new StringBuffer();
        map.forEach((key,value) ->{
            if(sign.length() > 0){
                sign.append("&");
            }
            sign.append(key).append("=").append(value);

        });
        System.out.println("sign = " + sign);
        //sha1加密
        String sha1Sign = DigestUtils.sha1Hex(sign.toString());
        return sha1Sign;
    }

}
