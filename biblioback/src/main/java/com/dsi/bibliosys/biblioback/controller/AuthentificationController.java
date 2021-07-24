package com.dsi.bibliosys.biblioback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.biblioback.app.configuration.JwtTokenUtil;
import com.dsi.bibliosys.biblioback.data.authentification.JwtReponse;
import com.dsi.bibliosys.biblioback.data.authentification.JwtRequete;
import com.dsi.bibliosys.biblioback.data.dto.UsagerDto;
import com.dsi.bibliosys.biblioback.mapper.UsagerMapper;
import com.dsi.bibliosys.biblioback.service.DetailsUtilisateurService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

import lombok.NonNull;
import reactor.core.publisher.Mono;

/**
 * Cette classe contient l'API REST de l'authentification qui reçoit le nom
 * d'utilisateur et le mot de passe pour l'authentification et renvoie le token
 * JWT en cas de réponse réussie.
 */
@RestController
public class AuthentificationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private DetailsUtilisateurService detailsUtilisateurService;
	@Autowired
	private UsagerService usagerService;
	@Autowired
	private UsagerMapper usagerMapper;

	@PostMapping("/authentification")
	public ResponseEntity<?> creerTokenAuthentification(@RequestBody JwtRequete requeteAuthentification)
			throws Exception {
		authentifier(requeteAuthentification.getUsername(), requeteAuthentification.getPassword());
		final UserDetails userDetails = detailsUtilisateurService
				.loadUserByUsername(requeteAuthentification.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtReponse(token));
	}

	private void authentifier(@NonNull String username, @NonNull String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("Utilisateur désactivé", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Identifiants invalides", e);
		}
	}

	@GetMapping("/authentification/usager")
	public ResponseEntity<Mono<UsagerDto>> getAuthenticateUsager() {
		return ResponseEntity.ok(Mono.just(usagerMapper.mapToDto(usagerService.findAuthenticateUsager())));
	}

}
