package com.lucio.demo.service;

import com.lucio.demo.bean.UserInfo;
import com.lucio.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo queryUserInfo(Integer id){
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
