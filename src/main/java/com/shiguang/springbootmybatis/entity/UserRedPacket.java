package com.shiguang.springbootmybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserRedPacket implements Serializable{
    private static final long serialVersionUID = -2166806965156907009L;
    private Integer id;

    private Integer redPacketId;

    private Integer userId;

    private BigDecimal amount;

    private Date grabTime;


}