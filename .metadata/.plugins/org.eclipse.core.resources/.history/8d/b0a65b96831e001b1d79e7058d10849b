package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.UsagerRepository;

/**
 * Classe fournissant les services de l'entité business Usager.
 */
@Service
public class UsagerService implements CrudService<Usager, Integer> {

	@Autowired
	private UsagerRepository usagerRepository;

	@Override
	public JpaRepository<Usager, Integer> getRepository() {
		return usagerRepository;
	}

	@Override
	public Usager create() {
		return new Usager();
	}

}
