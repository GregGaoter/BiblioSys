package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.repository.CollectionRepository;

/**
 * Classe fournissant les services de l'entité business Collection.
 */
@Service
public class CollectionService implements CrudService<Collection, Integer> {

	private final CollectionRepository collectionRepository;

	@Autowired
	public CollectionService(CollectionRepository collectionRepository) {
		this.collectionRepository = collectionRepository;
	}

	@Override
	public JpaRepository<Collection, Integer> getRepository() {
		return collectionRepository;
	}

	@Override
	public Collection create() {
		return new Collection();
	}

}
