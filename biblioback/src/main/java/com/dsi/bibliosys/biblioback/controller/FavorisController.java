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

import com.dsi.bibliosys.biblioback.data.dto.FavorisDto;
import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.mapper.FavorisMapper;
import com.dsi.bibliosys.biblioback.service.FavorisService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Favoris.
 */
@RestController
@RequestMapping("/favoris")
public class FavorisController {

	/**
	 * Service de l'entité business Favoris.
	 */
	@Autowired
	private FavorisService favorisService;

	/**
	 * Mapper entre l'entité business Favoris et l'entité DTO FavorisDto.
	 */
	@Autowired
	private FavorisMapper favorisMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/favoris".
	 * 
	 * @return Un FavorisDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<FavorisDto> create() {
		Favoris favoris = favorisService.create();
		FavorisDto favorisDto = favorisMapper.mapToDto(favoris);
		return Mono.just(favorisDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/favoris/{id}".
	 * 
	 * @return FavorisDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<FavorisDto>> readById(@PathVariable Integer id) {
		Favoris favoris = favorisService.findById(id);
		FavorisDto favorisDto = favorisMapper.mapToDto(favoris);
		return favorisDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(favorisDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/favoris/all".
	 * 
	 * @return Tous les FavorisDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<FavorisDto>> readAll() {
		List<Favoris> favoriss = favorisService.findAll();
		List<FavorisDto> favorissDto = favoriss.stream().map(favoris -> favorisMapper.mapToDto(favoris))
				.collect(Collectors.toList());
		return favorissDto == null || favorissDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(favorissDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/favoris".
	 * 
	 * @param favoris Nouvel FavorisDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'FavorisDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody FavorisDto favorisDto) {
		Favoris favoris = favorisMapper.mapToEntity(favorisDto);
		favorisService.saveAndFlush(favoris);
		return favoris == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/favoris/{id}".
	 * 
	 * @param favorisDtoSource FavorisDto source pour la mise à jour.
	 * @param id               Id de l'Favoris à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Favoris à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody FavorisDto favorisDtoSource, @PathVariable Integer id) {
		Favoris favorisSource = favorisMapper.mapToEntity(favorisDtoSource);
		Favoris favorisTarget = favorisService.update(favorisSource, id);
		return favorisTarget == null || favorisTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/favoris/{id}".
	 * 
	 * @param id Id de l'Favoris à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		favorisService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
