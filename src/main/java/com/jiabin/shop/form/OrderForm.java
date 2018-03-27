package com.jiabin.shop.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: 加冰
 * @Description: 进行表单验证
 * @Date: Created in 2018/3/27 10:58
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openId不能为空")
    private String openid; //openid 为一个完整名词

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
