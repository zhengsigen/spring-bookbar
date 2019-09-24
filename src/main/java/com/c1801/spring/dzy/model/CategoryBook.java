package com.c1801.spring.dzy.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryBook extends  Category{
    private List<Book>books;
}
