package com.jiabin.shop.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiabin.shop.dataobject.OrderDetail;
import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.form.OrderForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 加冰
 * @Description: 将表单转化为订单对象 这是前端表示消化为后端数据对象表示的过程
 * @Date: Created in 2018/3/27 11:09
 */
@Data
@Slf4j
public class OrderFromToOrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson(); //String 转化为 Json

        OrderDTO orderDTO = new OrderDTO(); //要转换成的订单

        //属性名不一样 不能用BeanUtil
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        /*items转换成订单详情*/
        List<OrderDetail> orderDetailList = new ArrayList<>(); //接收转换后的json
        try { //防止转换出错
            orderDetailList = gson.fromJson(orderForm.getItems(),  //Form是个String
                    new TypeToken<List<OrderDetail>>(){}.getType()); //转化成list
        }catch (Exception e){
            log.error("【对象转换】错误，String={}",orderForm.getItems());
            throw new ShopException(ResultEnums.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList); //填满DTO中的详情表

        return orderDTO;
    }
}
