package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sky.dao.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    /**
     * 批量插入订单明细数据
     * @param orderDetails
     */
    void insertBatch(@Param("orderDetails") List<OrderDetail> orderDetails);

    /**
     * 查询订单明细
     * @param orderId
     * @return
     */
    List<OrderDetail> getByOrderId(@Param("orderId") Long orderId);

}
