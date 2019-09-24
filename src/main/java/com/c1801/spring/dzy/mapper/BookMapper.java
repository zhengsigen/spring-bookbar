package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.BookInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    //查询指定书
    public Book queryBookByIBSN(String isbn);
    //新增一本书
    public void addBook(Book book);

    /**
     * 根据书籍id查询书籍
     * @param bookId
     * @return
     */
    public Book queryBookById(Integer bookId);

    /**
     * 条件查询分页
     * @param name 书名
     * @param author 作者
     * @param minStock 最低库存
     * @param maxStock 最高库存
     * @param minSale 最低销量
     * @param maxSale 最高销量
     * @param minStatus 最小状态
     * @param maxStatus 最大状态
     * @return
     */
    public Page<BookInfo> queryBookInfoList(
            @Param("name") String name,
            @Param("author") String author,
            @Param("minStock") Integer minStock,
            @Param("maxStock") Integer maxStock,
            @Param("minSale") Integer minSale,
            @Param("maxSale") Integer maxSale,
            @Param("minStatus") Integer minStatus,
            @Param("maxStatus") Integer maxStatus,
            @Param("nameStatus") Integer nameStatus,
            @Param("totalStatus") Integer totalStatus);


    /**
     * 查询该分类没有的书籍
     * @param categoryId
     * @return
     */
    public List<Book>getBooksNotInCategory(Integer categoryId);


}
