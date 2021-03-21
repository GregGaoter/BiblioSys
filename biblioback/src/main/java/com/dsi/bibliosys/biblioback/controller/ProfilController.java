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

import com.dsi.bibliosys.biblioback.data.dto.ProfilDto;
import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.mapper.ProfilMapper;
import com.dsi.bibliosys.biblioback.service.ProfilService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Profil.
 */
@RestController
@RequestMapping("/profil")
public class ProfilController {

	/**
	 * Service de l'entité business Profil.
	 */
	@Autowired
	private ProfilService profilService;

	/**
	 * Mapper entre l'entité business Profil et l'entité DTO ProfilDto.
	 */
	@Autowired
	private ProfilMapper profilMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/profil".
	 * 
	 * @return Un ProfilDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<ProfilDto> create() {
		Profil profil = profilService.create();
		ProfilDto profilDto = profilMapper.mapToDto(profil);
		return Mono.just(profilDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/profil/{id}".
	 * 
	 * @return ProfilDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<ProfilDto>> readById(@PathVariable Integer id) {
		Profil profil = profilService.findById(id);
		ProfilDto profilDto = profilMapper.mapToDto(profil);
		return profilDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(profilDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/profil/all".
	 * 
	 * @return Tous les ProfilDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<ProfilDto>> readAll() {
		List<Profil> profils = profilService.findAll();
		List<ProfilDto> profilsDto = profils.stream().map(profil -> profilMapper.mapToDto(profil))
				.collect(Collectors.toList());
		return profilsDto == null || profilsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(profilsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/profil".
	 * 
	 * @param profil Nouvel ProfilDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'ProfilDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody ProfilDto profilDto) {
		Profil profil = profilMapper.mapToEntity(profilDto);
		profilService.save(profil);
		return profil == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/profil/{id}".
	 * 
	 * @param profilDtoSource ProfilDto source pour la mise à jour.
	 * @param id              Id de l'Profil à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Profil à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody ProfilDto profilDtoSource, @PathVariable Integer id) {
		Profil profilSource = profilMapper.mapToEntity(profilDtoSource);
		Profil profilTarget = profilService.update(profilSource, id);
		return profilTarget == null || profilTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/profil/{id}".
	 * 
	 * @param id Id de l'Profil à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		profilService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
