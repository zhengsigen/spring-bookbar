package com.c1801.spring.dzy.common.auto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * user：少
 * dateTime: 2019/9/8 18:55
 * 未支付订单队列
 */
@Data
@NoArgsConstructor
public class OrderItem<T> implements Delayed {

    //过期时间
    private long exprieTime;

    private T data;


    public OrderItem(long exprieTime, T data){
        super();
        //过期时间 = 过期时长 + 当前时间
        this.exprieTime = exprieTime + System.currentTimeMillis();
        this.data = data;
    }


    /**
     * 返回到激活日期的剩余时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long time = unit.convert(this.exprieTime-System.currentTimeMillis(),unit);
        return time;
    }

    /**
     * 将订单按到期时间排序 剩余时间越少的排在队列的头部
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        //getDelay(TimeUnit.NANOSECONDS ) 新加入的元素到激活日期的剩余时间
        //o.getDelay(TimeUnit.NANOSECONDS ) 当前元素到激活日期的剩余时间
        long time = getDelay(TimeUnit.NANOSECONDS )- o.getDelay(TimeUnit.NANOSECONDS );
        int result = time == 0 ? 0 : (time < 0 ? -1 : 1);
        return result;
    }
}
