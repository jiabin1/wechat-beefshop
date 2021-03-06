package com.jiabin.shop.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 13:01
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId; //用String类型存储更多的值

    private String beefId;

    private String beefName;

    /*单价*/
    private BigDecimal beefPrice;

    /*数量*/
    private Integer beefQuantity;

    private String beefIcon;


}
