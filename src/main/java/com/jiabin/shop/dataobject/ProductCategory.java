package com.jiabin.shop.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //实体类DO对象 存储数据项属性
@Table(name="product_category")
@DynamicUpdate
@Data
public class ProductCategory {
    /*id*/
    @Id //主键
    @GeneratedValue //自增类型
    private Integer categoryId;

    private String categoryName;

    /*编号*/
    private Integer categoryType;

    //无参构造方法

    public ProductCategory() {
    }
}
