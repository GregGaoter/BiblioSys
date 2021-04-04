package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.repository.IdentifiantRepository;
import com.dsi.bibliosys.biblioback.repository.specification.IdentifiantSpecification;

/**
 * Classe fournissant les détails de l'utilisateur.
 */
@Service
public class DetailsUtilisateurService implements UserDetailsService {

	private final IdentifiantRepository identifiantRepository;

	@Autowired
	public DetailsUtilisateurService(IdentifiantRepository identifiantRepository) {
		this.identifiantRepository = identifiantRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Identifiant identifiant = identifiantRepository.findOne(IdentifiantSpecification.emailEqual(email))
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));
		String[] roles = identifiant.getProfils().stream().map(profil -> profil.getRole().getNom()).distinct()
				.toArray(String[]::new);
		return new User(identifiant.getEmail(), identifiant.getMotDePasse(), AuthorityUtils.createAuthorityList(roles));
	}

}
