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

import com.dsi.bibliosys.biblioback.data.dto.RayonDto;
import com.dsi.bibliosys.biblioback.data.entity.Rayon;
import com.dsi.bibliosys.biblioback.mapper.RayonMapper;
import com.dsi.bibliosys.biblioback.service.RayonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Rayon.
 */
@RestController
@RequestMapping("/rayon")
public class RayonController {

	/**
	 * Service de l'entité business Rayon.
	 */
	@Autowired
	private RayonService rayonService;

	/**
	 * Mapper entre l'entité business Rayon et l'entité DTO RayonDto.
	 */
	@Autowired
	private RayonMapper rayonMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/rayon".
	 * 
	 * @return Un RayonDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<RayonDto> create() {
		Rayon rayon = rayonService.create();
		RayonDto rayonDto = rayonMapper.mapToDto(rayon);
		return Mono.just(rayonDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/rayon/{id}".
	 * 
	 * @return RayonDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<RayonDto>> readById(@PathVariable Integer id) {
		Rayon rayon = rayonService.findById(id);
		RayonDto rayonDto = rayonMapper.mapToDto(rayon);
		return rayonDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(rayonDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/rayon/all".
	 * 
	 * @return Tous les RayonDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<RayonDto>> readAll() {
		List<Rayon> rayons = rayonService.findAll();
		List<RayonDto> rayonsDto = rayons.stream().map(rayon -> rayonMapper.mapToDto(rayon))
				.collect(Collectors.toList());
		return rayonsDto == null || rayonsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(rayonsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/rayon".
	 * 
	 * @param rayon Nouvel RayonDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'RayonDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody RayonDto rayonDto) {
		Rayon rayon = rayonMapper.mapToEntity(rayonDto);
		rayonService.saveAndFlush(rayon);
		return rayon == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/rayon/{id}".
	 * 
	 * @param rayonDtoSource RayonDto source pour la mise à jour.
	 * @param id             Id de l'Rayon à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Rayon à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody RayonDto rayonDtoSource, @PathVariable Integer id) {
		Rayon rayonSource = rayonMapper.mapToEntity(rayonDtoSource);
		Rayon rayonTarget = rayonService.update(rayonSource, id);
		return rayonTarget == null || rayonTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/rayon/{id}".
	 * 
	 * @param id Id de l'Rayon à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		rayonService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
