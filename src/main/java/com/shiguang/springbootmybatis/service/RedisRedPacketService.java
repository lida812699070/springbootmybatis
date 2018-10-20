package com.shiguang.springbootmybatis.service;

import java.math.BigDecimal;

/**
 * Create by lida
 */
public interface RedisRedPacketService {

    void saveUserRedPacketByRedis(Integer redPacketId, BigDecimal unitAmount);
}
