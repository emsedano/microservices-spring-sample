package com.themscode.sso.authconf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.themscode.commons.users.entities.User;
import com.themscode.sso.services.UserFinder;

@Component
public class TokenClaimsAdapter implements TokenEnhancer {
	
	@Autowired
	private UserFinder userFinderService; 

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String,  Object> data = new HashMap<>();
		
		String username = authentication.getName();
		
		User user = userFinderService.findByUserName(username);
		data.put("name",user.getName());
		data.put("email",user.getEmail());
		data.put("active",user.getActive());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(data);
		return accessToken;
	}

}
