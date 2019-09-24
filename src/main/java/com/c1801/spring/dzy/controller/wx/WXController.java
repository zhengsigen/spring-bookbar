package com.c1801.spring.dzy.controller.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.common.wx.qr.QRGenerate;
import com.c1801.spring.dzy.config.WXAuthorizedValids;
import com.c1801.spring.dzy.model.AuthorizedValids;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.model.WXSubscription;
import com.c1801.spring.dzy.service.WXUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@Transactional
@RequestMapping("/dzy/wx")
public class WXController {

    /**
     * 验证码有效时长
     */
    private Long validTime = 300000L;

    /**
     * 微信公众号
     */
    @Autowired
    WXSubscription wxSubscription;

    @Autowired
    WXAuthorizedValids wxAuthorizedValids;

    @Autowired
    WXUserService wxUserService;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * @param url
     * @return
     * @郑思根 调用摄像头扫一扫签名
     */
    @GetMapping("/config")
    public Map config(@RequestHeader("Referer") String url) {
        System.out.println(wxSubscription.getAppid());
        System.out.println(wxSubscription.getAppsecret());
        // 1. accessToken
        String forObject = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxSubscription.getAppid() + "&secret=" + wxSubscription.getAppsecret(), String.class);
        JSONObject jsonObject = JSON.parseObject(forObject);
        String accessToken = jsonObject.getString("access_token");
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("accessToken = " + accessToken);

        // 2. jsapiTicket
        String jsapiTicketJSON = restTemplate.getForObject(String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", accessToken), String.class);
        JSONObject jsapiTicketJsonObject = JSON.parseObject(jsapiTicketJSON);
        String ticket = jsapiTicketJsonObject.getString("ticket");
        System.out.println("jsapiTicketJsonObject = " + ticket);

        // 3. 签名(完全性)
        Map<String, String> packageParams = new TreeMap<>();
        packageParams.put("timestamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("noncestr", UUID.randomUUID().toString().replaceAll("-", ""));
        packageParams.put("jsapi_ticket", ticket);
        packageParams.put("url", url);
        // timestamp=&noncestr=&jsapi_ticket=&url=
        Set<String> params = packageParams.keySet();
        StringBuffer sign = new StringBuffer();
        for (String param : params) {
            if (sign.length() > 0) {
                sign.append("&");
            }
            sign.append(param).append("=").append(packageParams.get(param));
        }
        String sha1Sign = DigestUtils.sha1Hex(sign.toString());
        System.out.println("sha1Sign = " + sha1Sign);

        // 4. 返回前端
        Map<String, String> configParams = new HashMap<>();
        configParams.put("appId", wxSubscription.getAppid());
        configParams.put("timestamp", packageParams.get("timestamp"));
        configParams.put("nonceStr", packageParams.get("noncestr"));
        configParams.put("signature", sha1Sign);
        System.out.println("configParams = " + configParams);
        return configParams;
    }


    /**
     * @return
     * @郑思根 pc端登录获取扫一扫二维码
     */
    @GetMapping("/address")
    public ResData getAuthorizedAddress() {
        String redirectUri = "https://wx.panqingshan.cn/zsg/scanlogin";
        String state = UUID.randomUUID().toString().replaceAll("-", "");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxSubscription.getAppid() + "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
        Map<String, Object> packageParams = QRGenerate.getQRGenerate(url);
        packageParams.put("state", state);
        //将生成的state保存
        AuthorizedValids authorizedValids = new AuthorizedValids();
        authorizedValids.setState(state);
        authorizedValids.setUpdateDate(System.currentTimeMillis());
        wxAuthorizedValids.getWxAuthorizedValids().put(state, authorizedValids);
        return ResData.of(0, "成功", packageParams);
    }

    /***
     * @郑思根
     * 扫一扫授权状态返回
     */
    @GetMapping("/state")
    public ResData QRState(@RequestParam("state") String state, HttpSession session) {
        AuthorizedValids authorizedValids = wxAuthorizedValids.getWxAuthorizedValids().get(state);
        System.out.println("循环遍历打印：" + authorizedValids);
        long currTime = System.currentTimeMillis();
        //如果获取不到state说明二维码已经失效，或者时间过期
        if (authorizedValids == null || currTime - authorizedValids.getUpdateDate() > validTime) {
            return ResData.ofFail(1017, "该二维码已失效，请重新获取");
        }
        //获取不到授权数据说明还没授权
        if (authorizedValids.getLinkedHashMap() == null) {
            return ResData.ofFail(1018, "待用户授权");
        }
        //如果获取到user用户信息，说明是老用户
        if (authorizedValids.getUser() != null) {
            //删除二维码
            wxUserService.arrangementQR(state, validTime);
            session.setAttribute("user", authorizedValids.getUser());
            return ResData.of(0, "老用户登录", authorizedValids.getUser());
        }
        //删除二维码
        wxUserService.arrangementQR(state, validTime);
        session.setAttribute("userInfoMap", authorizedValids.getLinkedHashMap());
        return ResData.of(1, "新用户授权", authorizedValids.getLinkedHashMap().get("headimgurl"));
    }

//    /**
//     * @param fee
//     * @return
//     * @throws IOException
//     * @郑思根 根据价格生成微信二维码NATIVE
//     */
//    @PostMapping("/qrpay")
//    public ResData createQR(@RequestParam Long fee) throws IOException {
//        if (fee <= 0 || fee == null) return ResData.ofFail(1002, "参数类型异常");
//        //获取准备数据
//        Map<String, Object> params = wxUserService.preparingData("NATIVE", fee);
//        params.put("sign", wxUserService.sign(params));
//        //获取预支付订单
//        Map<String, String> xmlMap = wxUserService.getAdvancePaymentOrder(params);
//        return ResData.of(0, "成功", QRGenerate.getSmallQRGenerate(xmlMap.get("code_url")));
//    }
//
//    /***
//     * @郑思根
//     * 用户支付JSAPI
//     * @param fee
//     * @param session
//     * @return
//     */
//    @PostMapping("/pay")
//    public ResData pay(@RequestParam Long fee, HttpSession session) throws IOException {
//        if (fee <= 0 || fee == null) return ResData.ofFail(1002, "参数类型异常");
//        //获取用户openid
//        User user = (User) session.getAttribute("user");
//        String openId = "os6oy1oI2pJyeAeGefa8Mpy0Q9k0";
////        if(user==null) return ResData.ofFail(1009,"用户失效，请重新登录");
////        String openId = user.getOpenId();
//        //获取准备数据
//        Map<String, Object> params = wxUserService.preparingData("JSAPI", fee);
//        params.put("openid", openId);
//        params.put("sign", wxUserService.sign(params));
//        //获取预支付订单
//        Map<String, String> xmlMap = wxUserService.getAdvancePaymentOrder(params);
//        // 支付配置
//        Map<String, Object> packageParams = new TreeMap<>();
//        //公众号名称，由商户传入
//        packageParams.put("appId", wxSubscription.getAppid());
//        //时间戳，自1970年以来的秒数
//        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//        //随机串
//        packageParams.put("nonceStr", UUID.randomUUID().toString().replaceAll("-", ""));
//        //预支付订单id
//        packageParams.put("package", "prepay_id=" + xmlMap.get("prepay_id"));
//        //微信签名方式：
//        packageParams.put("signType", "MD5");
//        //微信签名
//        packageParams.put("paySign", wxUserService.sign(packageParams));
//        return ResData.of(0, "成功", packageParams);
//    }
}
