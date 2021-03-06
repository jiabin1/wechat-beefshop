package com.jiabin.shop.enums;

import lombok.Getter;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 12:35
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISH(1, "已完成"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
