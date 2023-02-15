package com.lucio.demo.mapper;

import com.lucio.demo.bean.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserInfo> queryList();

    UserInfo getUserById(Integer id);
}
