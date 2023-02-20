package com.lucio.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        //1.redisTemplate要想具备访问数据库的能力，就得创建连接；连接是由连接工厂创建的，所以把连接工厂注入进来
        RedisTemplate<String, Object> template = new RedisTemplate<>();   //1.1 在参数上声明一个连接工厂，上面括号;然后实例化这个bean
        template.setConnectionFactory(factory); //1.2 把工厂设置给template，现在就有了访问数据库的能力

        //2.我们配这个template主要配的是序列化方式
        //因为写的程序是java程序，得到的数据是java类型的数据，最终要把数据存到redis数据库里，所以要指定一种序列化的方式，或者说数据转换的方式
        //2.1 设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //2.2 设置value的序列化方式
        template.setValueSerializer(RedisSerializer.json());
        /*有一个value比较特殊，就是 hash。这个value本身就是一个hash，hash又说明key、value*/
        //2.3 设置 hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //2.4 设置 hash的value的序列化方式
        template.setHashKeySerializer(RedisSerializer.json());

        template.afterPropertiesSet();
        return template;
    }
}

