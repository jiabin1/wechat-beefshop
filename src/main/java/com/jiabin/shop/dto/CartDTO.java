package com.jiabin.shop.dto;

import lombok.Data;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 20:11
 */
@Data
public class CartDTO {
    /*购买牛肉编号*/
    private String beefId;
    /*购买牛肉数量*/
    private Integer beefQuantity;

    public CartDTO(String beefId, Integer beefQuantity) {
        this.beefId = beefId;
        this.beefQuantity = beefQuantity;
    }
}
