package com.c1801.spring.dzy.common.email;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * user：少
 * dateTime: 2019/8/24 10:13
 * 邮件实体类
 */
@Data
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    // 必填参数
    private String[] email;// 接收方邮件数组


    private String subject;// 主题,邮件标题


    private String content;// 邮件内容文本


    // 以模板发送邮件的时候填写以下参数的值
    private String template;// 邮件模板


    private HashMap<String, Object> kvMap;// 自定义参数，用于模板发送时显示内容



    public Email() {

    }


    public Email(String[] email, String subject, String content, String template, HashMap<String, Object> kvMap) {

        this.email = email;
        this.subject = subject;
        this.content = content;
        this.template = template;
        this.kvMap = kvMap;
    }

}
