package com.jiabin.shop.service;

import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: 加冰
 * @Description: 第一次用类头注释模板，仪式感和版权感
 *              关于商品的操作
 * @Date: Created in 2018/3/24 14:57
 */
public interface BeefInfoService {

    /*根据id查询牛肉*/
    BeefInfo findOne(String beefId);
    /*查询在架牛肉列表*/
    List<BeefInfo> findUpAll();
    /*卖家端分页查询商品*/
    Page<BeefInfo> findAll(Pageable pageable);
    BeefInfo save(BeefInfo beefInfo);

    /*加库存 */
    void increaseStock(List<CartDTO> cartDTOList);

    /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

}
