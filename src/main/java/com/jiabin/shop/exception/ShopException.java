package com.jiabin.shop.exception;

import com.jiabin.shop.enums.ResultEnums;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 18:43
 */
public class ShopException extends RuntimeException {

    private Integer code;

    public ShopException(ResultEnums resultEnums) {
        super(resultEnums.getMsg()); //将msg传给父类的构造方法 内含message属性

        this.code = resultEnums.getCode();
    }

    public ShopException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
