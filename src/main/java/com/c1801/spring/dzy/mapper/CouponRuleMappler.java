package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.CouponRule;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface CouponRuleMappler {
    /**
     * 查询
     * @return
     */
    public Page<CouponRule> queryCouponRule();

    /**
     * 修改
     * @param rule
     * @return
     */
    public boolean updateCouponRule(@Param("rule") CouponRule rule);

    /**
     * 添加
     * @param rule
     * @return
     */
    public boolean addCouponRule(@Param("rule") CouponRule rule);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCouponRule(@Param("id") Integer id);
}
