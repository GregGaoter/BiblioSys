package com.dsi.bibliosys.biblioback.data.authentification;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Setter;

/**
 * Classe qui contient les champs de détails de l'utilisateur de Spring Security.
 */
@Setter
public class JwtDetailsUtilisateur implements UserDetails {

	private static final long serialVersionUID = 4553186436889248195L;

	private String username;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
