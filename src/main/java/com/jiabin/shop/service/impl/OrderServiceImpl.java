package com.jiabin.shop.service.impl;

import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.dataobject.OrderDetail;
import com.jiabin.shop.dataobject.OrderMaster;
import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.Shopexception;
import com.jiabin.shop.repository.OrderDetailRepository;
import com.jiabin.shop.repository.OrderMasterRepository;
import com.jiabin.shop.service.BeefInfoService;
import com.jiabin.shop.service.OrderService;
import com.jiabin.shop.utills.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 16:28
 */
@Service  //业务逻辑处理 勿忘！！！
public class OrderServiceImpl implements OrderService{

    @Autowired
    private BeefInfoService beefInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /*这个一个消化来自外部的订单并生成系统内部订单的过程*/
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.generateUniqueKey();//生成唯一订单编号
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);//总价容器 先至零

        /* 1.解析请求的订单（具体购买内容），计算总价格与填入订单具体内容数据库*/
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){ //订单具体每一项
            BeefInfo beefInfo = beefInfoService.findOne(orderDetail.getBeefId());//找到对应商品
            if(beefInfo == null){ //先校验是否是有这个商品
                throw new Shopexception(ResultEnums.PRODUCT_NOT_EXIT);
            }

            /*累加计算价格*/
            orderAmount = beefInfo.getBeefPrice()
                    .multiply(new BigDecimal(orderDetail.getBeefQuantity()))
                    .add(orderAmount);
            /*填入服务器数据库中的订单详情库*/
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());//生成内部数据项的过程
            orderDetail.setOrderId(orderId);//所属订单
            BeanUtils.copyProperties(beefInfo, orderDetail);

            orderDetailRepository.save(orderDetail);
        }

        /*2.写入订单主表*/
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        /*扣库存*/

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
