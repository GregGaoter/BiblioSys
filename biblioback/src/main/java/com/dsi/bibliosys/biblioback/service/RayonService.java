package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Rayon;
import com.dsi.bibliosys.biblioback.repository.RayonRepository;

/**
 * Classe fournissant les services de l'entité business Rayon.
 */
@Service
public class RayonService implements CrudService<Rayon, Integer> {

	private final RayonRepository rayonRepository;

	@Autowired
	public RayonService(RayonRepository rayonRepository) {
		this.rayonRepository = rayonRepository;
	}

	@Override
	public JpaRepository<Rayon, Integer> getRepository() {
		return rayonRepository;
	}

	@Override
	public Rayon create() {
		return new Rayon();
	}

}
