package com.jiabin.shop.repository;

import com.jiabin.shop.dataobject.BeefInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeefInfoRepository extends JpaRepository<BeefInfo,String> {

    /*根据状态查上架商品*/
    List<BeefInfo> findByBeefStatus(Integer beefStatus);

    BeefInfo findOneByBeefId(String beefId);
}
