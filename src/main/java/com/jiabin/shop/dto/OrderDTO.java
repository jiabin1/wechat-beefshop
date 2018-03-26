package com.jiabin.shop.dto;

import com.jiabin.shop.dataobject.OrderDetail;
import com.jiabin.shop.enums.OrderStatusEnum;
import com.jiabin.shop.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: 加冰
 * @Description: dto传输层，专门提供数据在各个层之间流通，而不是映射数据库
 * @Date: Created in 2018/3/26 16:16
 */
@Data
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

    List<OrderDetail> orderDetailList;
}
