package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.UsagerRepository;
import com.dsi.bibliosys.biblioback.repository.specification.UsagerSpecification;

/**
 * Classe fournissant les services de l'entité business Usager.
 */
@Service
public class UsagerService implements CrudService<Usager, Integer> {

	private final UsagerRepository usagerRepository;
	@Autowired
	private AuthentificationService authentificationService;

	@Autowired
	public UsagerService(UsagerRepository usagerRepository) {
		this.usagerRepository = usagerRepository;
	}

	@Override
	public JpaRepository<Usager, Integer> getRepository() {
		return usagerRepository;
	}

	@Override
	public Usager create() {
		return new Usager();
	}

	public Usager findAuthenticateUsager() {
		return usagerRepository
				.findOne(UsagerSpecification.emailEqual(authentificationService.getAuthentification().getName()))
				.orElseThrow();
	}

}
