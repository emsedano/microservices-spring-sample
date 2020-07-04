package com.themscode.users.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.themscode.commons.users.entities.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@RestResource(path="by-username")
	public User findByUsername(@Param("username") String username);
	
	@Query("select u from User u where u.username=?1")
	@RestResource(path="with-username")
	public User getWithUsername(@Param("username") String username);

}
