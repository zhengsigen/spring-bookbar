package com.c1801.spring.dzy.mapper;


import com.c1801.spring.dzy.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * user：黄
 * dateTime: 2019/8/26 11:16
 */
@Mapper
public interface ApplaudMapper {

    /**
     * 点赞
     * @param recommendId
     * @param userId
     * @return
     */
    public boolean applaudBook(@Param("recommendId")Integer recommendId, @Param("userId")Integer userId);


    /**
     * 取消点赞
     * @param recommendId
     * @param userId
     * @return
     */
    public boolean cancelApplaudBook(@Param("recommendId") Integer recommendId, @Param("userId")Integer userId);


}
