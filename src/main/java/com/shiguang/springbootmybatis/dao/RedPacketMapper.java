package com.shiguang.springbootmybatis.dao;

import com.shiguang.springbootmybatis.entity.RedPacket;
import org.apache.ibatis.annotations.Param;

public interface RedPacketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedPacket record);

    int insertSelective(RedPacket record);

    RedPacket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedPacket record);

    int updateByPrimaryKey(RedPacket record);

    /**
     * 减库存
     *
     * @param id
     * @return
     */
    int decreaseRedPacket(Integer id);

    /**
     * 减库存
     *
     * @param id
     * @return
     */
    int decreaseRedPacketForVersion(@Param("id") Integer id, @Param("version") Integer version);
}