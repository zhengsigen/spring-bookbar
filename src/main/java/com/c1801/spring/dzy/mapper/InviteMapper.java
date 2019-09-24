package com.c1801.spring.dzy.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * user：少
 * dateTime: 2019/9/5 10:27
 * 邀请数据库接口
 */
@Mapper
public interface InviteMapper {

    /**
     * 查询用户的邀请码
     * @param userId
     * @return
     */
    public String queryUserInviteCode(Integer userId);


    /**
     * 通过邀请码查询用户id
     * @param inviteCode
     * @return
     */
    public Integer queryUserByInviteCode(String inviteCode);


    /**
     * 创建用户的邀请码
     * @param userId
     * @return
     */
    public int createUserInviteCode(Integer userId,String inviteCode);



}
