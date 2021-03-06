package com.dsi.bibliosys.biblioback.app.configuration;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe utilitaire pour créer et valider le token JWT.
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3855342133243345122L;
	public static final long JWT_TOKEN_VALIDITY = 1000 * 604800;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Récupère le nom d'utilisateur du token JWT.
	 * 
	 * @param token JWT
	 * @return Le nom d'utilisateur
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	/**
	 * Récupère la date de publication du token JWT.
	 * 
	 * @param token JWT
	 * @return La date de publication
	 */
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	/**
	 * Récupère la date d'expiration du token JWT.
	 * 
	 * @param token JWT
	 * @return La date d'expiration
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Récupère les informations du token JWT avec la clef secrète.
	 * 
	 * @param token JWT
	 * @return JWT body
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Vérifie si le token JWT a expiré.
	 * 
	 * @param token JWT
	 * @return true si le token JWT a expiré, false sinon.
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	private Boolean ignoreTokenExpiration(String token) {
		return false;
	}

	/**
	 * Génère le token JWT pour l'utilisateur.
	 * 
	 * @param userDetails Les détails de l'utilisateur
	 * @return Le token JWT
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String username = userDetails.getUsername();
		return doGenerateToken(claims, username);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	/**
	 * Valide le token JWT.
	 * 
	 * @param token       JWT
	 * @param userDetails Les détails de l'utilisateur
	 * @return true si le token JWT est valide, false sinon.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
