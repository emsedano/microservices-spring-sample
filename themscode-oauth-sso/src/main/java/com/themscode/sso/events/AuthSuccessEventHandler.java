package com.themscode.sso.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.themscode.commons.users.entities.User;
import com.themscode.sso.services.UserFinder;

import feign.FeignException;

@Component
public class AuthSuccessEventHandler implements AuthenticationEventPublisher {

	Logger log = LoggerFactory.getLogger(AuthSuccessEventHandler.class);
	
	@Autowired
	private UserFinder userService;
	
	@Autowired
	private Environment env;
	
	@Value("${config.security.oauth.client.id}")
	private String clientId;

	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();

		if (notClientIdEvent(authentication)) {
			log.info(String.format("Authenticated as %s", user.getUsername()));
			
			User dbUser = userService.findByUserName(authentication.getName());
			log.info(String.format("User found s %s", dbUser.getUsername()));
			if (dbUser.getAttempts() != null && dbUser.getAttempts() > 0) {
				log.info(String.format(String.format("User has %s prev failure attempts, resseting value to 'null'", dbUser.getUsername())));
				dbUser.setAttempts(null);
				userService.update(dbUser, dbUser.getId());
			}
		}
		
	}


	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		if (notClientIdEvent(authentication)) {
			try {
				User user = userService.findByUserName(authentication.getName());
				Integer attempts = user.getAttempts();
				log.info(String.format("%s attempts found", attempts));
				if (attempts == null) {
					attempts = 1;
					user.setAttempts(attempts);
				} else {
					attempts++;
					user.setAttempts(attempts);
				}
				log.info(String.format("user %s has made %s attempts", user.getUsername(), attempts));
				if (attempts > 3) {
					log.error(String.format("user %s has been disabled due max attempt limit reach", user.getUsername()));
					user.setActive(false);
				}
				userService.update(user, user.getId());
			} catch (FeignException e) {
				log.error(String.format("User %s does not exist", authentication.getName()));
			}
		}
		log.warn("***********************************************************************");
		log.warn(String.format("*******   Authentication failed due to %s   *******", exception.getMessage()));
		log.warn("***********************************************************************");
		
	}
	
	private boolean notClientIdEvent(Authentication authentication) {
		return !authentication.getName().equalsIgnoreCase(clientId);
	}

}
