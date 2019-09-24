package com.c1801.spring.dzy.config;

import com.c1801.spring.dzy.common.email.Email;
import com.c1801.spring.dzy.common.email.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * user：少
 * dateTime: 2019/8/24 18:18
 * 异常注入邮件模板
 */
@Configuration
public class ExceptionEmial {


    @Autowired
    private IMailService mailService;

    @Value("${alarm.email}")
    private String[] email;


    /**
     * 异常信息注入邮件模板
     * @param e
     */
    public void exceptionEmial(Exception e){

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));

        Email mail = new Email();
        mail.setEmail(email);
        mail.setSubject("异常告警邮件通知");
        mail.setContent(stringWriter.toString());
        // mailService.send(mail);//发送普通邮件
        mail.setTemplate("notifyEmail.ftl");

        //自定义模板参数，用于在ftl中接收展示
        HashMap<String, Object> mapParam = new HashMap<>();
        mapParam.put("exceptionCause", e.getCause());
        mapParam.put("exceptionMessage", e.getMessage());
        mapParam.put("exceptionClass", e.getClass());
        mail.setKvMap(mapParam);

        //发送模板邮件
        mailService.sendFreemarker(mail);

    }

}
