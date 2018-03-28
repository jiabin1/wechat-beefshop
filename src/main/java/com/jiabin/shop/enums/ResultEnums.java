package com.jiabin.shop.enums;

import lombok.Getter;

/**
 * @Author: 加冰
 * @Description: 列举返回的结果
 * @Date: Created in 2018/3/26 17:09
 */
@Getter
public enum ResultEnums {
    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11, "库存不足"),
    ORDER_NOT_EXIT(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIT(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAILED(15, "更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17, "支付状态错误"),

    CART_EMPTY(18, "购物车不能为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),


    ;

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
