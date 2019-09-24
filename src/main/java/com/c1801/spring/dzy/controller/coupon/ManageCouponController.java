package com.c1801.spring.dzy.controller.coupon;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.CouponMapper;
import com.c1801.spring.dzy.model.Coupon;
import com.c1801.spring.dzy.model.UserCoupon;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/manage/coupon")
public class ManageCouponController {


    @Autowired
    public CouponMapper couponMapper;
    @GetMapping
    public ResData queryCoupon(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "5") Integer size){
        PageHelper.startPage(page,size);
        Page<Coupon> coupons = couponMapper.queryCoupon();
        PageInfo pageInfo = PageInfo.ofPageInfo(coupons);
        return ResData.ofSuccess(0,"优惠券查询完毕!",pageInfo);
    }

    @PostMapping
    public ResData addCoupon(@RequestBody Coupon coupon){
        Boolean res = couponMapper.addCoupon(coupon);
        if(res){
            return ResData.ofSuccess(0,"优惠券创建成功");
        }
        return ResData.ofSuccess(0,"优惠券创建失败！");
    }

    @GetMapping("/{id}")
    public ResData queryCouponByID(@PathVariable  Integer id){
        List <UserCoupon> coupons = couponMapper.queryCouponByID(id);
        return ResData.ofSuccess(0,"优惠券查询完毕!",coupons);
    }

    @PostMapping("/send")
    public ResData addCoupon(@RequestBody UserCoupon coupon){
        couponMapper.sendCoupon(coupon);
        return ResData.ofSuccess(0,"优惠券创建成功！");
    }

    @PutMapping()
    public ResData updateAmount(@RequestParam("id")Integer id,@RequestParam("expiresIn") Integer expiresIn){
        couponMapper.updateExpiresIn(id,expiresIn);
        return ResData.ofSuccess(0,"优惠券修改成功！");
    }
}
