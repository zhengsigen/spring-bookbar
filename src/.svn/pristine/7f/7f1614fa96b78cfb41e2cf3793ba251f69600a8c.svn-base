package com.c1801.spring.dzy.controller.category;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.BookMapper;
import com.c1801.spring.dzy.mapper.CategoryMapper;
import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.Category;
import com.c1801.spring.dzy.model.CategoryBook;
import com.c1801.spring.dzy.model.Pager;
import com.c1801.spring.dzy.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理
 */
@RestController
@Transactional
@RequestMapping("/manage/category")
public class ManageCategoryController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserService userService;


    //分类列表
    @GetMapping
    public ResData getCategoryList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                   @RequestParam(value = "name",required = false)String name){
        Integer index = (page-1)*pageSize;
        List<Category> categoryListTotal = categoryMapper.getCategoryListTotal(name);
        List<Category> categoryList = categoryMapper.getCategoryList(index, pageSize, name);
        Pager pager = new Pager();
        pager.setTotal(categoryListTotal.size());
        pager.setPages( (categoryListTotal.size()/pageSize) + (categoryListTotal.size()%pageSize==0?0:1) );
        pager.setData(categoryList);
        return ResData.of(0,"成功",pager);
    }


    //分类id
    @GetMapping("/{categoryId}")
    public ResData getCategoryByCategoryId(@PathVariable("categoryId") Integer categoryId){
        CategoryBook category = categoryMapper.getCategoryByCategoryId(categoryId);
        if(category==null) return ResData.ofSuccess(10088,"分类id不存在或分类暂无书籍");
        return ResData.of(0,"成功",category);
    }


    //新增分类
    @PostMapping
    public ResData addCategory(@RequestBody Category category){
        try{
            categoryMapper.addCategory(category);
        }catch (Exception e){
            if(category.getId()==null) return ResData.ofSuccess(10089,"分类昵称已存在");
        }
        return ResData.ofSuccess(0,"新增成功");
    }


    //为指定分类添加书籍
    @PostMapping("/book")
    public ResData addCategory(@RequestBody CategoryBook categoryBook){
        List<Integer>bookId = new ArrayList<>();
        for (Book book : categoryBook.getBooks()) {
            bookId.add(book.getId());
        }
        categoryMapper.addCategorys(categoryBook.getId(),bookId);
        return ResData.ofSuccess(0,"新增成功");
    }

    //删除指定分类
    @DeleteMapping("/{categoryId}")
    public ResData delCategory(@PathVariable("categoryId") Integer categoryId){
        //删除分类单表
        categoryMapper.delCategory(categoryId);
        //删除分类关联表
        categoryMapper.delCategoryBook(categoryId);
        return ResData.ofSuccess(0,"删除成功");
    }

    //删除分类中书籍
    @DeleteMapping
    public ResData delCategoryBooks(@RequestParam("categoryId")Integer categoryId,@RequestParam("bookId")List<Integer>bookId){
        categoryMapper.delCategoryBooks(categoryId,bookId);
        return ResData.ofSuccess(0,"删除成功");
    }


    //修改分类昵称
    @PutMapping
    public ResData updateCategory(@RequestBody Category category){
        categoryMapper.updateCategory(category);
        return ResData.ofSuccess(0,"修改成功");
    }


    //查询所有书籍（不在当前分类中的书籍）
    @GetMapping("/book")
    public ResData getBooksNotInCategory(@RequestParam("categoryId") Integer categoryId){
        List<Book> booksNotInCategory = bookMapper.getBooksNotInCategory(categoryId);
        return ResData.of(0,"成功",booksNotInCategory);
    }


}
