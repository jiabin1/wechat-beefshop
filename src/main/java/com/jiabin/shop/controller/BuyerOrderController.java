package com.jiabin.shop.controller;

import com.jiabin.shop.converter.OrderFromToOrderDTOConverter;
import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.form.OrderForm;
import com.jiabin.shop.service.BuyerService;
import com.jiabin.shop.service.OrderService;
import com.jiabin.shop.utills.ResultVOUtil;
import com.jiabin.shop.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private BuyerService buyerService;

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
     @PostMapping("/list")
     public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size){
         if (StringUtils.isEmpty(openid)){ //数据校验
                log.error("【查询订单列表】openid为空");
                throw new ShopException(ResultEnums.PARAM_ERROR);
         }
         PageRequest request = new PageRequest(page, size);
         Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

         return ResultVOUtil.success(orderDTOPage.getContent());
     }
     /*订单详情*/
     @GetMapping("/detail")
     public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid, //openid校验用
                                      @RequestParam("orderid") String orderid){

         OrderDTO orderDTO = buyerService.findOrderOne(openid, orderid);
         return ResultVOUtil.success(orderDTO);
     }


     /*取消订单 */
     @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid){
         buyerService.findOrderOne(openid, orderid); //不需要返回对象 所以不需要声明容器去接收

         return ResultVOUtil.success();
     }
}
