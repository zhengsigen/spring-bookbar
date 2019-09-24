package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.Category;
import com.c1801.spring.dzy.model.CategoryBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //分类列表
    public List<Category> getCategoryList(@Param("index")Integer index,@Param("pageSize")Integer pageSize,@Param("name")String name);
    //分类列表总数量
    public List<Category> getCategoryListTotal(String name);
    //查询指定分类
    public CategoryBook getCategoryByCategoryId(Integer categoryId);
    //为指定分类添加书籍
    public void addCategorys(@Param("categoryId")Integer categoryId,@Param("bookId")List<Integer>bookId);
    //删除指定分类
    public void delCategory(Integer categoryId);
    //删除分类关联表
    public void delCategoryBook(Integer categoryId);
    //删除指定分类指定关联书籍
    public void delCategoryBooks(@Param("categoryId") Integer categoryId, @Param("bookId")List<Integer>bookId);
    //修改指定分类
    public void updateCategory(Category category);
    //新增
    public void addCategory(Category category);


    /**
     * 给书籍增加分类标签
     * @param categories
     * @return
     */
    public int bookAppendCategorys(@Param("categories") List<Category> categories, @Param("bookId") Integer bookId);

    /**
     * 移除书籍分类标签
     * @param categories
     * @return
     */
    public int delCategoryFromBook(@Param("categories") List<Category> categories, @Param("bookId") Integer bookId);

    /**
     * 查询出该书有的分类标签
     * @param bookId
     * @return
     */
    public List<Category> getCategoryInBook(Integer bookId);


    /**
     * 查询出书籍没有的分类标签
     * @param bookId
     * @return
     */
    public List<Category> getCategoryNotBook(Integer bookId);

    public List<Integer> getCategoryMapByBooksId(List<Integer>bookId);

}
