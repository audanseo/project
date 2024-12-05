package com.ezen.massagemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan(basePackages = "com.ezen.massagemall.**")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MassagemallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassagemallApplication.class, args);
	}

}
