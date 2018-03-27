package com.jiabin.shop.enums;

import lombok.Getter;

/**
 * @Author: 加冰
 * @Description: 列举返回的结果
 * @Date: Created in 2018/3/26 17:09
 */
@Getter
public enum ResultEnums {

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11, "库存不足"),
    ORDER_NOT_EXIT(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIT(13, "商品详情不存在"),

    ;

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
