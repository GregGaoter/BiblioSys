package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.repository.ProfilRepository;

/**
 * Classe fournissant les services de l'entité business Profil.
 */
@Service
public class ProfilService implements CrudService<Profil, Integer> {

	private final ProfilRepository profilRepository;

	@Autowired
	private ProfilService(ProfilRepository profilRepository) {
		this.profilRepository = profilRepository;
	}

	@Override
	public JpaRepository<Profil, Integer> getRepository() {
		return profilRepository;
	}

	@Override
	public Profil create() {
		return new Profil();
	}

}
