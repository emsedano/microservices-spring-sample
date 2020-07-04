package com.themscode.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EntityScan({"com.themscode.commons.users.entities"})
@SpringBootApplication
@EnableEurekaClient
public class ThemscodeUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemscodeUsersApplication.class, args);
	}

}
