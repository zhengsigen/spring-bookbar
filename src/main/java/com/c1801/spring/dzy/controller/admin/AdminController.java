package com.c1801.spring.dzy.controller.admin;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.model.Admin;
import com.c1801.spring.dzy.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/** 后台管理
 * user：少
 * dateTime: 2019/8/7 10:38
 */
@RestController
@Transactional
@RequestMapping("/manage/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping
    public ResData addAdmin(@RequestBody @Valid Admin admin){
        logger.info("新增管理员 = " + admin);
        int result = adminService.addAdmin(admin);
        return ResData.ofSuccess(0,"成功", result);
    };

    @DeleteMapping("/{id:\\d+}")
    public ResData deleteAdmin(@PathVariable Integer id){
        logger.info("删除管理员 = " + id);
        int result = adminService.delAdmin(id);
        return ResData.ofSuccess(0, "成功", result);
    }

    @PutMapping
    public ResData updateAdmin(@RequestBody @Valid Admin admin) throws MissingServletRequestParameterException{
        logger.info("修改admin = " + admin);
        if(admin.getId() == null){
            throw new MissingServletRequestParameterException("id", "必须有用户id参数");
        }
        int result = adminService.updateAdmin(admin);
        return ResData.ofSuccess(0, "成功", result);
    }

    @GetMapping("/{id:\\d+}")
    public ResData getAdmin(@PathVariable Integer id) {
        logger.info("根据id获取 = " + id);
        Admin admin = adminService.getAdmin(id);
        return ResData.ofSuccess(0, "成功", admin);
    }


    @GetMapping
    public ResData adminList(@RequestParam(value = "name", defaultValue = "",required = false) String name
                             , @RequestParam(value = "pageSize" ,defaultValue = "5")Integer pageSize
                             , @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum
                             , @RequestParam(defaultValue = "",required = false) String minDate
                             , @RequestParam(defaultValue = "", required = false) String maxDate){
        logger.info("查询所有管理员用户 = ");

        PageInfo pageInfo = adminService.queryAdminListOfPage(name, pageSize, pageNum, minDate, maxDate);
        return ResData.ofSuccess(0, "成功", pageInfo);
    }
}
