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

import com.dsi.bibliosys.biblioback.data.dto.RoleDto;
import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.mapper.RoleMapper;
import com.dsi.bibliosys.biblioback.service.RoleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Role.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

	/**
	 * Service de l'entité business Role.
	 */
	@Autowired
	private RoleService roleService;

	/**
	 * Mapper entre l'entité business Role et l'entité DTO RoleDto.
	 */
	@Autowired
	private RoleMapper roleMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/role".
	 * 
	 * @return Un RoleDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<RoleDto> create() {
		Role role = roleService.create();
		RoleDto roleDto = roleMapper.mapToDto(role);
		return Mono.just(roleDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/role/{id}".
	 * 
	 * @return RoleDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<RoleDto>> readById(@PathVariable Integer id) {
		Role role = roleService.findById(id);
		RoleDto roleDto = roleMapper.mapToDto(role);
		return roleDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(roleDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/role/all".
	 * 
	 * @return Tous les RoleDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<RoleDto>> readAll() {
		List<Role> roles = roleService.findAll();
		List<RoleDto> rolesDto = roles.stream().map(role -> roleMapper.mapToDto(role)).collect(Collectors.toList());
		return rolesDto == null || rolesDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(rolesDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/role".
	 * 
	 * @param role Nouvel RoleDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'RoleDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody RoleDto roleDto) {
		Role role = roleMapper.mapToEntity(roleDto);
		roleService.saveAndFlush(role);
		return role == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/role/{id}".
	 * 
	 * @param roleDtoSource RoleDto source pour la mise à jour.
	 * @param id            Id de l'Role à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Role à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody RoleDto roleDtoSource, @PathVariable Integer id) {
		Role roleSource = roleMapper.mapToEntity(roleDtoSource);
		Role roleTarget = roleService.update(roleSource, id);
		return roleTarget == null || roleTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/role/{id}".
	 * 
	 * @param id Id de l'Role à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
