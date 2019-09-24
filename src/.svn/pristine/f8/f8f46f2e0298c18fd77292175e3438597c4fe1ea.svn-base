package com.c1801.spring.dzy.controller.book;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.Category;
import com.c1801.spring.dzy.service.BookCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 后台管理
 * user：少
 * dateTime: 2019/8/14 10:27
 *
 * 书籍分类关联
 */
@Transactional
@RestController
@RequestMapping("/manage/book/category")
public class ManageBookCategoryController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookCategoryService bookCategoryService;

    /**
     * 给书籍添加分类标签
     * @param bookCategories
     * @return
     */
    @PostMapping
    public ResData bookAppendCategory(@RequestBody BookSku bookCategories) throws MissingServletRequestParameterException{

        if(bookCategories.getId() == null){
            throw new MissingServletRequestParameterException("id", "书籍id必须参入");
        }
        if(bookCategories.getCategorys() == null || bookCategories.getCategorys().isEmpty()){
            throw new MissingServletRequestParameterException("categorys", "分类不能为null或者空");
        }

        logger.info("bookCategories = " + bookCategories);

        int i = bookCategoryService.bookAppendCategory(bookCategories);

        return ResData.ofSuccess(0, "成功", i);
    }


    /**
     * 删除书籍的分类标签
     * @param bookCategories
     * @return
     */
    @DeleteMapping
    public ResData delCategoryFromBook(@RequestBody BookSku bookCategories){
        int i = bookCategoryService.delCategoryFromBook(bookCategories);
        return ResData.ofSuccess(0, "成功", i);
    }


    /**
     * 查询出某本书有的分类标签
     * @return
     */
    @GetMapping("/{bookId:\\d+}")
    public ResData getCategoryInBook(@PathVariable Integer bookId){
        logger.info("查询出有的分类标签" + bookId);
        List<Category> categoryInBook = bookCategoryService.getCategoryInBook(bookId);
        return ResData.ofSuccess(0, "成功",categoryInBook);
    }


    /**
     * 查询出书籍没有的分类标签
     * @param bookId
     * @return
     */
    @GetMapping("/not/{bookId:\\d+}")
    public ResData getCategoryNotBook(@PathVariable Integer bookId){
        logger.info("查询出书籍没有的分类标签" + bookId);
        List<Category> categoryInBook = bookCategoryService.getCategoryNotBook(bookId);
        return ResData.ofSuccess(0, "成功",categoryInBook);
    }

}
