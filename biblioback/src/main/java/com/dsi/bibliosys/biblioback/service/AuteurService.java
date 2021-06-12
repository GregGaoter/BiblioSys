package com.dsi.bibliosys.biblioback.service;

import static com.dsi.bibliosys.biblioback.repository.specification.EcritureLivreSpecification.livreIdEqual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.repository.AuteurRepository;
import com.dsi.bibliosys.biblioback.repository.EcritureLivreRepository;

/**
 * Classe fournissant les services de l'entité business Auteur.
 */
@Service
public class AuteurService implements CrudService<Auteur, Integer> {

	private final AuteurRepository auteurRepository;
	private final EcritureLivreRepository ecritureLivreRepository;

	@Autowired
	public AuteurService(AuteurRepository auteurRepository, EcritureLivreRepository ecritureLivreRepository) {
		this.auteurRepository = auteurRepository;
		this.ecritureLivreRepository = ecritureLivreRepository;
	}

	@Override
	public JpaRepository<Auteur, Integer> getRepository() {
		return auteurRepository;
	}

	@Override
	public Auteur create() {
		return new Auteur();
	}

	public Auteur findByLivreId(Integer id) {
		EcritureLivre ecritureLivre = ecritureLivreRepository.findOne(livreIdEqual(id)).orElseThrow();
		return auteurRepository.findById(ecritureLivre.getAuteur().getId()).orElseThrow();
	}

}
