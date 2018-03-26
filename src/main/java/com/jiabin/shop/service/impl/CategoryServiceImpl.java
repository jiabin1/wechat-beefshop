package com.jiabin.shop.service.impl;

import com.jiabin.shop.dataobject.ProductCategory;
import com.jiabin.shop.repository.ProductCategoryRepository;
import com.jiabin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private ProductCategoryRepository repository; //只需声明，spring自动创建对象

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOneByCategoryId(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
