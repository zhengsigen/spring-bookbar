package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.OrderBookRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/11 23:08
 */
@Mapper
public interface OrderBookRecordMapper {


    public int addOrderBookRecord(@Param("orderBookRecord") List<OrderBookRecord> orderBookRecord);
}
