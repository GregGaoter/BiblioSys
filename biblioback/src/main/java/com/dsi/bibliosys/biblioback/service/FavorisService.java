package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.repository.FavorisRepository;

/**
 * Classe fournissant les services de l'entité business Favoris.
 */
@Service
public class FavorisService implements CrudService<Favoris, Integer> {

	private final FavorisRepository favorisRepository;

	@Autowired
	public FavorisService(FavorisRepository favorisRepository) {
		this.favorisRepository = favorisRepository;
	}

	@Override
	public JpaRepository<Favoris, Integer> getRepository() {
		return favorisRepository;
	}

	@Override
	public Favoris create() {
		return new Favoris();
	}

}
