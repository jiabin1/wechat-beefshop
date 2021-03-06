package com.jiabin.shop.service.impl;

import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.dto.CartDTO;
import com.jiabin.shop.enums.BeefStatusEnum;
import com.jiabin.shop.enums.ResultEnums;
import com.jiabin.shop.exception.ShopException;
import com.jiabin.shop.repository.BeefInfoRepository;
import com.jiabin.shop.service.BeefInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author: 加冰
 * @Description: Service关注业务逻辑 在这一层屏蔽数据库操作
 * @Date: Created in 2018/3/24 15:48
 */
@Service //缺失此标注框架就不知道是个服务层 无法完成自动注入
public class BeefInfoServiceImpl implements BeefInfoService {

    @Autowired
    private BeefInfoRepository repository;

    @Override
    public BeefInfo findOne(String beefId) {
        return repository.findOneByBeefId(beefId);
    }

    @Override
    public List<BeefInfo> findUpAll() {
        return repository.findByBeefStatus(BeefStatusEnum.UP.getCode());
    }

    @Override
    public Page<BeefInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public BeefInfo save(BeefInfo beefInfo) {
        return repository.save(beefInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            //先判断商品是否存在
            BeefInfo beefInfo = repository.findOneByBeefId(cartDTO.getBeefId());
            if (beefInfo == null){
                throw new ShopException(ResultEnums.PRODUCT_NOT_EXIT);
            }

            Integer result = beefInfo.getBeefStock() + cartDTO.getBeefQuantity();
            beefInfo.setBeefStock(result);
            repository.save(beefInfo); //数据库事务都是表中一项为最小粒度单位，不能操作到一项中的具体属性
        }
    }

    @Override
    @Transactional //同一个订单 要么就全部减 数据库组合操作需要整体性 就一个事务
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            BeefInfo beefInfo = repository.findOneByBeefId(cartDTO.getBeefId());
            if (beefInfo == null){
                throw new ShopException(ResultEnums.PRODUCT_NOT_EXIT);
            }

            Integer result = beefInfo.getBeefStock() - cartDTO.getBeefQuantity();
            if (result < 0){
                throw new ShopException(ResultEnums.PRODUCT_NOT_ENOUGH);
            }

            beefInfo.setBeefStock(result); //复制容器
            repository.save(beefInfo); //把整个商品设置更新进去
        }

    }
}
