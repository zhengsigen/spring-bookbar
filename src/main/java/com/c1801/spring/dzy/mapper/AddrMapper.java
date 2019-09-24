package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * user：少
 * dateTime: 2019/8/7 14:59
 */
@Mapper
public interface AddrMapper {


    public Address getAddrById(Integer id);


    public int addAddr(@Param("address") Address address);


    public int updateAddr(@Param("address") Address address,@Param("id")Integer id);



}
