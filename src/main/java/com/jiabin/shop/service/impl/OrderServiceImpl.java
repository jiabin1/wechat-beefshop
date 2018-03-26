package com.jiabin.shop.service.impl;

import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 16:28
 */
@Service  //业务逻辑处理 勿忘！！！
public class OrderServiceImpl implements OrderService{
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO findOne(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findAll(OrderDTO orderDTO, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
