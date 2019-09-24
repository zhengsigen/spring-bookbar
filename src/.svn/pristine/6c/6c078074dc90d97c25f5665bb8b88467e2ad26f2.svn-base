package com.c1801.spring.dzy.controller.order;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.AddrMapper;
import com.c1801.spring.dzy.mapper.UserMapper;
import com.c1801.spring.dzy.model.Address;
import com.c1801.spring.dzy.model.Order;
import com.c1801.spring.dzy.model.OrderParam;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.service.OrderService;
import com.c1801.spring.dzy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * user：少
 * dateTime: 2019/8/30 11:06
 * 用户买书订单
 */
@RestController
@RequestMapping("/dzy/purchase")
@Transactional
public class PurchaseOrderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AddrMapper addrMapper;

    @Autowired
    private UserMapper userMapper;
    
    
    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     * @param orderParam
     * @param session
     * @return
     */
    @PostMapping
    public ResData createOrder(@RequestBody @Valid OrderParam orderParam, HttpSession session){
        logger.info(orderParam.toString());

        User user = (User) session.getAttribute("user");
        //1.订单生成
        Integer orderId = orderService.purchaseOrder(orderParam, user.getId());
        //2.更新用户地址
        
        //获取用户
        user = userMapper.getUserById(user.getId());

        //订单地址
        Address addr = orderParam.getAddress();

        //如果用户没有地址
        if (user.getAddrId() == null) {
            logger.info("新增地址");
            //新增用户地址
            addrMapper.addAddr(addr);
            //绑定用户地址
            userMapper.updateUserAddr(addr.getId(), user.getId());
        } else {
            logger.info("修改地址");
            //修改用户收货地址
            addrMapper.updateAddr(addr, user.getAddrId());
        }

        return ResData.ofSuccess(0, "成功",orderId);
    }



    /**
     * 取消订单
     * @param order
     * @return
     */
    @PatchMapping
    public ResData updateOrderStatus(@RequestBody Map<String,Integer> order, HttpSession session){
        Integer orderId = order.get("orderId");
        return ResData.ofSuccess(0, "成功");
    }




}
