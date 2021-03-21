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

import com.dsi.bibliosys.biblioback.data.dto.IdentifiantDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.mapper.IdentifiantMapper;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Identifiant.
 */
@RestController
@RequestMapping("/identifiant")
public class IdentifiantController {

	/**
	 * Service de l'entité business Identifiant.
	 */
	@Autowired
	private IdentifiantService identifiantService;

	/**
	 * Mapper entre l'entité business Identifiant et l'entité DTO IdentifiantDto.
	 */
	@Autowired
	private IdentifiantMapper identifiantMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant".
	 * 
	 * @return Un IdentifiantDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<IdentifiantDto> create() {
		Identifiant identifiant = identifiantService.create();
		IdentifiantDto identifiantDto = identifiantMapper.mapToDto(identifiant);
		return Mono.just(identifiantDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant/{id}".
	 * 
	 * @return IdentifiantDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<IdentifiantDto>> readById(@PathVariable Integer id) {
		Identifiant identifiant = identifiantService.findById(id);
		IdentifiantDto identifiantDto = identifiantMapper.mapToDto(identifiant);
		return identifiantDto == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Mono.just(identifiantDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant/all".
	 * 
	 * @return Tous les IdentifiantDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<IdentifiantDto>> readAll() {
		List<Identifiant> identifiants = identifiantService.findAll();
		List<IdentifiantDto> identifiantsDto = identifiants.stream()
				.map(identifiant -> identifiantMapper.mapToDto(identifiant)).collect(Collectors.toList());
		return identifiantsDto == null || identifiantsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(identifiantsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/identifiant".
	 * 
	 * @param identifiant Nouvel IdentifiantDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'IdentifiantDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody IdentifiantDto identifiantDto) {
		Identifiant identifiant = identifiantMapper.mapToEntity(identifiantDto);
		identifiantService.save(identifiant);
		return identifiant == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/identifiant/{id}".
	 * 
	 * @param identifiantDtoSource IdentifiantDto source pour la mise à jour.
	 * @param id                   Id de l'Identifiant à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Identifiant à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody IdentifiantDto identifiantDtoSource, @PathVariable Integer id) {
		Identifiant identifiantSource = identifiantMapper.mapToEntity(identifiantDtoSource);
		Identifiant identifiantTarget = identifiantService.update(identifiantSource, id);
		return identifiantTarget == null || identifiantTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/identifiant/{id}".
	 * 
	 * @param id Id de l'Identifiant à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		identifiantService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
