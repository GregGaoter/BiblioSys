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

import com.dsi.bibliosys.biblioback.data.dto.GenreDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.mapper.GenreMapper;
import com.dsi.bibliosys.biblioback.service.GenreService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Genre.
 */
@RestController
@RequestMapping("/genre")
public class GenreController {

	/**
	 * Service de l'entité business Genre.
	 */
	@Autowired
	private GenreService genreService;

	/**
	 * Mapper entre l'entité business Genre et l'entité DTO GenreDto.
	 */
	@Autowired
	private GenreMapper genreMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/genre".
	 * 
	 * @return Un GenreDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<GenreDto> create() {
		Genre genre = genreService.create();
		GenreDto genreDto = genreMapper.mapToDto(genre);
		return Mono.just(genreDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/genre/{id}".
	 * 
	 * @return GenreDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<GenreDto>> readById(@PathVariable Integer id) {
		Genre genre = genreService.findById(id);
		GenreDto genreDto = genreMapper.mapToDto(genre);
		return genreDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(genreDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/genre/all".
	 * 
	 * @return Tous les GenreDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<GenreDto>> readAll() {
		List<Genre> genres = genreService.findAll();
		List<GenreDto> genresDto = genres.stream().map(genre -> genreMapper.mapToDto(genre))
				.collect(Collectors.toList());
		return genresDto == null || genresDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(genresDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/genre".
	 * 
	 * @param genre Nouvel GenreDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'GenreDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody GenreDto genreDto) {
		Genre genre = genreMapper.mapToEntity(genreDto);
		genreService.saveAndFlush(genre);
		return genre == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/genre/{id}".
	 * 
	 * @param genreDtoSource GenreDto source pour la mise à jour.
	 * @param id             Id de l'Genre à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Genre à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody GenreDto genreDtoSource, @PathVariable Integer id) {
		Genre genreSource = genreMapper.mapToEntity(genreDtoSource);
		Genre genreTarget = genreService.update(genreSource, id);
		return genreTarget == null || genreTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/genre/{id}".
	 * 
	 * @param id Id de l'Genre à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		genreService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
