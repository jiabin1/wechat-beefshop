package com.jiabin.shop.repository;

import com.jiabin.shop.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 加冰
 * @Description:
 * @Date: Created in 2018/3/26 13:25
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

    List<OrderDetail> findByOrderId(String orderId);
}
