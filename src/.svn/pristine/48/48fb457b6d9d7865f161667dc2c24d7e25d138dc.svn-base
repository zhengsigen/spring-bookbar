package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Order;
import com.c1801.spring.dzy.model.OrderPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderPayMapper {

    /**
     * 生成订单支付记录
     * @param orderPay
     * @return
     */
    public int addOrderPayRecord(OrderPay orderPay);

    //订单支付时修改记录
    public void orderPaymentRecord(OrderPay orderPay);

    /**
     * 订单查询支付信息
     * 退款
     * @param orderId
     * @return
     */
    public Order queryOrderPay(Integer orderId);


    /**
     * 查询未支付订单
     * @return
     */
    public List<Order> queryNonPaymentOrder();

}
