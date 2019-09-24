package com.c1801.spring.dzy.common;

import com.c1801.spring.dzy.model.BookInfo;
import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/16 19:10
 * 分页信息
 */

@Data
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 当前页码
     */

    private Integer pageNum;


    /**
     * 每页的数量
     */

    private Integer pageSize;


    /**
     * 总数据记录量
     */

    private Long total;


    /**
     * 总页数
     */

    private Integer pages;


    /**
     * 结果集
     */

    private List<T> list;


    /**
     * 是否为第一页
     */

    private boolean isFirsPage = false;


    /**
     * 是否为最后一页
     */

    private boolean isLastPage = false;




    /**
     * 包装分页对象
     * @param page
     */

    public static PageInfo ofPageInfo(Page page){

        PageInfo pageInfo = new PageInfo();

        //当前页
        pageInfo.pageNum = page.getPageNum();
        //每页的数量
        pageInfo.pageSize = page.getPageSize();

        //总页数
        pageInfo.pages = page.getPages();

        pageInfo.list = page.getResult();

        //总数据记录量
        pageInfo.total = page.getTotal();

        pageInfo.judgePageBoudary();

        return pageInfo;
    }


    /**
     * 没有数据
     * @param pageSize
     * @return
     */
    public static PageInfo ofEmpty(Integer pageSize){
        PageInfo<BookInfo> pageInfo = new PageInfo<>();
        pageInfo.setList(Collections.emptyList());
        pageInfo.setPageNum(1);
        pageInfo.setPages(0);
        pageInfo.setTotal(0L);
        pageInfo.setPageSize(pageSize);
        pageInfo.judgePageBoudary();
        return  pageInfo;
    }

    /**
     * 判断页面边界
     */

    private void judgePageBoudary(){
        isFirsPage = pageNum == 1;
        isLastPage = pageNum.equals(pages);
    }

}

