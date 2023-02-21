package com.lucio.demo.controller;

import com.lucio.demo.bean.Response;
import com.lucio.demo.bean.UserInfo;
import com.lucio.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping(value = "user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/getInfo/{id}",method = RequestMethod.GET)
    public Response<UserInfo> getUserInfo(@PathVariable("id") Integer id){
        logger.info("getUserInfo id=" + id);
        UserInfo info = userService.queryUserInfo(id);
        return new Response<>(Response.SUCCESS,Response.SUCCESS,info);
    }
}
