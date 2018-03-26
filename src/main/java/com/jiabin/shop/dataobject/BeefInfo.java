package com.jiabin.shop.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class BeefInfo {

    @Id
    private String beefId;
    /*名*/
    private String beefName;
    /*牛肉价格*/
    private BigDecimal beefPrice;
    /*牛肉库存*/
    private Integer beefStock;
    /*牛肉描述*/
    private String beefDescription;
    /*牛肉图片连接地址*/
    private String beefIcon;
    /*牛肉状态 0正常 1下架*/
    private Integer beefStatus;
    /*类目编号*/
    private Integer categoryType;

    public BeefInfo() {
    }
}
