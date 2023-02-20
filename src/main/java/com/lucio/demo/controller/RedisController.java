package com.lucio.demo.controller;

import com.lucio.demo.bean.Response;
import com.lucio.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.TimeUnit;


@RestController
@EnableSwagger2
@RequestMapping(value = "redis")
public class RedisController {


    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/set/{key}/{value}",method = RequestMethod.GET)
    public Response<String> set(@PathVariable("key") String key, @PathVariable("value") String value){
        redisUtil.set(key,value);
        redisUtil.expire(key,20L, TimeUnit.SECONDS);
        return new Response<>(Response.SUCCESS,Response.SUCCESS,"成功");
    }



    @RequestMapping(value = "/get/{key}",method = RequestMethod.GET)
    public Object get(@PathVariable("key") String key){
        Object value = redisUtil.get(key);
        return value;
    }
}
