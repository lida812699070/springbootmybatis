package com.shiguang.springbootmybatis.service;

import com.shiguang.springbootmybatis.entity.RedPacket;

/**
 * Create by lida
 */
public interface RedPacketService {

    RedPacket getRedPacket(Integer id);

    int decreaseRedPacket(Integer id);

    int decreaseRedPacketForVersion(Integer id, Integer version);
}
