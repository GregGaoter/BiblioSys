package com.dsi.bibliosys.biblioback.app.configuration;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Gère les exceptions et chaque fois que le token JWT n'est pas validé, lève
 * une exception "Non autorisé".
 */
@Component
public class JwtPointEntreeAuthentification implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -3429856710888505459L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non autorisé");
	}

}
