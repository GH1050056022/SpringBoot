package com.lucio.demo.controller;

import com.lucio.demo.bean.CookbookInfo;
import com.lucio.demo.bean.Response;
import com.lucio.demo.service.CookbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping(value = "api/cookbook")
public class CookbookInfoController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CookbookService cookbookService;

    @RequestMapping(value = "/create/{token}",method = RequestMethod.POST)
    public Response<String> create(@PathVariable String token,
                                         @RequestBody CookbookInfo cookbookInfo){
        cookbookService.create(cookbookInfo);
        return new Response<String>(Response.SUCCESS,Response.SUCCESS,"成功");
    }

    @RequestMapping(value = "/update/{token}",method = RequestMethod.POST)
    public Response<String> update(@PathVariable String token,
                                   @RequestBody CookbookInfo cookbookInfo){
        cookbookService.update(cookbookInfo);
        return new Response<String>(Response.SUCCESS,Response.SUCCESS,"成功");
    }

    @RequestMapping(value = "/getInfo/{id}",method = RequestMethod.GET)
    public Response<CookbookInfo> getUserInfo(@PathVariable("id") Integer id){
        logger.info("getUserInfo id=" + id);
        CookbookInfo info = cookbookService.query(id);
        return new Response<CookbookInfo>(Response.SUCCESS,Response.SUCCESS,info);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public Response<String> delete(@PathVariable("id") Integer id){
        logger.info("getUserInfo id=" + id);
        cookbookService.delete(id);
        return new Response<String>(Response.SUCCESS,Response.SUCCESS,"成功");
    }
   
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Response<List<CookbookInfo>> list(@RequestBody CookbookInfo cookbookInfo){
        List<CookbookInfo> list = cookbookService.list(cookbookInfo);
        return new Response<>(Response.SUCCESS,Response.SUCCESS,list);
    }

}
