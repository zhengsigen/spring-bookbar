package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.BookInfo;
import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.Sku;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/8 16:08
 * 书籍单品数据库接口
 */
@Mapper
public interface BookSkuMapper {

    /**
     * 后台条件查询查询所有书籍sku信息
     * @return
     */
    public List<BookSku> queryBookSkuList(String name);

    /**
     * 查询书籍单品信息
     * @param isbn
     * @return
     */
    public BookSku getBookSku(Integer bookId);

    /**
     * 前台查询单本书籍信息
     * @param bookId
     * @return
     */
    public BookInfo getIsSellBookInfo(Integer bookId);

    /**
     * 查询出属于isbns集合中的书籍sku单品信息
     * @param ids
     * @return
     */
    public List<BookSku> getBookSkuList(List<Integer> ids);


    /**
     * 条件查询在售书籍
     * @param name
     * @return
     */
    public Page<BookInfo> queryIsSellBookInfoList(@Param("name") String name,@Param("rate") Double rate);


    /**
     * 根据id查询书籍sku信息
     * @param bookId
     * @return
     */
    public List<Sku> querySkuByBookId(Integer bookId);

}
