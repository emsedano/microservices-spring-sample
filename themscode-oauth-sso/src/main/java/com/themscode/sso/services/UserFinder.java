package com.themscode.sso.services;

import com.themscode.commons.users.entities.User;

public interface UserFinder {

	User findByUserName(String username);
}
