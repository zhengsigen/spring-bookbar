package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.BalanceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user：刘
 * dateTime: 2019/9/4 14:20
 */
@Mapper
public interface BalanceDetailMapper {

    /**
     * 查询明细
     * @param BalanceDetail
     * @return
     */
    public List<BalanceDetail> getUserById(Integer user_id);

    /**
     * 新增明细
     * @param BalanceDetail
     * @return
     */
    public int addBalanceDetail(@Param("balanceDetail") BalanceDetail balanceDetail);
}
