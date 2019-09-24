package com.c1801.spring.dzy.service;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.mapper.AdminMapper;
import com.c1801.spring.dzy.model.Admin;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/7 20:21
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 新增管理员用户
     * @param admin
     * @return
     */
    public int addAdmin(Admin admin){

        return adminMapper.addAdmin(admin);
    }

    /**
     * 删除管理员用户
     * @param id
     * @return
     */
    public int delAdmin(Integer id){
        return adminMapper.delAdmin(id);
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    public int updateAdmin(Admin admin){

        return adminMapper.updateAdmin(admin);
    }

    /**
     * 根据id查询单个管理员
     * @param id
     * @return
     */
    public Admin getAdmin(Integer id){
        return adminMapper.getAdminById(id);
    }

    /**
     * 条件查询管理员
     * @param admin
     * @return
     */
    public PageInfo queryAdminListOfPage(String name, Integer pageSize, Integer pageNum, String minDate, String maxDate){

        PageHelper.startPage(pageNum, pageSize);
        Page<Admin> adminList = adminMapper.queryAdminListOfPage(name, minDate, maxDate);

        PageInfo pageInfo = PageInfo.ofPageInfo(adminList);

        return  pageInfo;

    }
}
