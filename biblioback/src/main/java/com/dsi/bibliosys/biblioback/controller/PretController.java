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

import com.dsi.bibliosys.biblioback.data.dto.PretDto;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.mapper.PretMapper;
import com.dsi.bibliosys.biblioback.service.PretService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Pret.
 */
@RestController
@RequestMapping("/pret")
public class PretController {

	/**
	 * Service de l'entité business Pret.
	 */
	@Autowired
	private PretService pretService;

	/**
	 * Mapper entre l'entité business Pret et l'entité DTO PretDto.
	 */
	@Autowired
	private PretMapper pretMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/pret".
	 * 
	 * @return Un PretDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<PretDto> create() {
		Pret pret = pretService.create();
		PretDto pretDto = pretMapper.mapToDto(pret);
		return Mono.just(pretDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/pret/{id}".
	 * 
	 * @return PretDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<PretDto>> readById(@PathVariable Integer id) {
		Pret pret = pretService.findById(id);
		PretDto pretDto = pretMapper.mapToDto(pret);
		return pretDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(pretDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/pret/all".
	 * 
	 * @return Tous les PretDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<PretDto>> readAll() {
		List<Pret> prets = pretService.findAll();
		List<PretDto> pretsDto = prets.stream().map(pret -> pretMapper.mapToDto(pret)).collect(Collectors.toList());
		return pretsDto == null || pretsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(pretsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/pret".
	 * 
	 * @param pret Nouvel PretDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'PretDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody PretDto pretDto) {
		Pret pret = pretMapper.mapToEntity(pretDto);
		pretService.save(pret);
		return pret == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/pret/{id}".
	 * 
	 * @param pretDtoSource PretDto source pour la mise à jour.
	 * @param id            Id de l'Pret à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Pret à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody PretDto pretDtoSource, @PathVariable Integer id) {
		Pret pretSource = pretMapper.mapToEntity(pretDtoSource);
		Pret pretTarget = pretService.update(pretSource, id);
		return pretTarget == null || pretTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/pret/{id}".
	 * 
	 * @param id Id de l'Pret à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		pretService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
