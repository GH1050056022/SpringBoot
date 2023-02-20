package com.lucio.demo.controller;

import com.lucio.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "hello")
public class HelloController {

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String hello() {
        return "Hello World LucioZero";
    }

}
