package com.jiabin.shop.service;


import com.jiabin.shop.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 16:20
 */
public interface OrderService {

    /*创建订单 因为要显示订单详情 为方便显示又不污染数据库映射的类 用DTO*/
    OrderDTO  create(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO findOne(String orderId);

    /*查询订单列表*/
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    /*取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /*接单*/
    OrderDTO finish(OrderDTO orderDTO);

    /*支付*/
    OrderDTO paid(OrderDTO orderDTO);


}
