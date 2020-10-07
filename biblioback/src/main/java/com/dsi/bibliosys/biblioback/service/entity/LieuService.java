package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.repository.LieuRepository;

/**
 * Classe fournissant les services de l'entité business Lieu.
 */
@Service
public class LieuService implements CrudService<Lieu, Integer> {

	private final LieuRepository lieuRepository;

	@Autowired
	private LieuService(LieuRepository lieuRepository) {
		this.lieuRepository = lieuRepository;
	}

	@Override
	public JpaRepository<Lieu, Integer> getRepository() {
		return lieuRepository;
	}

	@Override
	public Lieu create() {
		return new Lieu();
	}

}
