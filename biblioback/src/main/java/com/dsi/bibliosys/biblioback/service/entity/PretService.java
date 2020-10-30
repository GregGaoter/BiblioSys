package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.repository.PretRepository;

/**
 * Classe fournissant les services de l'entité business Pret.
 */
@Service
public class PretService implements CrudService<Pret, Integer> {

	private final PretRepository pretRepository;

	@Autowired
	public PretService(PretRepository pretRepository) {
		this.pretRepository = pretRepository;
	}

	@Override
	public JpaRepository<Pret, Integer> getRepository() {
		return pretRepository;
	}

	@Override
	public Pret create() {
		return new Pret();
	}

}
