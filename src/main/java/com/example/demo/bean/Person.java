package com.example.demo.bean;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

/**
 * @ClassName: Person
 * @Description:
 * @Date: 2021/6/30 14:09
 * @Version: 1.0
 */

@Component //注册bean
@Validated  //数据校验
public class Person {

    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String name;
}
