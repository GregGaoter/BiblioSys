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

import com.dsi.bibliosys.biblioback.data.dto.BibliothequeDto;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.mapper.BibliothequeMapper;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Bibliotheque.
 */
@RestController
@RequestMapping("/bibliotheque")
public class BibliothequeController {

	/**
	 * Service de l'entité business Bibliotheque.
	 */
	@Autowired
	private BibliothequeService bibliothequeService;

	/**
	 * Mapper entre l'entité business Bibliotheque et l'entité DTO BibliothequeDto.
	 */
	@Autowired
	private BibliothequeMapper bibliothequeMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/bibliotheque".
	 * 
	 * @return Un BibliothequeDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<BibliothequeDto> create() {
		Bibliotheque bibliotheque = bibliothequeService.create();
		BibliothequeDto bibliothequeDto = bibliothequeMapper.mapToDto(bibliotheque);
		return Mono.just(bibliothequeDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/bibliotheque/{id}".
	 * 
	 * @return BibliothequeDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<BibliothequeDto>> readById(@PathVariable Integer id) {
		Bibliotheque bibliotheque = bibliothequeService.findById(id);
		BibliothequeDto bibliothequeDto = bibliothequeMapper.mapToDto(bibliotheque);
		return bibliothequeDto == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Mono.just(bibliothequeDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/bibliotheque/all".
	 * 
	 * @return Tous les BibliothequeDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<BibliothequeDto>> readAll() {
		List<Bibliotheque> bibliotheques = bibliothequeService.findAll();
		List<BibliothequeDto> bibliothequesDto = bibliotheques.stream()
				.map(bibliotheque -> bibliothequeMapper.mapToDto(bibliotheque)).collect(Collectors.toList());
		return bibliothequesDto == null || bibliothequesDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(bibliothequesDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/bibliotheque".
	 * 
	 * @param bibliotheque Nouvel BibliothequeDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'BibliothequeDto nouvellement créé est null, sinon renvoie
	 *         le code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody BibliothequeDto bibliothequeDto) {
		Bibliotheque bibliotheque = bibliothequeMapper.mapToEntity(bibliothequeDto);
		bibliothequeService.saveAndFlush(bibliotheque);
		return bibliotheque == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/bibliotheque/{id}".
	 * 
	 * @param bibliothequeDtoSource BibliothequeDto source pour la mise à jour.
	 * @param id                    Id de l'Bibliotheque à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Bibliotheque à mettre à jour n'est pas trouvé ou si son id est
	 *         null, sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody BibliothequeDto bibliothequeDtoSource, @PathVariable Integer id) {
		Bibliotheque bibliothequeSource = bibliothequeMapper.mapToEntity(bibliothequeDtoSource);
		Bibliotheque bibliothequeTarget = bibliothequeService.update(bibliothequeSource, id);
		return bibliothequeTarget == null || bibliothequeTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/bibliotheque/{id}".
	 * 
	 * @param id Id de l'Bibliotheque à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		bibliothequeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
