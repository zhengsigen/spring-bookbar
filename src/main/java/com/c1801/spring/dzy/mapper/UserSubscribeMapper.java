package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.UserSubscribe;

/**
 * 用户订阅
 */
public interface UserSubscribeMapper {
    //新增一个用户
    public void addUserSubscribe(Integer userId);

    //修改用户订阅
    public Integer updateUserSubscribe(UserSubscribe userSubscribe);

    //根据用户id查询用户订阅
    public UserSubscribe getUserSubscribe(Integer userId);
}
