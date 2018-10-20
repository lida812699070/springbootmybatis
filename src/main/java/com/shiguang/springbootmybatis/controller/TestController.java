package com.shiguang.springbootmybatis.controller;

import com.shiguang.springbootmybatis.annotion.CheckLoginAnnotation;
import com.shiguang.springbootmybatis.dao.OrderDetailMapper;
import com.shiguang.springbootmybatis.dao.OrderMasterMapper;
import com.shiguang.springbootmybatis.dao.ProductInfoMapper;
import com.shiguang.springbootmybatis.dao.UserDao;
import com.shiguang.springbootmybatis.entity.OrderDetail2;
import com.shiguang.springbootmybatis.service.UserRedPacketService;
import com.shiguang.springbootmybatis.service.impl.UserRedPacketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Create by lida
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    UserDao userDao;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Autowired
    UserRedPacketService userRedPacketService;


    @GetMapping("/test")
    @CheckLoginAnnotation
    public String test(@RequestParam(value = "id", required = false) String id) {
        OrderDetail2 orderDetail = orderDetailMapper.selectByPrimaryKey2(id);
//        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(id);
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId(id);
//        productInfo.setProductName("茶杯");
//        productInfo.setProductPrice(new BigDecimal(22.2));
//        productInfo.setProductStock(80);
//        productInfo.setProductStatus(0);
//        productInfo.setProductDescription("sdaas");
//        productInfo.setProductIcon("www.ccsda.com");
//        productInfo.setCategoryType(0);
//        productInfoMapper.insertSelective(productInfo);
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId("123");
//        productInfo.setProductPrice(new BigDecimal(666));
//        productInfoMapper.updateByPrimaryKeySelective(productInfo);

        log.info("orderMaster{}", orderDetail);
        return "success";
    }

    @GetMapping("/packet")
    @CheckLoginAnnotation
    public String packet(@RequestParam("redPacketId") Integer redPacketId,
                         @RequestParam("userId") Integer userId) {
        //保存
        int i = userRedPacketService.grapRedPacket(redPacketId, userId);
        log.error(i == 0 ? "失败了" : "成功了 i=" + i);
        return "success";
    }
}
