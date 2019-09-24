package com.c1801.spring.dzy.controller.user;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.UserMapper;
import com.c1801.spring.dzy.model.Account;
import com.c1801.spring.dzy.model.StatisticUser;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.service.UserService;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * user：少
 * dateTime: 2019/8/7 10:38
 */
@RestController
@Transactional
@RequestMapping("/manage/user")
public class ManageUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** 后台管理
     * 根据条件获取所有用户信息
     * @param name 用户名
     * @param pageSize 每页的数量
     * @param pageNum 当前页
     * @param minDate 最小时间
     * @param maxDate 最大时间
     * @return
     */
    @GetMapping
    public ResData getUserList(@RequestParam(value = "name", defaultValue = "") String name
            , @RequestParam(value = "pageSize" ,defaultValue = "5")Integer pageSize
            , @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum
            , @RequestParam(defaultValue = "") String minDate
            , @RequestParam(defaultValue = "") String maxDate) {

        PageInfo pageInfo = userService.getUserListOfPage(name, pageSize, pageNum, minDate, maxDate);
        return  ResData.of(0, "成功", pageInfo);
    }
    /** 后台管理
     * 根据id查询用户
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public ResData getUser(@PathVariable Integer id){
        Account user = userService.getAccountById(id);
        return  ResData.ofSuccess(0, "成功", user);
    }


    /** 后台管理
     * 根据一组用户id查询用户名
     * @param userIds
     * @return
     */
    @GetMapping("/list")
    public ResData getUserListByIds(@RequestParam("userId") List<Integer> userIds){
        List<User> userList = userService.getUserListByUserIds(userIds);
        return ResData.ofSuccess(0, "成功", userList);
    }
    /** 后台管理
     * 根据用户名查询id
     * @param name
     * @return
     */
    @GetMapping("/list/{name}")
    public ResData getUserListByName(@PathVariable String name){
        List<User> users = userMapper.queryUserList(name);
        return ResData.ofSuccess(0, "成功", users);
    }

    /** 后台管理
     * 根据用户id禁用用户
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResData getUserByState(@PathVariable("id") Integer id){
        User user =userMapper.getUserById(id);
        if(user.getEnabled() == 0){
            user=userMapper.updateUserByState(id,1);
        }else{
            user=userMapper.updateUserByState(id,0);
        }
        return ResData.ofSuccess(0, "成功", user);
    }

    /** 后台管理
     * 统计最近七天创建用户数量
     * @param minDate
     * @param maxDate
     * @return
     */
    @GetMapping("/create/statistic")
    public ResData getStatisticUserByLoginDate(@RequestParam(defaultValue = "") String minDate, @RequestParam(defaultValue = "") String maxDate){
        List<StatisticUser> statisticUser = userService.getStatisticUserByCreaeteDate(minDate, maxDate);
        return ResData.ofSuccess(0,"成功", statisticUser);
    }

}
