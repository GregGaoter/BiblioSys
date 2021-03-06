package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.repository.AdresseRepository;

/**
 * Classe fournissant les services de l'entité business Adresse.
 */
@Service
public class AdresseService implements CrudService<Adresse, Integer> {

	private final AdresseRepository adresseRepository;

	@Autowired
	public AdresseService(AdresseRepository adresseRepository) {
		this.adresseRepository = adresseRepository;
	}

	@Override
	public JpaRepository<Adresse, Integer> getRepository() {
		return adresseRepository;
	}

	@Override
	public Adresse create() {
		return new Adresse();
	}

}
