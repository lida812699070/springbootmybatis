package com.shiguang.springbootmybatis.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail2 {
    private String detailId;

    private String orderId;

    private String productStock;
    private String productName;

    private Integer productQuantity;

    private BigDecimal productPrice;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}