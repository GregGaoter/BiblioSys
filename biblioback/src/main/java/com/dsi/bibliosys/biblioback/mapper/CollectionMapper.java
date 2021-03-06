package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.CollectionDto;
import com.dsi.bibliosys.biblioback.data.entity.Collection;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business
 * Collection et l'entité DTO CollectionDto.
 */
@Component
public class CollectionMapper extends AbstractMapper implements Mapper<Collection, CollectionDto> {

	@Override
	public CollectionDto mapToDto(@NonNull Collection source) {
		CollectionDto collectionDto = new CollectionDto();
		collectionDto.setId(source.getId());
		collectionDto.setNom(source.getNom());
		return collectionDto;
	}

	@Override
	public Collection mapToEntity(@NonNull CollectionDto source) {
		Collection collection = new Collection();
		collection.setNom(source.getNom());
		return collection;
	}

}
