package com.c1801.spring.dzy.controller.user;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.common.wx.qr.QRGenerate;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.model.WXSubscription;
import com.c1801.spring.dzy.service.InviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * user：少
 * dateTime: 2019/9/3 19:12
 * 邀请新用户
 */
@RestController
@RequestMapping("/dzy/invite")
public class InviteController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InviteService inviteService;

    //微信授权url
    public static final String WX_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";

    /**
     * 微信配置
     */
    @Autowired
    private WXSubscription wxSubscription;

    @PostMapping
    public ResData getInviteQr(@RequestBody Map<String,String>urlMap, HttpSession session) throws UnsupportedEncodingException {

        String url = urlMap.get("url");
        User user = (User) session.getAttribute("user");
        //域名
        String domain = wxSubscription.getDomain();

        //1.查询用户的邀请码
        String inviteCode = inviteService.queryUserInviteCode(user.getId());
        //2.如果没有则生成保存到数据库
        if(inviteCode == null){
            inviteCode = inviteService.createUserInviteCode(user.getId());
        }
        //3.拼接
        //将域名和前端路由和inviteCode连接成一个链接
        url = url + "?state="+inviteCode;
        logger.info("二维码链接" + url);
        //4.返回二维码该前端
        Map<String, Object> qrGenerate = QRGenerate.getSmallQRGenerate(url);

        return ResData.ofSuccess(0,"成功",qrGenerate);
    }


    /**
     * 查询用户的邀请记录接口
     * @param session
     * @return
     */
    @GetMapping
    public ResData queryInviteRecord(HttpSession session
            , @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize
            , @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ResData.ofFail(2002,"请登录");
        }
        PageInfo pageInfo = inviteService.queryInviteRecordByInviterId(user.getId(), pageSize, pageNum);
        return ResData.ofSuccess(0,"成功",pageInfo);
    }

}
