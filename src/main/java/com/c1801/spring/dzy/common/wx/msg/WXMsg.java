package com.c1801.spring.dzy.common.wx.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user：少
 * dateTime: 2019/9/1 18:27
 * 微信消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WXMsg {

    /**
     * 消息内容
     */
    private String value;

    /**
     * 字体颜色
     */
    private String color;

    public WXMsg(String value){
        this.value = value;
    }

}
