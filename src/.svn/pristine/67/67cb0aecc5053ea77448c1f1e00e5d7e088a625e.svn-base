package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Admin;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/7 20:23
 */
@Mapper
public interface AdminMapper {

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    public int addAdmin(@Param("admin") Admin admin);

    /**
     * 删除管理员用户
     * @param id
     * @return
     */
    public int delAdmin(Integer id);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    public int updateAdmin(@Param("admin") Admin admin);

    /**
     * 管理员用户根据用户名密码登录
     * @param username
     * @param password
     * @return
     */
    Admin adminLogin(@Param("name")String username, @Param("password")String password);

    /**
     * 根据id 查询管理员用户
     * @param id
     * @return
     */
    public Admin getAdminById(Integer id);

    /**
     * 条件查询管理员用户分页
     * @param name
     * @param minDate
     * @param maxDate
     * @return
     */
    public Page<Admin> queryAdminListOfPage(@Param("name") String name, @Param("minDate") String minDate, @Param("maxDate") String maxDate);

}
