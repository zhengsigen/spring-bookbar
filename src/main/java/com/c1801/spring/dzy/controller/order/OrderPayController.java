package com.c1801.spring.dzy.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.common.XmlHelper;
import com.c1801.spring.dzy.mapper.OrderMapper;
import com.c1801.spring.dzy.mapper.OrderPayMapper;
import com.c1801.spring.dzy.mapper.UserMapper;
import com.c1801.spring.dzy.model.*;
import com.c1801.spring.dzy.service.MessageTemplateService;
import com.c1801.spring.dzy.service.OrderService;
import com.c1801.spring.dzy.service.UserService;
import com.c1801.spring.dzy.service.WXUserService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@Transactional
@RequestMapping("/dzy/order/pay")
public class OrderPayController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    WXUserService wxUserService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderPayMapper orderPayMapper;

    @Autowired
    UserService userService;

    @Autowired
    MessageTemplateService messageTemplateService;

    @Autowired
    OrderService orderService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * @郑思根
     * 订单支付
     * @param orderPay  订单id，订单支付类型
     * @param equipment  设备类型  0、电脑 1、手机
     * @return
     */
    @PostMapping
    public ResData OrderPay(@RequestBody OrderPay orderPay, @RequestParam("equipment") Integer equipment, HttpSession session) throws IOException {
        ResData resData = new ResData();
        //订单id
        Integer orderId = orderPay.getOrderId();
        //支付类型0余额，1微信
        Integer payType = orderPay.getPayType();
        //获取用户
        User user = (User) session.getAttribute("user");
        //获取最新用户状态
        User newSetUser = userMapper.getUserById(user.getId());
        //获取到订单信息
        OrderAccount orderAccount = orderMapper.queryOrderAccountByOrderId(orderId);
        if (orderAccount == null) return ResData.ofFail(1001, "参数缺失");
        //订单总额
        Order orderTotal = orderPayMapper.queryOrderPay(orderPay.getOrderId());
        Double total = orderTotal.getOrderPay().getFinalPrice();
        if (total == null) return ResData.ofFail(1001, "参数缺失");
        //转换成分
        Long fee = (long) (total * 100);
        //如果是微信支付
        if (payType == 1) {
            //如果是手机微信支付
            if (equipment == 1) {
                //获取到JSAPI
                resData = wxUserService.pay(orderPay.getOrderId(),fee, newSetUser);
                //如果是电脑微信支付
            } else {
                //获取NATIVE
                resData = wxUserService.createQR(orderPay.getOrderId(),fee);
            }
            //如果是余额支付
        } else {
            //余额明细对象
            BalanceDetail balanceDetail = new BalanceDetail();
            //如果余额足够
            Double discountTotal = total * 0.88;
            if (newSetUser.getBalance() >= discountTotal) {
                //设置余额支付金额
                orderPay.setFeePay(discountTotal);
                balanceDetail.setFee(discountTotal);
                //扣除余额
                userMapper.BalancePayment(newSetUser.getId(), discountTotal);
                resData.setCode(0);
                resData.setDesc("余额支付成功");
                //修改订单状态
                Order order = new Order();
                order.setId(orderPay.getOrderId());
                order.setStatus(1);
                orderService.changeOrderStatus(order);
                //如果余额不足
            } else {
                //获取用户88折对应的微信价值
                Double userTotal = newSetUser.getBalance() / 0.88;
                userMapper.BalancePayment(newSetUser.getId(), newSetUser.getBalance());
                //减去余额后需要微信支付的金额
                Long reductionTotal = (long) ((total - userTotal) * 100);
                //设置余额支付金额
                orderPay.setFeePay(newSetUser.getBalance());
                balanceDetail.setFee(newSetUser.getBalance());
                //如果是手机微信支付
                if (equipment == 1) {
                    //获取到JSAPI
                    resData = wxUserService.pay(orderPay.getOrderId(),reductionTotal, newSetUser);
                    //如果是电脑微信支付
                } else {
                    //获取NATIVE
                    resData = wxUserService.createQR(orderPay.getOrderId(),reductionTotal);
                }
            }
            //新增余额消费明细
            balanceDetail.setOrderId(orderPay.getOrderId());
            balanceDetail.setUserId(newSetUser.getId());
            balanceDetail.setType(1);
            userService.addBalanceDetail(balanceDetail);
        }
        //订单支付记录
        orderPay.setPayType(0);
        orderPayMapper.orderPaymentRecord(orderPay);
        return resData;
    }


    // 支付订单
    // 订单通知
    @PostMapping("/notice")
    @ResponseBody
    public String notice(@RequestBody String xml) throws IOException {
        Map<String, String> map = XmlHelper.of(xml).toMap();
        String return_code = map.get("return_code");
        boolean bool = true;
        //判断是否是微信发送的请求
        if (StringUtils.isNotEmpty(return_code) && return_code.equals("SUCCESS")) {
            // 需要判断此值
            String result_code = map.get("result_code");
            if (StringUtils.isNotEmpty(result_code) && result_code.equals("SUCCESS")) {
                // 校验返回的sign是否一致
                bool = wxUserService.validateSign(map);
            }
        }
        Map result = new HashMap<>();
        if(bool){
            //修改订单状态
            Order order = new Order();
            order.setId(Integer.valueOf(map.get("out_trade_no")));
            order.setStatus(1);
            orderService.changeOrderStatus(order);
            //订单支付记录
            OrderPay orderPay = new OrderPay();
            orderPay.setWxPay(Double.valueOf(map.get("total_fee"))/100);
            orderPay.setOrderId(Integer.valueOf(map.get("out_trade_no")));
            orderPay.setPayType(1);
            orderPayMapper.orderPaymentRecord(orderPay);
            //设置返回数据
            result.put("return_code", "SUCCESS");
            result.put("return_msg", "OK");
            return wxUserService.toXml(result);
        }
        return wxUserService.toXml(result);
    }
}
