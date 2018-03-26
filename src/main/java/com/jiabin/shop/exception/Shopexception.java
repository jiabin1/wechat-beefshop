package com.jiabin.shop.exception;

import com.jiabin.shop.enums.ResultEnums;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 18:43
 */
public class Shopexception extends RuntimeException {

    private Integer code;

    public Shopexception(ResultEnums resultEnums) {
        super(resultEnums.getMsg()); //将msg传给父类的构造方法 内含message属性

        this.code = resultEnums.getCode();
    }
}
