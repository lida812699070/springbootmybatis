package com.shiguang.springbootmybatis.service.impl;

import com.shiguang.springbootmybatis.entity.UserRedPacket;
import com.shiguang.springbootmybatis.service.RedisRedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Create by lida
 */
@Slf4j
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final String PREFIX = "red_packet_list_";
    private static final int TIME_SIZE = 1000;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    @Async
    public void saveUserRedPacketByRedis(Integer redPacketId, BigDecimal unitAmount) {

        log.error("开始保存数据");
        long start = System.currentTimeMillis();
        BoundListOperations<String, String> ops = redisTemplate.boundListOps(PREFIX + redPacketId);
        Long size = ops.size();
        long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
        int count = 0;
        ArrayList<UserRedPacket> userRedPacketList = new ArrayList<>(TIME_SIZE);
        for (int i = 0; i < times; i++) {
            List userIdList;
            if (i == 0) {
                userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPacketList.clear();
            for (Object anUserIdList : userIdList) {
                String args = anUserIdList.toString();
                String[] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                Integer userId = Integer.valueOf(userIdStr);
                long time = Long.parseLong(timeStr);
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setUserId(userId);
                userRedPacketList.add(userRedPacket);
            }
//            count += executeBatch(userRedPacketList);
        }
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        log.error("保存数据结束， 耗时" + (end - start)
                + "毫秒，共" + count + "条记录被保存。 ");

    }
}
