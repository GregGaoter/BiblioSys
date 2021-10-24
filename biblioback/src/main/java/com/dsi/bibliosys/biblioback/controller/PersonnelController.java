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

import com.dsi.bibliosys.biblioback.data.dto.PersonnelDto;
import com.dsi.bibliosys.biblioback.data.entity.Personnel;
import com.dsi.bibliosys.biblioback.mapper.PersonnelMapper;
import com.dsi.bibliosys.biblioback.service.PersonnelService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Personnel.
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelController {

	/**
	 * Service de l'entité business Personnel.
	 */
	@Autowired
	private PersonnelService personnelService;

	/**
	 * Mapper entre l'entité business Personnel et l'entité DTO PersonnelDto.
	 */
	@Autowired
	private PersonnelMapper personnelMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/personnel".
	 * 
	 * @return Un PersonnelDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<PersonnelDto> create() {
		Personnel personnel = personnelService.create();
		PersonnelDto personnelDto = personnelMapper.mapToDto(personnel);
		return Mono.just(personnelDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/personnel/{id}".
	 * 
	 * @return PersonnelDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<PersonnelDto>> readById(@PathVariable Integer id) {
		Personnel personnel = personnelService.findById(id);
		PersonnelDto personnelDto = personnelMapper.mapToDto(personnel);
		return personnelDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(personnelDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/personnel/all".
	 * 
	 * @return Tous les PersonnelDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<PersonnelDto>> readAll() {
		List<Personnel> personnels = personnelService.findAll();
		List<PersonnelDto> personnelsDto = personnels.stream().map(personnel -> personnelMapper.mapToDto(personnel))
				.collect(Collectors.toList());
		return personnelsDto == null || personnelsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(personnelsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/personnel".
	 * 
	 * @param personnel Nouvel PersonnelDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'PersonnelDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody PersonnelDto personnelDto) {
		Personnel personnel = personnelMapper.mapToEntity(personnelDto);
		personnelService.saveAndFlush(personnel);
		return personnel == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/personnel/{id}".
	 * 
	 * @param personnelDtoSource PersonnelDto source pour la mise à jour.
	 * @param id                 Id de l'Personnel à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Personnel à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody PersonnelDto personnelDtoSource, @PathVariable Integer id) {
		Personnel personnelSource = personnelMapper.mapToEntity(personnelDtoSource);
		Personnel personnelTarget = personnelService.update(personnelSource, id);
		return personnelTarget == null || personnelTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/personnel/{id}".
	 * 
	 * @param id Id de l'Personnel à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		personnelService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
