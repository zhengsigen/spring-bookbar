package com.c1801.spring.dzy.controller.book;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/manage/book")
public class ManageBookController {

    @Autowired
    private BookService bookService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** 后台管理
     * 条件书籍信息查询分页
     * @param name
     * @param author
     * @param minStock
     * @param maxStock
     * @param minSale
     * @param maxSale
     * @param minStatus
     * @param maxStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping
    public ResData getBookInfoList(@RequestParam(value = "name",defaultValue = "", required = false) String name
                                   , @RequestParam(value = "author", defaultValue = "", required = false) String author
                                   , @RequestParam(value = "minStock", required = false) Integer minStock
                                   , @RequestParam(value = "maxStock", required = false) Integer maxStock
                                   , @RequestParam(value = "minSale", required = false) Integer minSale
                                   , @RequestParam(value = "maxSale", required = false) Integer maxSale
                                   , @RequestParam(value = "minStatus", required = false) Integer minStatus
                                   , @RequestParam(value = "maxStatus", required = false) Integer maxStatus
                                   , @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize
                                   , @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum
                                    , @RequestParam(value = "nameStatus", required = false) Integer nameStatus
                                    ,@RequestParam(value = "totalStatus",required = false) Integer totalStatus){

        logger.info("name = " + name + author +  minStock+ maxStock + minSale+ maxSale+ minStatus+ maxStatus+ pageSize+ pageNum+nameStatus+totalStatus);
        PageInfo<BookInfo> bookList = bookService.queryBookInfoOfPage(name, author, minStock, maxStock, minSale, maxSale, minStatus, maxStatus, pageSize, pageNum,nameStatus,totalStatus);
        logger.info("bookList："+bookList);
        System.out.println("nameStatus:"+nameStatus);
        System.out.println("totalStatus:"+totalStatus);
        return ResData.ofSuccess(0, "成功",bookList);
    }


    /** 后台管理
     * 条件查询书籍所有单品信息
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/sku")
    public ResData getBookSkuList(@RequestParam(value = "name",defaultValue = "") String name
                                , @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize
                                , @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){

        logger.info("name = " + name + " pageSize = " + pageSize + " pageNum =" + pageNum);

        if(StringUtils.isEmpty(name.trim())){
            name = null;
        }
        logger.info("查询所有书籍单品信息");
        List<BookSku> bookSkuList = bookService.getBookSkuList(name, pageSize, pageNum);
        return ResData.ofSuccess(0, "成功", bookSkuList);
    }



    /** 后端
     * 通过id查询本书籍所有单品信息
     * @param bookId
     * @return
     */
    @GetMapping("/sku/{bookId:\\d{1,13}+}")
    public ResData getBook(@PathVariable Integer bookId){

        logger.info("查询本书籍所有单品信息 = " + bookId);

        BookSku bookSku = bookService.getBookSku(bookId);
        logger.info("bookSku = " + bookSku);
        return ResData.ofSuccess(0, "成功",bookSku);
    }




    /** 后台管理
     * 修改sku
     * @param sku
     * @return
     * @throws MissingServletRequestParameterException
     */
    @PutMapping
    public ResData updateSku(@RequestBody Sku sku) throws MissingServletRequestParameterException{
        logger.info("修改 sku = " + sku);
        if(sku.getId() == null){
            throw new MissingServletRequestParameterException("id", "必须有用户id参数");
        }
        int i = bookService.updateSku(sku);
        return ResData.ofSuccess(0, "成功",i);
    }


    /** 后台管理
     * 删除一本书籍一个单品
     * @param id
     * @return
     */
    @DeleteMapping("/sku/{id:\\d+}")
    public ResData delSku(@PathVariable("id") Integer id){
        logger.info("id = " + id);
        int i = bookService.delSku(id);
        return ResData.ofSuccess(0, "成功", i);
    }


}
