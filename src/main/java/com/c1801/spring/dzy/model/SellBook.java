package com.c1801.spring.dzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellBook {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private boolean isFree;
    private boolean isOrder;
    private Date createDate;
    private Date updateDate;
}
