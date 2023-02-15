package com.lucio.demo.controller;

import com.lucio.demo.bean.UserInfo;
import com.lucio.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: HelloController
 * @Author: lucio
 * @Description:
 * @Date: 2021/6/29 16:48
 * @Version: 1.0
 */

@RestController
@EnableSwagger2
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World LucioZero";
    }

    @RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public UserInfo getUserInfo(@PathVariable("id") Integer id){
        return userService.queryUserInfo(id);
    }

}
