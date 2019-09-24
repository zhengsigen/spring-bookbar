package com.c1801.spring.dzy.common;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

/**
 * 模板
 * httpUrlConnection访问远程接口工具
 */
public class PhoneShortMessage {
    public static String getVerCode(String phone){
        //region-id是您正在使用的地域ID。你可以通过调用DescribeRegions接口查看地域ID。
        //您的AccessKey ID/AccessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIOqylE0uPw04E", "21CV9yJT5A6TMu8yRSD52sRQjXoy2Z");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//域名(domain)：该产品的服务地址。
        request.setVersion("2017-05-25");//API版本(version)：该API的版本号，格式为YYYY-MM-DD。
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");//地域id
        request.putQueryParameter("PhoneNumbers", phone);//要发送信息的手机号码
        request.putQueryParameter("SignName", "博远");//签名名称
        request.putQueryParameter("TemplateCode", "SMS_171859589");//模版CODE
        String VerCode = getMsgCode();
        request.putQueryParameter("TemplateParam", "{\"code\":\""+VerCode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            int status = response.getHttpStatus();
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return VerCode;
    }
    /**
     * 生成随机的6位数，短信验证码
     * @return
     */
    public static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}