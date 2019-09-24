package com.c1801.spring.dzy.service;

import com.c1801.spring.dzy.mapper.CouponMapper;
import com.c1801.spring.dzy.mapper.FreightMapper;
import com.c1801.spring.dzy.model.Freight;
import com.c1801.spring.dzy.model.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * 黄
 * 优惠券
 */
@Service
public class FreightService {
    @Autowired
    FreightMapper freightMapper;

    /**
     * 查询地址运费信息
     * @param province
     * @return Freight  {id, province城市, charge费用, pinkage包邮门槛, is_shipments是否发货}
     */
    public Freight query(String province){
        Freight freight = freightMapper.queryByProvince(province);
        return freight;
    }

    /**
     * 查询地址运费信息
     * @param id
     * @return freight {id, province城市, charge费用, pinkage包邮门槛, is_shipments是否发货}
     */
    public Freight query(Integer id){
        Freight freight = freightMapper.queryByID(id);
        return freight;
    }
}
