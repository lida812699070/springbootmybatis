package com.shiguang.springbootmybatis.dao;

import com.shiguang.springbootmybatis.entity.OrderDetail;
import com.shiguang.springbootmybatis.entity.OrderDetail2;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(String detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    /**普通关联*/
    OrderDetail selectByPrimaryKey(String detailId);
    /**通过表连接进行增加另外表中的字段  Bean要重新定义*/
    OrderDetail2 selectByPrimaryKey2(String detailId);
    /**通过表连接进行表关联s*/
    OrderDetail selectByPrimaryKey3(String detailId);

    List<OrderDetail> selectByOrderId(String orderId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}