package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.BookCollectionPack;
import com.c1801.spring.dzy.model.RecommendAccount;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * user：少
 * dateTime: 2019/8/30 15:48
 */
@Mapper
public interface CollectionMapper {


    /**
     * 查询单个书单信息
     * @param collectionId
     * @return
     */
    public BookCollectionPack querySingleCollection(Integer collectionId);


    /**
     * 查询书单的推荐信息
     * @param collectionId
     * @param recommendId
     * @param order
     * @param userId
     * @return
     */

    public Page<RecommendAccount> queryRecommendList(@Param("collectionId") Integer collectionId, @Param("recommendId") Integer recommendId, @Param("order")Integer order, @Param("userId") Integer userId);


    /**
     * 根据推荐id查询推荐信息
     * @param recommendId
     * @param userId
     * @return
     */
    public RecommendAccount queryRecommendByRecommendId(@Param("recommendId")Integer recommendId,@Param("userId") Integer userId);



    /**
     * 修改推荐信息最后修改时间
     * @param id
     * @return
     */
    public int updateRecommendUpdateDate(Integer id);
}
