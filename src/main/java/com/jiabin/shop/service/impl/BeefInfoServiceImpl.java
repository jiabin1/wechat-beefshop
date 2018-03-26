package com.jiabin.shop.service.impl;

import com.jiabin.shop.dataobject.BeefInfo;
import com.jiabin.shop.enums.BeefStatusEnum;
import com.jiabin.shop.repository.BeefInfoRepository;
import com.jiabin.shop.service.BeefInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/24 15:48
 */
@Service //忘了就不知道是个服务层 无法自动注入
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
}
