package com.themscode.sso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.themscode.commons.users.entities.User;
import com.themscode.sso.client.UserFeignClient;

import feign.FeignException;

@Service
public class UserService implements UserFinder, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserFeignClient client;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			User user = findByUserName(username);

			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.peek(role -> log.info(String.format("Granted Role: %s", role.getAuthority()))).collect(Collectors.toList());

			log.info(String.format("User logged in as %s with %s", user.getEmail(), authorities.toString()));

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
					true, true, authorities);

		} catch (FeignException e) {
			String message = String.format("User %s not found", username);
			log.error(message);
			throw new UsernameNotFoundException(message);
		}
	}

	@Override
	public User findByUserName(String username) {
		return client.byUserName(username);
	}

	public User update(User user, Long id) {
		return client.update(user, id);
	}

}
