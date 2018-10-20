package com.shiguang.springbootmybatis.service.impl;

import com.shiguang.springbootmybatis.dao.RedPacketMapper;
import com.shiguang.springbootmybatis.dao.UserRedPacketMapper;
import com.shiguang.springbootmybatis.entity.RedPacket;
import com.shiguang.springbootmybatis.entity.UserRedPacket;
import com.shiguang.springbootmybatis.service.UserRedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by lida
 */
@Service
@Slf4j
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * redis处理高并发
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacket(Integer redPacketId, Integer userId) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer redPacketSize = 0;
        if (valueOperations.get("redPacketSize") != null) {
            redPacketSize = (Integer) valueOperations.get("redPacketSize");
        }
        if (redPacketSize > 20000) {
            return 0;
        }

        valueOperations.set("redPacketSize", redPacketSize + 1);
        RedPacket redPacket = redPacketMapper.selectByPrimaryKey(redPacketId);
        if (redPacket.getStock() > 0) {
            int i = redPacketMapper.decreaseRedPacket(redPacketId);
            if (i == 0) {
                return 0;
            }
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmout());
            int result = userRedPacketMapper.insert(userRedPacket);
            return result;
        }
        return 0;
    }

/**
 * 乐观锁处理
 */
//    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//    public int grapRedPacket(Integer redPacketId, Integer userId) {
//
//        long start = System.currentTimeMillis();
//        //乐观锁重入机制
//        while (true) {
//            long end = System.currentTimeMillis();
//
//            if (end - start > 100) {
//                return 0;
//            }
//            RedPacket redPacket = redPacketMapper.selectByPrimaryKey(redPacketId);
//            if (redPacket.getStock() > 0) {
//                int i = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
//                //如果失败  就继续请求直到时间超过100毫秒
//                if (i == 0) {
//                    continue;
//                }
//                UserRedPacket userRedPacket = new UserRedPacket();
//                userRedPacket.setRedPacketId(redPacketId);
//                userRedPacket.setUserId(userId);
//                userRedPacket.setAmount(redPacket.getUnitAmout());
//                int result = userRedPacketMapper.insert(userRedPacket);
//                return result;
//            } else {
//                return 0;
//            }
//        }
//    }
}
