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

import com.dsi.bibliosys.biblioback.data.dto.AuteurDto;
import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.mapper.AuteurMapper;
import com.dsi.bibliosys.biblioback.service.AuteurService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Auteur.
 */
@RestController
@RequestMapping("/auteur")
public class AuteurController {

	/**
	 * Service de l'entité business Auteur.
	 */
	@Autowired
	private AuteurService auteurService;

	/**
	 * Mapper entre l'entité business Auteur et l'entité DTO AuteurDto.
	 */
	@Autowired
	private AuteurMapper auteurMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/auteur".
	 * 
	 * @return Un AuteurDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<AuteurDto> create() {
		Auteur auteur = auteurService.create();
		AuteurDto auteurDto = auteurMapper.mapToDto(auteur);
		return Mono.just(auteurDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/auteur/{id}".
	 * 
	 * @return AuteurDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<AuteurDto>> readById(@PathVariable Integer id) {
		Auteur auteur = auteurService.findById(id);
		AuteurDto auteurDto = auteurMapper.mapToDto(auteur);
		return auteurDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(auteurDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/auteur/all".
	 * 
	 * @return Tous les AuteurDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<AuteurDto>> readAll() {
		List<Auteur> auteurs = auteurService.findAll();
		List<AuteurDto> auteursDto = auteurs.stream().map(auteur -> auteurMapper.mapToDto(auteur))
				.collect(Collectors.toList());
		return auteursDto == null || auteursDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(auteursDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/auteur".
	 * 
	 * @param auteur Nouvel AuteurDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'AuteurDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody AuteurDto auteurDto) {
		Auteur auteur = auteurMapper.mapToEntity(auteurDto);
		auteurService.save(auteur);
		return auteur == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/auteur/{id}".
	 * 
	 * @param auteurDtoSource AuteurDto source pour la mise à jour.
	 * @param id              Id de l'Auteur à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Auteur à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody AuteurDto auteurDtoSource, @PathVariable Integer id) {
		Auteur auteurSource = auteurMapper.mapToEntity(auteurDtoSource);
		Auteur auteurTarget = auteurService.update(auteurSource, id);
		return auteurTarget == null || auteurTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/auteur/{id}".
	 * 
	 * @param id Id de l'Auteur à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		auteurService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	// =====================================
	// --- SPECIFICS ENDPOINTS
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/auteur/livre/{id}".
	 * 
	 * @return L'auteur du livre spécifié
	 */
	@GetMapping("/livre/{id}")
	public ResponseEntity<Mono<AuteurDto>> readByLivreId(@PathVariable Integer id) {
		AuteurDto auteurDto = auteurMapper.mapToDto(auteurService.findByLivreId(id));
		return auteurDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(auteurDto));
	}
}
