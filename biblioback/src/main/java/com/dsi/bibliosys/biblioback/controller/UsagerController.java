package com.dsi.bibliosys.biblioback.controller;

import java.util.List;

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
	 * @return Un Usager vide
	 */
	@GetMapping
	public Mono<Usager> read() {
		return Mono.just(usagerService.create());
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/{id}".
	 * 
	 * @return L'usager avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<Usager>> readId(@PathVariable Integer id) {
		Usager usager = usagerService.findById(id);
		return usager == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(usager));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/all".
	 * 
	 * @return Tous les usagers.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<Usager>> readAll() {
		List<Usager> usagers = usagerService.findAll();
		return usagers == null || usagers.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(usagers));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/usager".
	 * 
	 * @param usager Nouvel usager à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'usager nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsagerDto usagerDto) {
		//Usager usager = usagerMapper.mapToEntity(usagerDto);
		//Usager usagerSaved = usagerService.save(usager);
		//return usagerSaved.getId() == null ? ResponseEntity.noContent().build() : ResponseEntity.created(null).build();
		return ResponseEntity.noContent().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/usager/{id}".
	 * 
	 * @param usagerSource Usager source pour la mise à jour.
	 * @param id           Id de l'usager à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'usager à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Usager usagerSource, @PathVariable Integer id) {
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
	 * @param id Id de l'usager à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usagerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
