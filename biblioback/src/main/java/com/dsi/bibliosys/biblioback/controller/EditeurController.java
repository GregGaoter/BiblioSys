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

import com.dsi.bibliosys.biblioback.data.dto.EditeurDto;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.mapper.EditeurMapper;
import com.dsi.bibliosys.biblioback.service.EditeurService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Editeur.
 */
@RestController
@RequestMapping("/editeur")
public class EditeurController {

	/**
	 * Service de l'entité business Editeur.
	 */
	@Autowired
	private EditeurService editeurService;

	/**
	 * Mapper entre l'entité business Editeur et l'entité DTO EditeurDto.
	 */
	@Autowired
	private EditeurMapper editeurMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/editeur".
	 * 
	 * @return Un EditeurDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<EditeurDto> create() {
		Editeur editeur = editeurService.create();
		EditeurDto editeurDto = editeurMapper.mapToDto(editeur);
		return Mono.just(editeurDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/editeur/{id}".
	 * 
	 * @return EditeurDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<EditeurDto>> readById(@PathVariable Integer id) {
		Editeur editeur = editeurService.findById(id);
		EditeurDto editeurDto = editeurMapper.mapToDto(editeur);
		return editeurDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(editeurDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/editeur/all".
	 * 
	 * @return Tous les EditeurDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<EditeurDto>> readAll() {
		List<Editeur> editeurs = editeurService.findAll();
		List<EditeurDto> editeursDto = editeurs.stream().map(editeur -> editeurMapper.mapToDto(editeur))
				.collect(Collectors.toList());
		return editeursDto == null || editeursDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(editeursDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/editeur".
	 * 
	 * @param editeur Nouvel EditeurDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'EditeurDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody EditeurDto editeurDto) {
		Editeur editeur = editeurMapper.mapToEntity(editeurDto);
		editeurService.save(editeur);
		return editeur == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/editeur/{id}".
	 * 
	 * @param editeurDtoSource EditeurDto source pour la mise à jour.
	 * @param id               Id de l'Editeur à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Editeur à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody EditeurDto editeurDtoSource, @PathVariable Integer id) {
		Editeur editeurSource = editeurMapper.mapToEntity(editeurDtoSource);
		Editeur editeurTarget = editeurService.update(editeurSource, id);
		return editeurTarget == null || editeurTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/editeur/{id}".
	 * 
	 * @param id Id de l'Editeur à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		editeurService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
