package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Cart;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/26 22:11
 * 购物车数据库接口
 */
@Mapper
public interface CartMapper {


    /**
     * 添加商品到购物车
     * @param bookId
     * @param condition
     * @param userId
     * @return
     */
        public int addShopToCart(@Param("bookId") Integer bookId,@Param("condition") Integer condition, @Param("userId") Integer userId);


    /**
     * 根据购物车id和用户id删除购物车
     * @param cartId
     * @param userId
     * @return
     */
    public int delShopFromCart(@Param("cartId") Integer cartId, @Param("userId") Integer userId);


    /**
     * 批量删除结算商品
     * @param cartIds
     * @param userId
     * @return
     */
    public int delInOrderShopFromCart(@Param("cartIds") List<Integer> cartIds, @Param("userId")Integer userId);


    /**
     * 修改购物车商品品相
     * @param cart
     * @return
     */
    public int updateConditoinOfCart(@Param("cart") Cart cart);


    /**
     * 修改是否选中
     * @param cartId
     * @return
     */
    public int updateSelectedCart(@Param("cartId") Integer cartId, @Param("userId") Integer userId);


    /**
     * 批量修改是否选中
     * @param cartIds
     * @param selected
     * @param userId
     * @return
     */
    public int updateSelectedCarts(@Param("list") List<Integer> cartIds, @Param("selected") Integer selected, @Param("userId") Integer userId);


    /**
     * 修改 购物车是否在订单中的状态
     * @param cartIds 要修改的购物车id
     * @param inOrder 修改后的状态
     * @param userId 要修改的用户id
     * @return
     */
    public int updateInOrderOfCartsAndStock(@Param("list") List<Integer> cartIds, @Param("inOrder")Integer inOrder, @Param("userId")Integer userId);


    /**
     * 查询书籍是否在用户购物车中
     * @param bookId
     * @return
     */
    public Cart queryBookInCart(@Param("bookId") Integer bookId, @Param("userId")Integer userId);


    /**
     * 查询用户购物车有库存的商品
     * @param userId
     * @return
     */
    public Page<Cart> queryHasStockCarts(Integer userId,Integer hasSelected);


    /**
     * 查询用户购物车缺货商品
     * @param userId
     * @return
     */
    public Page<Cart> queryStockoutCarts(Integer userId);


    /**
     * 查询用户购物车商品数量
     * @param userId
     * @return
     */
    public int queryUserCartCount(Integer userId);


    /**
     * 查询用户购物车有库存商品数量
     * @param userId
     * @return
     */
    public int queryHasStockCartCount(Integer userId);


    /**
     * 查询要生成订单的购物车商品是否在售有库存
     * @param cartIds
     * @param userId
     * @return
     */
    public List<Cart> queryPurchaseCarts(@Param("list") List<Integer> cartIds,@Param("userId") Integer userId);


}
