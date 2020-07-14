package com.themscode.api.gateway.auth;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RefreshScope
@EnableResourceServer
public class SecuredResourcesConfig extends ResourceServerConfigurerAdapter {
	
	Logger log = LoggerFactory.getLogger(SecuredResourcesConfig.class);

	@Value("${config.security.oauth.jwt.key}") String signingKey;

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
		.antMatchers(
				HttpMethod.POST,
					"/api/data/**", 
					"/api/service/**", 
					"/api/us/**").hasRole("ADMIN")
		// updates
		.antMatchers(
				HttpMethod.PUT,
					"/api/data/**", 
					"/api/service/**", 
					"/api/us/**").hasRole("ADMIN")
		// delete
		.antMatchers(
				HttpMethod.DELETE,
					"/api/data/**", 
					"/api/service/**", 
					"/api/us/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.cors()
			.configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		
		corsConfig.setAllowedMethods(
				Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")
			);
		
		corsConfig.setAllowCredentials(true);
		
		corsConfig.setAllowedHeaders(
				Arrays.asList("Authorization", "Content-Type")
			);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		return tokenStore;
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		log.debug("Using signingKey: %s", signingKey);
		tokenConverter.setSigningKey(signingKey);
		return tokenConverter;
	}

	
}
