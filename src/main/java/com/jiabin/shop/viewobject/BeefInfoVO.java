package com.jiabin.shop.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: 加冰
 * @Description: 商品详情
 * @Date: Created in 2018/3/24 16:25
 */
@Data // 忘记就没有getset
public class BeefInfoVO {

    @JsonProperty("id")
    private String beefId;

    @JsonProperty("name")
    private String beefName;

    @JsonProperty("price")
    private BigDecimal beefPrice;

    @JsonProperty("description")
    private String beefDescription;

    @JsonProperty("icon")
    private String beefIcon;
}
