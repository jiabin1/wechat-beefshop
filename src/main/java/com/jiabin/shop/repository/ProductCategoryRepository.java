package com.jiabin.shop.repository;

import com.jiabin.shop.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

   ProductCategory findOneByCategoryId(Integer categoryId);
   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
