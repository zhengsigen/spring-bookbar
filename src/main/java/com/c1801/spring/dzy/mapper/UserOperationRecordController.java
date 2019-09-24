package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserOperationRecordController {
    public Category IsUserHasCategory(@Param("userId")Integer userId, @Param("categoryId")Integer categoryId);
    public void UserRecordAdd(@Param("userId")Integer userId,@Param("categoryId")Integer categoryId);
}
