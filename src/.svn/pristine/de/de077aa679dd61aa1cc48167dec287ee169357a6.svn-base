package com.c1801.spring.dzy.controller.order;

import com.c1801.spring.dzy.common.Calculate;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.*;
import com.c1801.spring.dzy.model.*;
import com.c1801.spring.dzy.service.BookService;
import com.c1801.spring.dzy.service.MessageTemplateService;
import com.c1801.spring.dzy.service.OrderService;
import com.c1801.spring.dzy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/manage/order")
public class ManageOrderController {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SellBookMapper sellBookMapper;
    @Autowired
    AddrMapper addrMapper;
    @Autowired
    OrderBookMapper orderBookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookService bookService;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    MessageTemplateService messageTemplateService;
    @Autowired
    UserSubscribeMapper userSubscribeMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 后台管理
     *
     * @param orderId
     * @param status
     * @return
     */
    //修改订单状态
    @PutMapping
    public ResData cancelOrder(@RequestBody Order order) throws IOException {
        return orderService.changeOrderStatus(order);
    }

    //根据订单id查询订单
    @GetMapping("/{orderId}")
    public ResData queryOrderAccountByOrderId(@PathVariable("orderId") Integer orderId) {
        OrderAccount orderAccount = orderMapper.queryOrderAccountByOrderId(orderId);
        logger.info("订单书籍列表orderAccount order = " + orderAccount);
        return ResData.of(0, "查询成功", orderAccount);
    }


    /**
     * 后台管理
     *
     * @param orderBookRecords
     * @return
     */
    //订单校对
    @PutMapping("/proofread")
    public ResData proofreadingOrder(@RequestBody List<OrderBookRecord> orderBookRecords) throws IOException {
        if (orderBookRecords.size() == 0) {
            return ResData.ofSuccess(0, "当前订单没有书籍");
        }
        //获取订单根据品相的价格
        Double total = 0.0;
        for (OrderBookRecord obr : orderBookRecords) {
            Book book = bookMapper.queryBookById(obr.getBookId());
            Double condition = obr.getCondition() == 0 ? 0.3 : obr.getCondition() == 1 ? 0.25 : 0;
            double mul = Calculate.mul(book.getPrice(), condition);
            double round = Calculate.round(mul, 2);
            total += round;
        }
        //修改订单价格
        orderMapper.updateOrderTotal(total, orderBookRecords.get(0).getOrderId());
        //修改订单状态
        orderMapper.cancelOrder(orderBookRecords.get(0).getOrderId(), 5);
        //订单记录
        bookService.addOrderBookRecordMapper(orderBookRecords);

        //消息推送
        UserSubscribe userSubscribe = userSubscribeMapper.getUserSubscribe(orderBookRecords.get(0).getUserId());
        if (userSubscribe.getOrderStatus()) {
            OrderAccount order = orderMapper.queryOrderAccountByOrderId(orderBookRecords.get(0).getOrderId());
            User user = userMapper.getUserById(order.getUserId());
            OrderAccount temp = orderMapper.queryOrderAccountByOrderId(orderBookRecords.get(0).getOrderId());
            messageTemplateService.sendOrderStatus(user, temp);
        }
        return ResData.ofSuccess(0, "校对完成");
    }


    /**
     * 后台管理
     *
     * @param orderBookRecords
     * @return
     */
    //订单结算
    @PutMapping("/settlement")
    public ResData settlementOrder(@RequestBody List<OrderBookRecord> orderBookRecords) throws IOException {
        if (orderBookRecords.size() == 0) {
            return ResData.ofSuccess(0, "当前订单没有书籍");
        }
        //获取订单
        OrderAccount orderAccount = orderMapper.queryOrderAccountByOrderId(orderBookRecords.get(0).getOrderId());
        //修改用户余额
        userMapper.updateUserBalance(orderBookRecords.get(0).getUserId(), orderAccount.getTotal());
        BalanceDetail detail = new BalanceDetail();
        detail.setFee(orderAccount.getTotal());
        detail.setUserId(orderAccount.getUserId());
        detail.setOrderId(orderAccount.getId());
        detail.setType(orderAccount.getType());
        userService.addBalanceDetail(detail);
        //修改订单状态
        orderMapper.cancelOrder(orderBookRecords.get(0).getOrderId(), 6);
        //不合格不入库
        for (int i = 0; i < orderBookRecords.size(); i++) {
            if (orderBookRecords.get(i).getCondition() == 2) {
                orderBookRecords.remove(i);
                i--;
            }
        }
        //书籍入库
        bookService.addStock(orderBookRecords);
        //消息推送
        //消息推送
        UserSubscribe userSubscribe = userSubscribeMapper.getUserSubscribe(orderBookRecords.get(0).getUserId());
        if (userSubscribe.getOrderStatus()) {
            OrderAccount order = orderMapper.queryOrderAccountByOrderId(orderBookRecords.get(0).getOrderId());
            User user = userMapper.getUserById(order.getUserId());
            OrderAccount temp = orderMapper.queryOrderAccountByOrderId(orderBookRecords.get(0).getOrderId());
            messageTemplateService.sendOrderStatus(user, temp);
        }
        return ResData.ofSuccess(0, "结算完成");
    }


    //一个月内订单成功数量，用户数量，收书数量
    @GetMapping("/date")
    public ResData queryOrderListInDate() {
        return ResData.of(0, "成功", orderMapper.queryOrderListInDate());
    }


    //指定时间内，指定用户，所有订单 条件查询
    @GetMapping
    public ResData querOrderListInSection(@RequestParam(value = "start", required = false) String start,
                                          @RequestParam(value = "end", required = false) String end,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                          @RequestParam(value = "totalPriceStatus", defaultValue = "", required = false) Integer totalPriceStatus,
                                          @RequestParam(value = "totalStatus", defaultValue = "", required = false) Integer totalStatus) {
        Integer index = (page - 1) * pageSize;
        List<OrderAccount> total = orderMapper.querOrderListInSectionTotal(start, end, name);

        List<OrderAccount> orderAccounts = orderMapper.querOrderListInSection(start, end, name, index, pageSize, totalPriceStatus, totalStatus);

        Pager pager = new Pager();
        pager.setTotal(total.size());
        pager.setData(orderAccounts);
        System.out.println("totalPriceStatus : " + totalPriceStatus);

        System.out.println("totalStatus:" + totalStatus);
        return ResData.of(0, "成功", pager);
    }


    //指定时间内，指定用户所有已完成订单
    @GetMapping("/list/section/complete")
    public ResData queryOrderListInSectionAndComplete(@RequestParam(value = "start", required = false) String start,
                                                      @RequestParam(value = "end", required = false) String end,
                                                      @RequestParam(value = "name", required = false) String name,
                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Integer index = (page - 1) * pageSize;
        List<OrderAccount> total = orderMapper.queryOrderListInSectionAndCompleteTotal(start, end, name);
        List<OrderAccount> orderAccounts = orderMapper.queryOrderListInSectionAndComplete(start, end, name, index, pageSize);
        Pager pager = new Pager();
        pager.setTotal(total.size());
        pager.setData(orderAccounts);
        return ResData.of(0, "成功", pager);
    }
}
