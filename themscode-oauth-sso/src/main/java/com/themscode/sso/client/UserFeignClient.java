package com.themscode.sso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.themscode.commons.users.entities.User;

@FeignClient("themscode-users")
public interface UserFeignClient {
	
	@GetMapping("/users/search/by-username")
	public User byUserName(@RequestParam String username);
	
	@PutMapping("/users/{id}")
	public User update(@RequestBody User user, @PathVariable Long id);

}
