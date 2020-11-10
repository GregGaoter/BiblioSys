package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Personnel;
import com.dsi.bibliosys.biblioback.repository.PersonnelRepository;

/**
 * Classe fournissant les services de l'entité business Personnel.
 */
@Service
public class PersonnelService implements CrudService<Personnel, Integer> {

	private final PersonnelRepository personnelRepository;

	@Autowired
	public PersonnelService(PersonnelRepository personnelRepository) {
		this.personnelRepository = personnelRepository;
	}

	@Override
	public JpaRepository<Personnel, Integer> getRepository() {
		return personnelRepository;
	}

	@Override
	public Personnel create() {
		return new Personnel();
	}

}
