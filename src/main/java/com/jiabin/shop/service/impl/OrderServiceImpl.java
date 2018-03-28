package com.jiabin.shop.service.impl;

import com.jiabin.shop.converter.OrderMasterToOrderDTOConverter;
import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.dataobject.OrderDetail;
import com.jiabin.shop.dataobject.OrderMaster;
import com.jiabin.shop.dto.CartDTO;
import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.OrderStatusEnum;
import com.jiabin.shop.enums.PayStatusEnum;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.repository.OrderDetailRepository;
import com.jiabin.shop.repository.OrderMasterRepository;
import com.jiabin.shop.service.BeefInfoService;
import com.jiabin.shop.service.OrderService;
import com.jiabin.shop.utills.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional //确保抛出异常后会回滚
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.generateUniqueKey();//生成唯一订单编号
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);//总价容器 先至零

//AAA        List<CartDTO> cartDTOList = new ArrayList<>();
        /* 1.解析请求的订单（具体购买内容），计算总价格与填入订单具体内容数据库*/
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){ //订单具体每一项
            BeefInfo beefInfo = beefInfoService.findOne(orderDetail.getBeefId());//找到对应商品
            if(beefInfo == null){ //先校验是否是有这个商品
                throw new ShopException(ResultEnums.PRODUCT_NOT_EXIT);
            }

            /*累加计算价格*/
            orderAmount = beefInfo.getBeefPrice()
                    .multiply(new BigDecimal(orderDetail.getBeefQuantity()))
                    .add(orderAmount);
            /*填入服务器数据库中的订单详情库*/
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());//生成内部数据项的过程
            orderDetail.setOrderId(orderId);//所属订单
            BeanUtils.copyProperties(beefInfo, orderDetail);
            orderDetail.setBeefId(beefInfo.getBeefId());

//AAA            CartDTO cartDTO = new CartDTO(orderDetail.getBeefId(), orderDetail.getBeefQuantity());
//AAA            cartDTOList.add(cartDTO);

            orderDetailRepository.save(orderDetail);
        }

        /*2.写入订单主表*/
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId); //返回的DTO需要id 若在 1 处只能设置一个master
        BeanUtils.copyProperties(orderDTO, orderMaster);// 先拷贝再设置 不然会被覆盖 null也会被传入
        //1
        orderMaster.setOrderAmount(orderAmount); //以下三项 在DTO中都为null
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        /*扣库存*/
        //AAA等价于
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getBeefId(), e.getBeefQuantity())
        ).collect(Collectors.toList());
        beefInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOneByOrderId(orderId);
        if (orderMaster == null){
            throw new ShopException(ResultEnums.ORDER_NOT_EXIT);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){ //list不能为空
            throw new ShopException(ResultEnums.ORDER_NOT_EXIT);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);//!!!!!订单详情设置到数据传输对象上去

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        //封装回去page
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();//只有订单主表DAO能与数据库交互

        //判断状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new ShopException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //修改订单状态返回更新后的对象并判断是否成功
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster); //修改完再拷贝
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null){
            throw new ShopException(ResultEnums.ORDER_UPDATE_FAILED);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){//判断列表是否有商品 即列表是否为空 能让代码更健壮
            throw new ShopException(ResultEnums.ORDER_DETAIL_EMPTY);
        }
        /*抽取商品编号与数量形成购物车这种与业务逻辑相贴合的数据传输对象*/
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()//将订单列表里的具体内容表抽出
                .map(e -> new CartDTO(e.getBeefId(), e.getBeefQuantity()))//装入购物车每一项
                .collect(Collectors.toList());//形成表
        beefInfoService.increaseStock(cartDTOList);
        //退款
        if(orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO 支付过程  todo标记能缓存待做事项
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new ShopException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster); //返回结果
        if (updateResult == null){
            throw new ShopException(ResultEnums.ORDER_UPDATE_FAILED);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new ShopException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //判断支付
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new ShopException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }

        //修改
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster); //返回结果
        if (updateResult == null){
            throw new ShopException(ResultEnums.ORDER_UPDATE_FAILED);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

}
