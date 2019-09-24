package com.c1801.spring.dzy.controller.user;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.config.UserValids;
import com.c1801.spring.dzy.model.Account;
import com.c1801.spring.dzy.model.ShortMessageConfiguration;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.model.ValidCode;
import com.c1801.spring.dzy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * user：少
 * dateTime: 2019/8/7 10:38
 */
@RestController
@Transactional
@RequestMapping("/dzy/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 用户验证码
     */
    @Autowired
    private UserValids userValid;


    /**
     * 登录过的用户
     */
    @Autowired
    private Map<String,Account> accountMap;

    /**
     * 验证码有效时长
     */
    private Long validTime = 180000L;

    @Autowired
    ShortMessageConfiguration smc ;

    /**
     * 用户根据手机号 和验证码登录
     * @param validCode
     * @return
     */
    @PostMapping("/login")
    public ResData getCode(@RequestBody ValidCode validCode, HttpSession session){
        logger.info("登录验证validCode = " + validCode);
        ValidCode validCode1 = userValid.getUserValidsList().get(validCode.getPhone());

        long currTime = System.currentTimeMillis();
        if(validCode1 == null || !validCode1.getCode().equals(validCode.getCode()) || currTime- validCode1.getUpdateDate() > validTime) {
            return ResData.of(1007, "验证码错误或过期，请重新获取验证码", null);
        }
        //验证通过移除验证码
        userValid.getUserValidsList().remove(validCode.getPhone());

        logger.info("登录验证后 = " + validCode1);

        User user = new User();
        user.setName(validCode.getPhone());
        Account  account = userService.userLogin(user);
        if(account.getUser().getEnabled() == 0){
            return ResData.ofFail(2003,"该账号已被封", null);
        }
        //保存登录成功用户
        accountMap.put(account.getUser().getName(),account);
        session.setAttribute("user",account.getUser());
        session.setMaxInactiveInterval(30 * 60);
        return ResData.ofSuccess(0,"登录成功", account);
    }
    /** 前端
     * 根据id查询用户
     * @param
     * @return
     */
    @GetMapping("/info")
    public ResData getUser(HttpSession session){
        User u = (User)session.getAttribute("user");
        if(u == null){
            u = new User();
        }
        logger.info("根据id获取用户信息 = " + u.getId());
        Account user = userService.getAccountById(u.getId());
        logger.info("user = " + user);
        return  ResData.ofSuccess(0, "成功", user);
    }
	
	/**
     * 退出
     * @param session
     * @return
     */
	@GetMapping("/logout")
	public Object logout(HttpSession session){
		session.removeAttribute("user");
        session.invalidate();
		return "退出成功";
	}

}
