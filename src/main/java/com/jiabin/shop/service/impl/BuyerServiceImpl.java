package com.jiabin.shop.service.impl;

import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.service.BuyerService;
import com.jiabin.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/27 21:12
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService; //要用到订单的服务
    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return  checkOrderOwner(openid, orderid);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderid);
        if (orderDTO == null){
            log.error("【取消订单】查不到更改订单， orderid={}", openid);
            throw  new  ShopException(ResultEnums.ORDER_NOT_EXIT);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderid){
        OrderDTO orderDTO = orderService.findOne(orderid);
        if (orderDTO == null){
            throw new ShopException(ResultEnums.ORDERDETAIL_NOT_EXIT);
        }
        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){ //根据订单查到的openid与传入的openid不符合
            log.error("【查询订单】与订单的openid不一致");
            throw new ShopException(ResultEnums.ORDER_OWNER_ERROR);
        }
        return  orderDTO;
    }
}
