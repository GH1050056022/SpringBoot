package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@EnableAsync
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean //配置docket以配置Swagger具体参数
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
	}


	//配置文档信息
	private ApiInfo apiInfo() {
		Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
		return new ApiInfo(
				"Swagger学习", // 标题
				"学习演示如何配置Swagger", // 描述
				"v1.0", // 版本
				"http://terms.service.url/组织链接", // 组织链接
				contact, // 联系人信息
				"Apach 2.0 许可", // 许可
				"许可链接", // 许可连接
				new ArrayList<>()// 扩展
		);
	}
}
