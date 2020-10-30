package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.repository.AuteurRepository;

/**
 * Classe fournissant les services de l'entité business Auteur.
 */
@Service
public class AuteurService implements CrudService<Auteur, Integer> {

	private final AuteurRepository auteurRepository;

	@Autowired
	public AuteurService(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository;
	}

	@Override
	public JpaRepository<Auteur, Integer> getRepository() {
		return auteurRepository;
	}

	@Override
	public Auteur create() {
		return new Auteur();
	}

}
