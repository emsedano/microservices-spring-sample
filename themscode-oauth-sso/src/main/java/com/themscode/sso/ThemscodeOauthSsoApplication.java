package com.themscode.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ThemscodeOauthSsoApplication  {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ThemscodeOauthSsoApplication.class, args);
	}

	
//	public void run(String... args) throws Exception {
//		String password = "holo";
//		
//		for (int i = 0; i < 4; i++) {
//			String encripted = passwordEncoder.encode(password);
//			System.out.println(String.format("genrated: %s", encripted));
//		}
//		
//	}

}
