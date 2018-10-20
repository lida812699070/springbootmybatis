package com.shiguang.springbootmybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedPacket implements Serializable {

    private static final long serialVersionUID = 2396731286678791110L;
    private Integer id;

    private Integer userId;

    private BigDecimal amount;

    private Date sendDate;

    private Integer total;

    private BigDecimal unitAmout;

    private Integer stock;

    private Integer version;

    private String note;

}