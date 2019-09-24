package com.c1801.spring.dzy.service;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.mapper.InviteMapper;
import com.c1801.spring.dzy.mapper.InviteRecordMapper;
import com.c1801.spring.dzy.model.InviteRecord;
import com.c1801.spring.dzy.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * user：少
 * dateTime: 2019/9/5 10:37
 * 邀请新人
 */
@Service
@Transactional
public class InviteService {

    @Autowired
    private InviteMapper inviteMapper;

    @Autowired
    private InviteRecordMapper inviteRecordMapper;


    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 新增邀请码
     * @param userId
     * @return
     */
    public String createUserInviteCode(Integer userId){
        //邀请码
        String inviteCode = UUID.randomUUID().toString().replaceAll("-","") + "@";

        int userInviteCode = inviteMapper.createUserInviteCode(userId, inviteCode);
        if(userInviteCode == 1){
            logger.info("新增邀请码成功");
        }else {
            logger.warn("新增邀请码失败");
        }
        return inviteCode;
    }


    /**
     * 查询邀请码
     * @param userId
     * @return
     */
    public String queryUserInviteCode(Integer userId){

        return inviteMapper.queryUserInviteCode(userId);
    }


    /**
     * 新增一条邀请记录
     * @param inviteeId 被邀请人id
     * @param inviteCode 邀请码
     * @return 0 不要被邀请， 1:邀请记录新增成功
     */
    public int createInviteRecord(Integer inviteeId,String inviteCode){

        Integer inviterId = inviteMapper.queryUserByInviteCode(inviteCode);

        //被邀请人不能与邀请人相同
        //邀请人不存在 不能邀请
        if(inviterId == null || inviteeId.equals(inviterId)){
            return 0;
        }
        int result = 0;
        try{
            result = inviteRecordMapper.createInviteRecord(inviterId, inviteeId);
        //该用户已被邀请 被邀请人 数据库唯一
        }catch (Exception e){
            return  0;
        }
        return result;
    }


    /**
     * 修改邀请状态
     * @param inviteeId 被邀请人id
     * @param status 状态
     * @return
     */
    public int updateInviteStatus(Integer inviteeId, Integer status){
        return inviteRecordMapper.updateInviteStatus(inviteeId,status);
    }


    /**
     * 根据被邀请人查询邀请人id
     * @param inviteeId
     * @return
     */
    public User queryInviteRecordByInviteeId(Integer inviteeId){

        return inviteRecordMapper.queryInviteRecordByInviteeId(inviteeId);
    }


    /**
     * 查询用户的邀请记录分页
     * @param inviterId
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo queryInviteRecordByInviterId(Integer inviterId, Integer pageSize, Integer pageNum){

        PageHelper.startPage(pageNum,pageSize,"irt.create_date desc");
        Page<InviteRecord> inviteRecords = inviteRecordMapper.queryInviteRecordByInviterId(inviterId);
        PageInfo pageInfo = PageInfo.ofPageInfo(inviteRecords);
        return  pageInfo;
    }

    /**
     * 通过被邀请人查询邀请记录
     * @param inviteeId
     * @return
     */
    public InviteRecord queryInviteByInviteeId(Integer inviteeId){
        return inviteRecordMapper.queryInviteByInviteeId(inviteeId);
    }
}
