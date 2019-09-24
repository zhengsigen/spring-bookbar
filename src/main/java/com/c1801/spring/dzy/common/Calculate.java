package com.c1801.spring.dzy.common;

import java.math.BigDecimal;

/**
 * user：少
 * dateTime: 2019/8/13 16:04
 * 价格计算
 */
public class Calculate {

    /**
     * v 保留scale位小数四舍五入
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 两个数相乘
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
}
