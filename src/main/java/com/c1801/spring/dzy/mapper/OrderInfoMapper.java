package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Order;
import com.c1801.spring.dzy.model.OrderAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * user：少
 * dateTime: 2019/9/7 21:37
 * 订单信息数据库接口
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    public Order queryOrderByOrderId(Integer orderId);

    /**
     * 通过订单id查询用户订单详情信息
     * @param orderId
     * @return
     */
    public OrderAccount queryOrderAccountByOrderId(Integer orderId);

}
