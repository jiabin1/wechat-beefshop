package com.jiabin.shop.enums;

/**
 * @Author: 加冰
 * @Description: 列举返回的结果
 * @Date: Created in 2018/3/26 17:09
 */
public enum ResultEnums {

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    ;

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
