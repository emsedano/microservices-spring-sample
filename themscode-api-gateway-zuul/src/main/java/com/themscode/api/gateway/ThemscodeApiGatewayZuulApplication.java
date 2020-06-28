package com.themscode.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ThemscodeApiGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemscodeApiGatewayZuulApplication.class, args);
	}

}
