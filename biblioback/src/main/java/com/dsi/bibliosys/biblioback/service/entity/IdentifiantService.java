package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.repository.IdentifiantRepository;

/**
 * Classe fournissant les services de l'entité business Identifiant.
 */
@Service
public class IdentifiantService implements CrudService<Identifiant, Integer> {

	private final IdentifiantRepository identifiantRepository;

	@Autowired
	private IdentifiantService(IdentifiantRepository identifiantRepository) {
		this.identifiantRepository = identifiantRepository;
	}

	@Override
	public JpaRepository<Identifiant, Integer> getRepository() {
		return identifiantRepository;
	}

	@Override
	public Identifiant create() {
		return new Identifiant();
	}

}
