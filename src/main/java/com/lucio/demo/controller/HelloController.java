package com.lucio.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: HelloController
 * @Description:
 * @Date: 2021/6/29 16:48
 * @Version: 1.0
 */

@RestController
@EnableSwagger2
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World LucioZero";
    }

}
