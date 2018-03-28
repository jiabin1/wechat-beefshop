package com.jiabin.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiabin.shop.dataobject.OrderDetail;
import com.jiabin.shop.enums.OrderStatusEnum;
import com.jiabin.shop.enums.PayStatusEnum;
import com.jiabin.shop.utills.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 加冰
 * @Description: dto传输层，专门提供数据在各个层之间流通，而不是映射数据库
 *               关联详情表和主表
 * @Date: Created in 2018/3/26 16:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  //有null就不返回
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /*默认0 新订单*/
    private Integer orderStatus;  // 用枚举属性映射代码
    /*默认0 未支付*/
    private Integer payStatus;

    private Date createTime;

    private Date UpdateTime;

    List<OrderDetail> orderDetailList = new ArrayList<>(); //赋予初始空值 字段必须时

    @JsonIgnore //若将DTO发送给前端 不会多出getOrderStatusEnums的键对值
    public OrderStatusEnum getOrderStatusEnums(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnums(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }


}
