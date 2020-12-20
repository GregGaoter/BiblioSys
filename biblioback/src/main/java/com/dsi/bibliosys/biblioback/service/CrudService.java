package com.dsi.bibliosys.biblioback.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dsi.bibliosys.biblioback.app.HasLogger;

import lombok.NonNull;

/**
 * Interface de service fournissant les opérations CRUD sur les entités
 * business.
 *
 * @param <E> Type de l'entité business.
 * @param <I> Type de la clef primaire de l'entité business.
 */
public interface CrudService<E, I> extends HasLogger {

	/**
	 * Renvoie le repository de l'entité business.
	 * 
	 * @return Le repository de l'entité business.
	 */
	public JpaRepository<E, I> getRepository();

	/**
	 * Renvoie une instance de l'entité business avec les attributs initialisés à
	 * null.
	 * 
	 * @return Une instance de l'entité business avec les attributs initialisés à
	 *         null.
	 */
	public E create();

	/**
	 * Enregistre une entité business et synchronise instantanément les
	 * modifications dans la base de données.
	 * 
	 * @param entity L'entité business à enregistrer.
	 * @return L'entité business enregistrée.
	 */
	@Transactional
	public default E save(@NonNull E entity) {
		return getRepository().saveAndFlush(entity);
	}

	/**
	 * Met à jour une entité business et synchronise instantanément les
	 * modifications dans la base de données.
	 * 
	 * @param entitySource L'entité business source.
	 * @param id           L'id de l'entité business à mettre à jour.
	 * @return L'entité business avec l'id donné mise à jour.
	 */
	@Transactional
	public default E update(@NonNull E entitySource, @NonNull I id) {
		E entityTarget = load(id);
		BeanUtils.copyProperties(entitySource, entityTarget, "id");
		return save(entityTarget);
	}

	/**
	 * Supprime de la base de données l'entité business passée en argument.
	 * 
	 * @param entity L'entité business à supprimer.
	 */
	@Transactional
	public default void delete(@NonNull E entity) {
		getRepository().delete(entity);
	}

	/**
	 * Supprime de la base de données l'entité business d'id passé en argument.
	 * 
	 * @param id L'id de l'entité business à supprimer.
	 */
	@Transactional
	public default void deleteById(@NonNull I id) {
		getRepository().deleteById(id);
	}

	/**
	 * Renvoie le nombre d'entité business.
	 * 
	 * @return Le nombre d'entité business.
	 */
	public default long count() {
		return getRepository().count();
	}

	/**
	 * Renvoie une entité business par son id.
	 * 
	 * @param id L'id de l'entité business.
	 * @return L'entité business avec l'id donné.
	 * @throws EntityNotFoundException si l'entité business avec l'id donné n'est
	 *                                 pas trouvée.
	 */
	private E load(@NonNull I id) {
		E entity = getRepository().findById(id).orElse(null);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	/**
	 * Renvoie une entité business par son id.
	 * 
	 * @param id L'id de l'entité business.
	 * @return L'entité business avec l'id donné.
	 */
	public default E findById(@NonNull I id) {
		return load(id);
	}

	/**
	 * Renvoie toutes les entités business.
	 * 
	 * @return Toutes les entités business.
	 */
	public default List<E> findAll() {
		return getRepository().findAll();
	}

}
