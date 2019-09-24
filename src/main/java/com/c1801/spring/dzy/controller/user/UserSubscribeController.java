package com.c1801.spring.dzy.controller.user;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.UserSubscribeMapper;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.model.UserSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Transactional
@RequestMapping("/dzy/user/subscribe")
public class UserSubscribeController {

    @Autowired
    UserSubscribeMapper userSubscribeMapper;

    /**
     * @return
     * @郑思根 根据用户id 获取用户订阅
     */
    @GetMapping
    public ResData getUserSubscribeById(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResData.ofFail(2002, "请登录");
        UserSubscribe userSubscribe = userSubscribeMapper.getUserSubscribe(user.getId());
        return ResData.of(0, "成功", userSubscribe);
    }

    /**
     * @return
     * @郑思根 新增用户订阅
     */
    @PostMapping
    public ResData addUserSubscribe(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userSubscribeMapper.addUserSubscribe(user.getId());
        return ResData.ofSuccess(0, "成功");
    }

    /**
     * @param userSubscribe
     * @return
     * @郑思根 用户修改订阅
     */
    @PutMapping
    public ResData updateUserSubscribe(@RequestBody UserSubscribe userSubscribe, HttpSession session) {
        User user = (User) session.getAttribute("user");
        userSubscribe.setUserId(user.getId());
        userSubscribeMapper.updateUserSubscribe(userSubscribe);
        return ResData.ofSuccess(0, "成功");
    }

}
