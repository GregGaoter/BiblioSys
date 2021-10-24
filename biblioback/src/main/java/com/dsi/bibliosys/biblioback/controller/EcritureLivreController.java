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

import com.dsi.bibliosys.biblioback.data.dto.EcritureLivreDto;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.mapper.EcritureLivreMapper;
import com.dsi.bibliosys.biblioback.service.EcritureLivreService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business EcritureLivre.
 */
@RestController
@RequestMapping("/ecritureLivre")
public class EcritureLivreController {

	/**
	 * Service de l'entité business EcritureLivre.
	 */
	@Autowired
	private EcritureLivreService ecritureLivreService;

	/**
	 * Mapper entre l'entité business EcritureLivre et l'entité DTO
	 * EcritureLivreDto.
	 */
	@Autowired
	private EcritureLivreMapper ecritureLivreMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/ecritureLivre".
	 * 
	 * @return Un EcritureLivreDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<EcritureLivreDto> create() {
		EcritureLivre ecritureLivre = ecritureLivreService.create();
		EcritureLivreDto ecritureLivreDto = ecritureLivreMapper.mapToDto(ecritureLivre);
		return Mono.just(ecritureLivreDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/ecritureLivre/{id}".
	 * 
	 * @return EcritureLivreDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<EcritureLivreDto>> readById(@PathVariable Integer id) {
		EcritureLivre ecritureLivre = ecritureLivreService.findById(id);
		EcritureLivreDto ecritureLivreDto = ecritureLivreMapper.mapToDto(ecritureLivre);
		return ecritureLivreDto == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Mono.just(ecritureLivreDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/ecritureLivre/all".
	 * 
	 * @return Tous les EcritureLivreDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<EcritureLivreDto>> readAll() {
		List<EcritureLivre> ecritureLivres = ecritureLivreService.findAll();
		List<EcritureLivreDto> ecritureLivresDto = ecritureLivres.stream()
				.map(ecritureLivre -> ecritureLivreMapper.mapToDto(ecritureLivre)).collect(Collectors.toList());
		return ecritureLivresDto == null || ecritureLivresDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(ecritureLivresDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/ecritureLivre".
	 * 
	 * @param ecritureLivre Nouvel EcritureLivreDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'EcritureLivreDto nouvellement créé est null, sinon renvoie
	 *         le code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody EcritureLivreDto ecritureLivreDto) {
		EcritureLivre ecritureLivre = ecritureLivreMapper.mapToEntity(ecritureLivreDto);
		ecritureLivreService.saveAndFlush(ecritureLivre);
		return ecritureLivre == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/ecritureLivre/{id}".
	 * 
	 * @param ecritureLivreDtoSource EcritureLivreDto source pour la mise à jour.
	 * @param id                     Id de l'EcritureLivre à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'EcritureLivre à mettre à jour n'est pas trouvé ou si son id est
	 *         null, sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody EcritureLivreDto ecritureLivreDtoSource, @PathVariable Integer id) {
		EcritureLivre ecritureLivreSource = ecritureLivreMapper.mapToEntity(ecritureLivreDtoSource);
		EcritureLivre ecritureLivreTarget = ecritureLivreService.update(ecritureLivreSource, id);
		return ecritureLivreTarget == null || ecritureLivreTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/ecritureLivre/{id}".
	 * 
	 * @param id Id de l'EcritureLivre à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		ecritureLivreService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
