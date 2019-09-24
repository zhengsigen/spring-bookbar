package com.c1801.spring.dzy.model;

import lombok.Data;

import java.util.List;

//省级
@Data
public class Provincial {
    private String value;
    private String label;
    private List<Municipa> children;
}
