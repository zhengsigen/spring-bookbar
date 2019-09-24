package com.c1801.spring.dzy.service;

import com.c1801.spring.dzy.mapper.CategoryMapper;
import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * user：少
 * dateTime: 2019/8/14 11:16
 * 书籍分类
 */
@Transactional
@Service
public class BookCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 该书籍增加分类
     * @param bookCategories
     * @return
     */
    public int bookAppendCategory(BookSku bookCategories) {
        List<Category> categories = bookCategories.getCategorys();
        int i = categoryMapper.bookAppendCategorys(categories, bookCategories.getId());
        return i;
    }

    /**
     * 删除书籍分类标签
     * @param bookCategories
     * @return
     */
    public int delCategoryFromBook(BookSku bookCategories) {
        List<Category> categories = bookCategories.getCategorys();
        int i = categoryMapper.delCategoryFromBook(categories, bookCategories.getId());
        return i;
    }

    /**
     * 获取该书有的标签
     * @param bookId
     * @return
     */
    public List<Category> getCategoryInBook(Integer bookId){

        List<Category> categoryInBook = categoryMapper.getCategoryInBook(bookId);

        return categoryInBook;
    }


    /**
     * 查询出书籍没有的分类标签
     * @param bookId
     * @return
     */
    public List<Category> getCategoryNotBook(Integer bookId){
        List<Category> categoryInBook = categoryMapper.getCategoryNotBook(bookId);
        return categoryInBook;
    }
}
