package com.themscode.core.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.themscode.commons.models.entity"})
public class ThemscodeProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemscodeProductServiceApplication.class, args);
	}

}
