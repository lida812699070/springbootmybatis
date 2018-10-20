package com.shiguang.springbootmybatis.service.impl;

import com.shiguang.springbootmybatis.dao.RedPacketMapper;
import com.shiguang.springbootmybatis.entity.RedPacket;
import com.shiguang.springbootmybatis.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by lida
 */
@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Integer id) {
        return redPacketMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(Integer id) {
        return redPacketMapper.decreaseRedPacket(id);
    }

    @Override
    public int decreaseRedPacketForVersion(Integer id, Integer version) {
        return redPacketMapper.decreaseRedPacketForVersion(id, version);
    }
}
