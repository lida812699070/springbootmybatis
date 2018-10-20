package com.shiguang.springbootmybatis.controller;

import com.shiguang.springbootmybatis.utils.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Create by lida
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestControllerTest {

    @Autowired
    TestController testController;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    WebConfig webConfig;

    @Test
    public void packet() {

        redisTemplate.opsForValue().set("redPacketSize",0);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 30000; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    testController.packet(1,1);
//                    log.error("2222222222");
                }
            });
        }
        try {
            Thread.sleep(120*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}