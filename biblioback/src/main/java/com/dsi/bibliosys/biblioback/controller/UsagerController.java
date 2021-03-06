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

import com.dsi.bibliosys.biblioback.data.dto.UsagerDto;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.mapper.UsagerMapper;
import com.dsi.bibliosys.biblioback.service.UsagerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Usager.
 */
@RestController
@RequestMapping("/usager")
public class UsagerController {

	/**
	 * Service de l'entité business Usager.
	 */
	@Autowired
	private UsagerService usagerService;

	/**
	 * Mapper entre l'entité business Usager et l'entité DTO UsagerDto.
	 */
	@Autowired
	private UsagerMapper usagerMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager".
	 * 
	 * @return Un UsagerDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<UsagerDto> create() {
		Usager usager = usagerService.create();
		UsagerDto usagerDto = usagerMapper.mapToDto(usager);
		return Mono.just(usagerDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/{id}".
	 * 
	 * @return UsagerDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<UsagerDto>> readById(@PathVariable Integer id) {
		Usager usager = usagerService.findById(id);
		UsagerDto usagerDto = usagerMapper.mapToDto(usager);
		return usagerDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(usagerDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/all".
	 * 
	 * @return Tous les UsagerDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<UsagerDto>> readAll() {
		List<Usager> usagers = usagerService.findAll();
		List<UsagerDto> usagersDto = usagers.stream().map(usager -> usagerMapper.mapToDto(usager))
				.collect(Collectors.toList());
		return usagersDto == null || usagersDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(usagersDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/usager".
	 * 
	 * @param usager Nouvel UsagerDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'UsagerDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsagerDto usagerDto) {
		Usager usager = usagerMapper.mapToEntity(usagerDto);
		usagerService.save(usager);
		return usager == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/usager/{id}".
	 * 
	 * @param usagerDtoSource UsagerDto source pour la mise à jour.
	 * @param id              Id de l'Usager à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Usager à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UsagerDto usagerDtoSource, @PathVariable Integer id) {
		Usager usagerSource = usagerMapper.mapToEntity(usagerDtoSource);
		Usager usagerTarget = usagerService.update(usagerSource, id);
		return usagerTarget == null || usagerTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/usager/{id}".
	 * 
	 * @param id Id de l'Usager à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usagerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
