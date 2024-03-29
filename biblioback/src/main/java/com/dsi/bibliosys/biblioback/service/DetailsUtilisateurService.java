package com.dsi.bibliosys.biblioback.service;

import static com.dsi.bibliosys.biblioback.repository.specification.IdentifiantSpecification.emailEqual;
import static com.dsi.bibliosys.biblioback.repository.specification.ProfilSpecification.identifiantIdEqual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.repository.IdentifiantRepository;
import com.dsi.bibliosys.biblioback.repository.ProfilRepository;

/**
 * Classe fournissant les détails de l'utilisateur.
 */
@Service
public class DetailsUtilisateurService implements UserDetailsService {

	private final IdentifiantRepository identifiantRepository;
	private final ProfilRepository profilRepository;

	@Autowired
	public DetailsUtilisateurService(IdentifiantRepository identifiantRepository, ProfilRepository profilRepository) {
		this.identifiantRepository = identifiantRepository;
		this.profilRepository = profilRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Identifiant identifiant = identifiantRepository.findOne(emailEqual(email))
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));
		List<Profil> profils = profilRepository.findAll(identifiantIdEqual(identifiant.getId()));
		String[] roles = profils.stream().map(profil -> profil.getRole().getNom()).distinct().toArray(String[]::new);
		return new User(identifiant.getEmail(), identifiant.getMotDePasse(), AuthorityUtils.createAuthorityList(roles));
	}

}
