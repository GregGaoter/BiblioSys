package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.repository.BibliothequeRepository;

/**
 * Classe fournissant les services de l'entité business Bibliotheque.
 */
@Service
public class BibliothequeService implements CrudService<Bibliotheque, Integer> {

	private final BibliothequeRepository bibliothequeRepository;

	@Autowired
	public BibliothequeService(BibliothequeRepository bibliothequeRepository) {
		this.bibliothequeRepository = bibliothequeRepository;
	}

	@Override
	public JpaRepository<Bibliotheque, Integer> getRepository() {
		return bibliothequeRepository;
	}

	@Override
	public Bibliotheque create() {
		return new Bibliotheque();
	}

}
