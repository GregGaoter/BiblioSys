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

import com.dsi.bibliosys.biblioback.data.dto.AdresseDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.mapper.AdresseMapper;
import com.dsi.bibliosys.biblioback.service.AdresseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Adresse.
 */
@RestController
@RequestMapping("/adresse")
public class AdresseController {

	/**
	 * Service de l'entité business Adresse.
	 */
	@Autowired
	private AdresseService adresseService;

	/**
	 * Mapper entre l'entité business Adresse et l'entité DTO AdresseDto.
	 */
	@Autowired
	private AdresseMapper adresseMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/adresse".
	 * 
	 * @return Un AdresseDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<AdresseDto> create() {
		Adresse adresse = adresseService.create();
		AdresseDto adresseDto = adresseMapper.mapToDto(adresse);
		return Mono.just(adresseDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/adresse/{id}".
	 * 
	 * @return AdresseDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<AdresseDto>> readById(@PathVariable Integer id) {
		Adresse adresse = adresseService.findById(id);
		AdresseDto adresseDto = adresseMapper.mapToDto(adresse);
		return adresseDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(adresseDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/adresse/all".
	 * 
	 * @return Tous les AdresseDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<AdresseDto>> readAll() {
		List<Adresse> adresses = adresseService.findAll();
		List<AdresseDto> adressesDto = adresses.stream().map(adresse -> adresseMapper.mapToDto(adresse))
				.collect(Collectors.toList());
		return adressesDto == null || adressesDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(adressesDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/adresse".
	 * 
	 * @param adresse Nouvel AdresseDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'AdresseDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody AdresseDto adresseDto) {
		Adresse adresse = adresseMapper.mapToEntity(adresseDto);
		adresseService.saveAndFlush(adresse);
		return adresse == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/adresse/{id}".
	 * 
	 * @param adresseDtoSource AdresseDto source pour la mise à jour.
	 * @param id               Id de l'Adresse à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Adresse à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody AdresseDto adresseDtoSource, @PathVariable Integer id) {
		Adresse adresseSource = adresseMapper.mapToEntity(adresseDtoSource);
		Adresse adresseTarget = adresseService.update(adresseSource, id);
		return adresseTarget == null || adresseTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/adresse/{id}".
	 * 
	 * @param id Id de l'Adresse à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		adresseService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
