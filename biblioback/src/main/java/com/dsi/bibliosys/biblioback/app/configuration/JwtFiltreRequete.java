package com.dsi.bibliosys.biblioback.app.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dsi.bibliosys.biblioback.app.HasLogger;
import com.dsi.bibliosys.biblioback.service.DetailsUtilisateurService;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Le but de cette classe est de gérer le filtrage de la requête côté client.
 * C'est ici que toutes les requêtes viendront en premier avant l'API REST. Si la
 * validation du token JWT est réussie, l'API REST reçoit une requête.
 */
@Component
public class JwtFiltreRequete extends OncePerRequestFilter implements HasLogger {

	@Autowired
	private DetailsUtilisateurService detailsUtilisateurService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("BIBLIOSYS-AUTHORIZATION");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				getLogger().error("Impossible d'obtenir le token JWT.");
			} catch (ExpiredJwtException e) {
				getLogger().warn("Le token JWT a expiré.");
			}
		} else {
			getLogger().warn("Le token JWT ne commence pas par Bearer.");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = detailsUtilisateurService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
