package com.themscode.api.gateway.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class SecuredResourcesConfig extends ResourceServerConfigurerAdapter {


	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}


	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/sso/oauth/token/**").permitAll()
		// read (all)
		.antMatchers(
				HttpMethod.GET, 
				"/api/data/**", 
				"/api/service/**", 
				"/api/us/**"
		).permitAll()
		// create
		.antMatchers(HttpMethod.POST,
				"/api/data/**", 
				"/api/service/**", 
				"/api/us/**").hasRole("ADMIN")
		// updates
		.antMatchers(HttpMethod.PUT,
				"/api/data/**", 
				"/api/service/**", 
				"/api/us/**").hasRole("ADMIN")
		// delete
		.antMatchers(HttpMethod.DELETE,
				"/api/data/**", 
				"/api/service/**", 
				"/api/us/**").hasRole("ADMIN")
		.anyRequest().authenticated();
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		return tokenStore;
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("themscode-secret-holo");
		return tokenConverter;
	}

	
}
