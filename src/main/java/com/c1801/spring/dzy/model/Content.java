package com.c1801.spring.dzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    //消息内容
    private String value;

    //消息字体颜色
    private String color;
}
