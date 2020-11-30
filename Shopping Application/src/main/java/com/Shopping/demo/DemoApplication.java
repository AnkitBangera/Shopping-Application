package com.Shopping.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.Shopping.demo.Util.EmailUtil;

@SpringBootApplication
@EnableEurekaClient
public class DemoApplication {
	@Bean
	public EmailUtil getEmailUtil() {
		return new EmailUtil();
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
