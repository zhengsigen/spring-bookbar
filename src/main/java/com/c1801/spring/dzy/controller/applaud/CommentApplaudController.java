package com.c1801.spring.dzy.controller.applaud;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.CommentApplaudMapper;
import com.c1801.spring.dzy.mapper.CommentMapper;
import com.c1801.spring.dzy.mapper.UserMapper;
import com.c1801.spring.dzy.mapper.UserSubscribeMapper;
import com.c1801.spring.dzy.model.BookComment;
import com.c1801.spring.dzy.model.Comment;
import com.c1801.spring.dzy.model.User;
import com.c1801.spring.dzy.model.UserSubscribe;
import com.c1801.spring.dzy.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@Transactional
@RequestMapping("/dzy/comment/applaud")
public class CommentApplaudController {
    /**
     * 评论点赞
     */
    @Autowired
    private CommentApplaudMapper commentApplaudMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSubscribeMapper userSubscribeMapper;
    @Autowired
    private MessageTemplateService messageTemplateService;

    @PostMapping
    public ResData applaudOrCancle(@RequestParam("commentId") Integer commentId, HttpSession session) throws IOException {
        User u = (User) session.getAttribute("user");
        if (u == null) {
            u = new User();
            return ResData.ofFail(2002, "用户没有登录", null);
        }
        System.out.println("评论id：" + commentId);
        System.out.println("用户id：" + u.getId());
        Integer num = commentApplaudMapper.cancleApplaud(commentId, u.getId());
        if (num == 1) {
            ResData resData = new ResData();
            resData.setCode(0);
            resData.setData("成功");
            return resData;
        }
        //消息推送
        Comment comment = commentMapper.getCommentById(commentId);
        UserSubscribe userSubscribe = userSubscribeMapper.getUserSubscribe(comment.getUserId());
        if (userSubscribe.getAgree()) {
            User user = userMapper.getUserById(comment.getUserId());
            messageTemplateService.sendDynamic(user, u, null, comment.getBookId(), commentId);
        }

        commentApplaudMapper.addApplaud(commentId, u.getId());
        ResData resData = new ResData();
        resData.setCode(0);
        resData.setData("成功");
        return resData;
    }
}
