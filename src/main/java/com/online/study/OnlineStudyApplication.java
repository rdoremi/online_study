package com.online.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.online.study.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OnlineStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStudyApplication.class, args);
	}

}
