package com.dsi.bibliosys.biblioback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.repository.EcritureLivreRepository;

/**
 * Classe fournissant les services de l'entité business EcritureLivre.
 */
@Service
public class EcritureLivreService implements CrudService<EcritureLivre, Integer> {

	private final EcritureLivreRepository ecritureLivreRepository;

	@Autowired
	public EcritureLivreService(EcritureLivreRepository ecritureLivreRepository) {
		this.ecritureLivreRepository = ecritureLivreRepository;
	}

	@Override
	public JpaRepository<EcritureLivre, Integer> getRepository() {
		return ecritureLivreRepository;
	}

	@Override
	public EcritureLivre create() {
		return new EcritureLivre();
	}
	
	public List<EcritureLivre> findAll(Specification<EcritureLivre> spec){
		return ecritureLivreRepository.findAll(spec);
	}

}
