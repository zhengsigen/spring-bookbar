package com.c1801.spring.dzy.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Configuration
public class RandomIdCreate {
    /**
     * 用户随机id集合
     */
    private List<Integer> userId = new ArrayList<>();

    /**
     * 订单随机id集合
     */
    private List<Integer> orderId = new ArrayList<>();

    /**
     * 随机数
     */
    private Random random = new Random();

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void postConstruct() {
        addOrderId();
        addUserId();
    }

    /**
     * 随机生成一百个用户id，String截取九位数再加上十亿内随机数
     */
    public void addUserId(){
        for (int i = 0; i < 100; i++) {
            String id = String.valueOf(System.currentTimeMillis());
            Integer temp = Integer.valueOf(id.substring(3,id.length()-1))+random.nextInt(1000000000);
            userId.add(temp);
        }
    }
    /**
     * 随机生成一百个订单id，String截取九位数再加上十亿内随机数
     */
    public void addOrderId(){
        for (int i = 0; i < 100; i++) {
            String id = String.valueOf(System.currentTimeMillis());
            Integer temp = Integer.valueOf(id.substring(3,id.length()-1))+random.nextInt(1000000000);
            orderId.add(temp);
        }
    }

    /**
     * 调用获取用户id，并判断用户id是否少于十个
     * @return
     */
    public Integer getNewUserId(){
        Integer id = userId.get(0);
        userId.remove(0);
        if (userId.size() < 10)addUserId();
        return id;
    }

    /**
     * 调用获取订单id，并判断订单id是否少于十个
     * @return
     */
    public Integer getNewOrderId(){
        Integer id = orderId.get(0);
        orderId.remove(0);
        if (orderId.size() < 10)addOrderId();
        return id;
    }
}
