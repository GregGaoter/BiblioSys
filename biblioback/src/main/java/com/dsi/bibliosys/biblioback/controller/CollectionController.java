package com.dsi.bibliosys.biblioback.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.biblioback.data.dto.CollectionDto;
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.mapper.CollectionMapper;
import com.dsi.bibliosys.biblioback.service.CollectionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Collection.
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {

	/**
	 * Service de l'entité business Collection.
	 */
	@Autowired
	private CollectionService collectionService;

	/**
	 * Mapper entre l'entité business Collection et l'entité DTO CollectionDto.
	 */
	@Autowired
	private CollectionMapper collectionMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/collection".
	 * 
	 * @return Un CollectionDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<CollectionDto> create() {
		Collection collection = collectionService.create();
		CollectionDto collectionDto = collectionMapper.mapToDto(collection);
		return Mono.just(collectionDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/collection/{id}".
	 * 
	 * @return CollectionDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<CollectionDto>> readById(@PathVariable Integer id) {
		Collection collection = collectionService.findById(id);
		CollectionDto collectionDto = collectionMapper.mapToDto(collection);
		return collectionDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(collectionDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/collection/all".
	 * 
	 * @return Tous les CollectionDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<CollectionDto>> readAll() {
		List<Collection> collections = collectionService.findAll();
		List<CollectionDto> collectionsDto = collections.stream()
				.map(collection -> collectionMapper.mapToDto(collection)).collect(Collectors.toList());
		return collectionsDto == null || collectionsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(collectionsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/collection".
	 * 
	 * @param collection Nouvel CollectionDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'CollectionDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CollectionDto collectionDto) {
		Collection collection = collectionMapper.mapToEntity(collectionDto);
		collectionService.save(collection);
		return collection == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/collection/{id}".
	 * 
	 * @param collectionDtoSource CollectionDto source pour la mise à jour.
	 * @param id                  Id de l'Collection à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Collection à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody CollectionDto collectionDtoSource, @PathVariable Integer id) {
		Collection collectionSource = collectionMapper.mapToEntity(collectionDtoSource);
		Collection collectionTarget = collectionService.update(collectionSource, id);
		return collectionTarget == null || collectionTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/collection/{id}".
	 * 
	 * @param id Id de l'Collection à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		collectionService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
