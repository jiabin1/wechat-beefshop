package com.jiabin.shop.service;

import com.jiabin.shop.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    //卖家
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    //买家 通过type来查  save是新增与更新
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
