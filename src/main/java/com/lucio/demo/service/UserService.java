package com.lucio.demo.service;

import com.lucio.demo.bean.UserInfo;
import com.lucio.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserInfo queryUserInfo(Integer id){
        return userMapper.getUserById(id);
    }
}
