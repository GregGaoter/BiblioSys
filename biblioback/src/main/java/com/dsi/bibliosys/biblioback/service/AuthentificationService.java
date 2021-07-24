package com.dsi.bibliosys.biblioback.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService implements Authentification {

	@Override
	public Authentication getAuthentification() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
