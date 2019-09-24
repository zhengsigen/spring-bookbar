package com.c1801.spring.dzy.model;

import lombok.Data;

//返回书籍加折扣
@Data
public class BookPacking extends Book{
    private Double maxDiscount;
    private Double minDiscount;
}
