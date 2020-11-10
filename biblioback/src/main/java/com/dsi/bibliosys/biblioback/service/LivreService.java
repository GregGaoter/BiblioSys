package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.repository.LivreRepository;

/**
 * Classe fournissant les services de l'entité business Livre.
 */
@Service
public class LivreService implements CrudService<Livre, Integer> {

	private final LivreRepository livreRepository;

	@Autowired
	public LivreService(LivreRepository livreRepository) {
		this.livreRepository = livreRepository;
	}

	@Override
	public JpaRepository<Livre, Integer> getRepository() {
		return livreRepository;
	}

	@Override
	public Livre create() {
		return new Livre();
	}

}
