package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.Cart;
import com.c1801.spring.dzy.model.OrderBookRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderBookMapper {
    //为指定订单添加书籍
    public void addOrderBook(@Param("orderId")Integer orderId, @Param("bookId")List<Integer>bookId);
    //查询指定订单书籍
    public List<Book> queryOrderBookByOrderId(Integer orderId);

    /**
     * 新增买书订单书籍记录
     * @param orderId 订单id
     * @param carts 订单中的购物车商品
     * @return
     */
    public int addPurchaseOrderBook(@Param("orderId")Integer orderId, @Param("carts")List<Cart> carts);

    /**
     * 通过订单id查询订单中的书
     * 退库存 和 重新添加到购物车 用到
     * @param orderId
     * @return
     */
    public List<OrderBookRecord> queryPurchaseOrderBookByOrderId(Integer orderId);
}
