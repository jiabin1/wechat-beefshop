package com.jiabin.shop.repository;

import com.jiabin.shop.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 13:21
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{

    /*分页返回Page对象*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
