package com.shiguang.springbootmybatis.dao;

import com.shiguang.springbootmybatis.entity.UserRedPacket;

public interface UserRedPacketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRedPacket record);

    int insertSelective(UserRedPacket record);

    UserRedPacket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRedPacket record);

    int updateByPrimaryKey(UserRedPacket record);
}