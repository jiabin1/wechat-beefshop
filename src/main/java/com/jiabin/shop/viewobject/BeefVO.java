package com.jiabin.shop.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 加冰
 * @Description: 商品状态
 * @Date: Created in 2018/3/24 16:21
 */
@Data
public class BeefVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<BeefInfoVO> beefInfoVOList;

}
