package com.lucio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: BLZController
 * @Author: lucio
 * @Description:
 * @Date: 2021/6/30 16:49
 * @Version: 1.0
 */
@RestController
public class BLZController {

    @Autowired
    RestTemplate restTemplate;

    String url = "https://cn.battle.net";


    @RequestMapping(value = "/blz",method = RequestMethod.GET)
    public String test(){
        return restTemplate.getForObject(url + "/wow/boss", String.class);
    }
}
