package com.jiabin.shop.controller;

import com.jiabin.shop.converter.OrderFromToOrderDTOConverter;
import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.form.OrderForm;
import com.jiabin.shop.service.OrderService;
import com.jiabin.shop.utills.ResultVOUtil;
import com.jiabin.shop.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/27 10:23
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService; //业务逻辑的具体实现 私有
     /*创建订单*/

     @PostMapping("/create")
     public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

         if (bindingResult.hasErrors()){
             log.error("【创建订单】参数不正确， orderForm={}", orderForm);
             throw new ShopException(ResultEnums.PARAM_ERROR.getCode(),
                     bindingResult.getFieldError().getDefaultMessage());
         }

         OrderDTO orderDTO = OrderFromToOrderDTOConverter.convert(orderForm);
         if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
             log.error("【创建订单】购物车不能为空");
             throw new ShopException(ResultEnums.CART_EMPTY.getCode(),
                     bindingResult.getFieldError().getDefaultMessage());
         }

         OrderDTO createResult = orderService.create(orderDTO);

         Map<String, String> map = new HashMap<>();

         map.put("orderId", createResult.getOrderId());

         return ResultVOUtil.success(map);////
     }

     /*订单列表*/

     /*订单详情*/

     /*取消订单 */
}
