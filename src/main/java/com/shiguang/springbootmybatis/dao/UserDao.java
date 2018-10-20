package com.shiguang.springbootmybatis.dao;


import com.shiguang.springbootmybatis.entity.UserInfo;

public interface UserDao {

    UserInfo selectByPrimaryKey(String id);

}