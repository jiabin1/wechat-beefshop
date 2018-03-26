package com.jiabin.shop.enums;

import lombok.Getter;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/24 15:41
 */
@Getter
public enum BeefStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String message;

    BeefStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
