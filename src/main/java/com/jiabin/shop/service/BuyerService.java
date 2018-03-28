package com.jiabin.shop.service;

import com.jiabin.shop.dto.OrderDTO;

/**
 * @Author: 加冰
 * @Description: 接口屏蔽具体实现  此接口做校验用户用
 * @Date: Created in 2018/3/27 21:09
 */
public interface BuyerService {

    //查询订单
    OrderDTO findOrderOne(String openid, String orderid);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderid);
}
