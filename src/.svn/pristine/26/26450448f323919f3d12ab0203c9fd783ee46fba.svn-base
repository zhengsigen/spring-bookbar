package com.c1801.spring.dzy.controller.orderbook;


import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.OrderBookMapper;
import com.c1801.spring.dzy.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/dzy/orderbook")
public class OrderBooksController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    //订单书籍链表添加
    @Autowired
    OrderBookMapper orderBookMapper;

    //获取指定订单所有书籍
    @GetMapping("/{orderId}")
    public ResData queryOrderBookByOrderId(@PathVariable("orderId") Integer orderId){
        List<Book> bookList = orderBookMapper.queryOrderBookByOrderId(orderId);
        logger.info("订单书籍列表bookList = " + bookList);
        return ResData.of(0,"查询成功",bookList);
    }
}
