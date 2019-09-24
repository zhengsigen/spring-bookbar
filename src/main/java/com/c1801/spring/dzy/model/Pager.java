package com.c1801.spring.dzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    private Integer total;
    private Integer pages;
    private Object data;
}
