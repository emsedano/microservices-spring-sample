package com.themscode.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.themscode.commons.users.entities.Role;
import com.themscode.commons.users.entities.User;


@Configuration
public class UserRepositoryConfig implements RepositoryRestConfigurer {

	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(User.class, Role.class);
	}	
}
