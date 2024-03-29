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

import com.dsi.bibliosys.biblioback.data.dto.LieuDto;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.mapper.LieuMapper;
import com.dsi.bibliosys.biblioback.service.LieuService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Lieu.
 */
@RestController
@RequestMapping("/lieu")
public class LieuController {

	/**
	 * Service de l'entité business Lieu.
	 */
	@Autowired
	private LieuService lieuService;

	/**
	 * Mapper entre l'entité business Lieu et l'entité DTO LieuDto.
	 */
	@Autowired
	private LieuMapper lieuMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/lieu".
	 * 
	 * @return Un LieuDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<LieuDto> create() {
		Lieu lieu = lieuService.create();
		LieuDto lieuDto = lieuMapper.mapToDto(lieu);
		return Mono.just(lieuDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/lieu/{id}".
	 * 
	 * @return LieuDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<LieuDto>> readById(@PathVariable Integer id) {
		Lieu lieu = lieuService.findById(id);
		LieuDto lieuDto = lieuMapper.mapToDto(lieu);
		return lieuDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(lieuDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/lieu/all".
	 * 
	 * @return Tous les LieuDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<LieuDto>> readAll() {
		List<Lieu> lieus = lieuService.findAll();
		List<LieuDto> lieusDto = lieus.stream().map(lieu -> lieuMapper.mapToDto(lieu)).collect(Collectors.toList());
		return lieusDto == null || lieusDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(lieusDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/lieu".
	 * 
	 * @param lieu Nouvel LieuDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'LieuDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody LieuDto lieuDto) {
		Lieu lieu = lieuMapper.mapToEntity(lieuDto);
		lieuService.saveAndFlush(lieu);
		return lieu == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/lieu/{id}".
	 * 
	 * @param lieuDtoSource LieuDto source pour la mise à jour.
	 * @param id            Id de l'Lieu à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Lieu à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody LieuDto lieuDtoSource, @PathVariable Integer id) {
		Lieu lieuSource = lieuMapper.mapToEntity(lieuDtoSource);
		Lieu lieuTarget = lieuService.update(lieuSource, id);
		return lieuTarget == null || lieuTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/lieu/{id}".
	 * 
	 * @param id Id de l'Lieu à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		lieuService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
