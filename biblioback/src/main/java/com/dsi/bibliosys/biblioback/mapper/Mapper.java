package com.dsi.bibliosys.biblioback.mapper;

import lombok.NonNull;

/**
 * Interface de service fournissant les opérations de mapping entre les entités
 * business et les entités DTO.
 *
 * @param <E> Type de l'entité business.
 * @param <D> Type de l'entité DTO.
 */
public interface Mapper<E, D> {

	/**
	 * Map les attributs de l'entité business dans l'entité DTO.
	 * 
	 * @param source L'entité business.
	 * @return Une nouvelle instance de l'entité DTO mappée avec les attributs de
	 *         l'entité business.
	 */
	public D mapToDto(@NonNull E source);

	/**
	 * Map les attributs de l'entité DTO dans l'entité business.
	 * 
	 * @param source L'entité DTO.
	 * @return Une nouvelle instance de l'entité business mappée avec les attributs
	 *         de l'entité DTO.
	 * @throws NotNullIdException si l'id de la source n'est pas null.
	 */
	public E mapToEntity(@NonNull D source);

}
