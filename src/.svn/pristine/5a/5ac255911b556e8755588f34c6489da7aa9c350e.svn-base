package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Account;
import com.c1801.spring.dzy.model.StatisticUser;
import com.c1801.spring.dzy.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/6 19:20
 */
@Mapper
public interface UserMapper {

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User getUser(@Param("user") User user);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    public int addUser(User user);


    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    public User queryUserByName(String name);


    /**
     * 修改用户余额
     * @param id
     * @param total
     */
    public int updateUserBalance(@Param("id") Integer id,@Param("total") Double total);

    /**
     * 用户余额付款
     * @param id
     * @param total
     */
    public void BalancePayment(@Param("id") Integer id,@Param("total") Double total);


    /**
     * 给用户绑定一个地址
     * @param addrId
     * @param userId
     */
    public void updateUserAddr(@Param("addrId")Integer addrId,@Param("userId")Integer userId);


    /**
     * 更新用户最后登录时间
     * @param user
     * @return
     */
    public int updateLoginDate(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public User getUserById(Integer id);

    /**
     * 根据用户id查询用户账户信息 用户基本信息 和地址
     * @param id
     * @return
     */
    public Account getAccount(Integer id);


    /**
     * 根据一组用户id查询用户名
     * @return
     */
    public List<User> getUserListByUserIds(List<Integer> userIds);

    /**
     * 根据用户名来查到用户-黄
     * @param name
     * @return
     */
    public List<User> queryUserList(@Param("name") String name);
   /*
    *  根据用户id修改禁用用户
    */
    public User updateUserByState(Integer id, Integer enabled);

	
    /**
     * 查询某个时间段每天新增用户数量
     * @param minCreateDate
     * @param maxLoginDate
     * @return
     */
    public List<StatisticUser> statisticUserByCreaeteDate(@Param("minCreateDate") String minCreateDate, @Param("maxCreateDate") String maxLoginDate);


    /**
     * 查询用户分页
     * @return
     */
    public Page<User> queryUserListOfPage(@Param("name") String name, @Param("minDate") String minDate, @Param("maxDate") String maxDate);


    /**
     * 查询这个用户的状态
     * @return 1=正常 0=禁用
     */
    public Integer queryUserEnabled(@Param("id")Integer id);


//    ------------------------微信切割线-------------------------

    /**
     * 根据手机号码获取用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);

    /**
     * //根据openid查询用户
     * @param openId
     * @return
     */
    public User queryUserByOpenId(String openId);

    /**
     * //新增微信用户
     * @param user
     */
    public void addWXUser(User user);

    /**
     * 微信用户绑定手机用户
     * @param user
     */
    public Integer UserBinding(@Param("user") User user,@Param("id") Integer id);
}
