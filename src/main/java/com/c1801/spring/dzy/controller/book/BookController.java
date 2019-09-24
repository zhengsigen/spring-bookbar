package com.c1801.spring.dzy.controller.book;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.BookInfo;
import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.Sku;
import com.c1801.spring.dzy.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/dzy/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 前端首页书籍列表
     * @param name
     * @param rate
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping
    public ResData getIsSellBookSkuList(@RequestParam(value = "name",defaultValue = "") String name
                                    ,@RequestParam(value = "rate", defaultValue = "0") Double rate
                                    , @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize
                                    , @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){

        logger.info("name = " + name + " rate=" + rate + " pageSize = " + pageSize + " pageNum =" + pageNum);
        if(StringUtils.isEmpty(name.trim())){
            name = null;
        }
        logger.info("查询所有书籍单品信息");
        PageInfo<BookSku> bookSkuList = bookService.queryIsSellBookInfoList(name,rate, pageSize, pageNum);
        return ResData.ofSuccess(0, "成功", bookSkuList);
    }


    /**
     * 前端通过id查询本书籍信息
     * @param bookId
     * @return
     */
    @GetMapping("/{bookId:\\d{1,13}+}")
    public ResData getIsSellBook(@PathVariable("bookId") Integer bookId){

        logger.info("查询本书籍所有单品信息 = " + bookId);

        BookInfo bookInfo = bookService.getIsSellBookInfo(bookId);
        logger.info("bookInfo = " + bookInfo);
        return ResData.ofSuccess(0, "成功",bookInfo);
    }

    /** 前端
     * 通过id查询书籍所有单品信息
     * @param bookId
     * @return
     */
    @GetMapping("/sku/{bookId:\\d+}")
    public ResData getBook(@PathVariable Integer bookId){

        logger.info("查询本书籍所有单品信息 = " + bookId);

        List<Sku>  skus= bookService.querySkuByBookId(bookId);
        logger.info("bookSku = " + skus);
        return ResData.ofSuccess(0, "成功",skus);
    }

}
