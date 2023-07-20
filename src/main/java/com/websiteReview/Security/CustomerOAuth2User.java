package com.websiteReview.Security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomerOAuth2User implements OAuth2User{

	private OAuth2User oAuth2User;
	
	
	public OAuth2User getoAuth2User() {
		return oAuth2User;
	}

	public void setoAuth2User(OAuth2User oAuth2User) {
		this.oAuth2User = oAuth2User;
	}

	public CustomerOAuth2User() {
		super();
	}

	public CustomerOAuth2User(OAuth2User oAuth2User) {
		super();
		this.oAuth2User = oAuth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}

}
