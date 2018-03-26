package com.jiabin.shop.enums;

import lombok.Getter;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 12:56
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(0, "成功支付"),
    ;

    private Integer Code;
    private String Msg;

    PayStatusEnum(Integer code, String msg) {
        Code = code;
        Msg = msg;
    }
}
