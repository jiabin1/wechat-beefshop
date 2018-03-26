package com.jiabin.shop.viewobject;

import lombok.Data;

/**
 * @Author: 加冰
 * @Description: 给前端用的 http请求返回的最外层对象 结果
 * @Date: Created in 2018/3/24 16:12
 */
@Data //自动生成getset
public class ResultVO<T> {   //data是对象 所以用泛型

    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*具体内容 是个对象 用泛型 对象的数据态*/
    private T data;
}
