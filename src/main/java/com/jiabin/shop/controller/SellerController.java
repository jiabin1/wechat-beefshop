package com.jiabin.shop.controller;

import com.jiabin.shop.dto.OrderDTO;
import com.jiabin.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/28 14:33
 */
@Controller//不需要返回json格式 所以不需要Rest
@RequestMapping("/seller/order")
public class SellerController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String, Object> map){
        PageRequest request = new PageRequest(page -1, size);//从第0页开始 这是一个页面容器
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);  //视图模型 把对象嵌入视图
        map.put("currentSize",size);

        return new ModelAndView( "order/list", map);


    }
}
