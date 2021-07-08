package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AsyncController
 * @Description:
 * @Date: 2021/7/1 17:20
 * @Version: 1.0
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/async/hello")
    public String hello(){
        asyncService.hello();
        return "success";
    }
}
