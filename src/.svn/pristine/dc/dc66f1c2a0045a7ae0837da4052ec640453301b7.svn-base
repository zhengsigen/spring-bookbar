package com.c1801.spring.dzy.controller.addr;

import com.c1801.spring.dzy.mapper.ZHCityInfoMapper;
import com.c1801.spring.dzy.model.Provincial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/dzy/provincial")
public class ZHCityInfoCrntroller {

    @Autowired
    ZHCityInfoMapper zhCityInfoMapper;
    //获取中国所有省份
    @GetMapping
    public List<Provincial> getProvincial(){
        return zhCityInfoMapper.getProvincial();
    }
}
