package com.c1801.spring.dzy.controller.freight;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.FreightMapper;
import com.c1801.spring.dzy.model.Freight;
import com.c1801.spring.dzy.model.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@Transactional
@RequestMapping("/manage/freight")
public class ManageFreightController {
    @Autowired
    FreightMapper freightMapper;
    @GetMapping()
    public ResData query(@RequestParam(value = "page", defaultValue = "1") Integer page
                        ,@RequestParam(value = "size", defaultValue = "5") Integer size){
        PageHelper.startPage(page,size);
        Page<Freight> query = freightMapper.query();
        PageInfo pageInfo = PageInfo.ofPageInfo(query);
        System.out.println(pageInfo);
        return ResData.ofSuccess(0,"查询完毕！",pageInfo);
    }

    @PostMapping()
    public ResData add(@RequestBody Freight freight){
        if(freightMapper.add(freight))
            return ResData.ofSuccess(0,"添加成功");
        return ResData.ofSuccess(0,"添加失败");
    }

    @PutMapping()
    public ResData update(@RequestBody Freight freight){
        if(freightMapper.update(freight))
            return ResData.ofSuccess(0,"修改成功");
        return ResData.ofSuccess(0,"修改失败");
    }


}
