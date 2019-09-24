package com.c1801.spring.dzy.common.auto;

import com.c1801.spring.dzy.model.Order;
import com.c1801.spring.dzy.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * user：少
 * dateTime: 2019/9/8 20:43
 * 处理未支付订单
 */
@Service
public class DelayOrder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    private Order orderImp;

    //检测弹出到期订单的线程
    private Thread takeOrder;

    private static DelayQueue<OrderItem<Order>> delayQueue = new DelayQueue<>();

    /**
     * 将未支付订单加入延时队列
     * @param order 未支付订单
     * @param expireTime 到期时长
     */
    public void orderDelay(Order order, long expireTime){
        OrderItem<Order> orderOrderItem = new OrderItem<>(expireTime, order);
        delayQueue.put(orderOrderItem);
        logger.info("订单超时时长：【" + expireTime + "毫秒】\t订单详情： = " + order);
    }

    /**
     * 管理未支付订单线程
     */
    private class TakeOrder implements Runnable {

        private Order order;
        public TakeOrder(Order order){
            super();
            this.order = order;
        }

        @Override
        public void run() {
            logger.info("处理到期订单线程已启动");
            while (!Thread.currentThread().isInterrupted()){
                try{
                    //take() 是阻塞方法，直到从延迟队列中获取到元素才会继续往下执行
                    OrderItem<Order> itemOrder = delayQueue.take();
                    if(itemOrder != null){
                        logger.info("处理超时订单："+ itemOrder.getData());
                        //执行自动取消订单操作
                        orderService.autoCanelOfOrder(itemOrder.getData());
                    }

                }catch (Exception e){
                    logger.info("The thread :" + e);
                }
            }
            logger.info("处理到期订单线程准备关闭。。。。");
        }

    }

    /**
     * spring IOC 容器初始化完成后调用，只调用一次
     */
    @PostConstruct
    public void init(){

        takeOrder = new Thread(new TakeOrder(orderImp));
        takeOrder.start();
        logger.info("容器启动");

    }

    /*
    统重启后，DelayQueue中的数据就被清空了，
    因此当系统重新启动的时候，需要在订单的实现类中去做一个检索数据库订单的操作，
    将已过期未支付的设置为已过期，将未过期未支付的重新推入DelayQueue队列中。
     */
    @PostConstruct
    public void initDelayOrder() {
        logger.info("系统启动，查询出未支付的订单。。。。。");
        //查询数据库未支付订单
        List<Order> orders = orderService.queryNoPayOrder();

        logger.info("系统启动，还有" + orders.size()+ " 个未到期未付款的订单" );
        if(orders != null && orders.size() > 0){
            orders.forEach(order -> {
                long expireTime = order.getOrderPay().getAutoConfirmTime();
                orderDelay(order,expireTime);
            });
        }
    }



    /**
     * spring IOC 容器销毁之前调用，只调用一次
     */
    @PreDestroy
    public void close(){
        takeOrder.interrupt();
    }



}
