package com.jiabin.shop.dataobject;

import com.jiabin.shop.enums.OrderStatusEnum;
import com.jiabin.shop.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 12:38
 */
@Entity
@Data
@DynamicUpdate //自动更新时间 Date值
public class OrderMaster {

    @Id //主键得做标记
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /*默认0 新订单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();  // 用枚举属性映射代码
    /*默认0 未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date UpdateTime;
}
