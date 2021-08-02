package com.dsi.bibliosys.biblioback.controller;

import static com.dsi.bibliosys.biblioback.repository.specification.LivreSpecification.genreIdEqual;
import static com.dsi.bibliosys.biblioback.repository.specification.LivreSpecification.rayonIdEqual;
import static com.dsi.bibliosys.biblioback.repository.specification.LivreSpecification.searchCriteriasEqual;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.biblioback.app.Utils;
import com.dsi.bibliosys.biblioback.data.dto.LivreDto;
import com.dsi.bibliosys.biblioback.data.dto.LivreResultatDto;
import com.dsi.bibliosys.biblioback.data.dto.LivreSearchCriteriasDto;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.mapper.LivreMapper;
import com.dsi.bibliosys.biblioback.service.LivreService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Livre.
 */
@RestController
@CrossOrigin
@RequestMapping("/livre")
public class LivreController {

	/**
	 * Service de l'entité business Livre.
	 */
	@Autowired
	private LivreService livreService;

	/**
	 * Mapper entre l'entité business Livre et l'entité DTO LivreDto.
	 */
	@Autowired
	private LivreMapper livreMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre".
	 * 
	 * @return Un LivreDto vide
	 */
	@GetMapping("/nouveau")
//	@PreAuthorize("hasRole('BIBLIOTHECAIRE')")
	// @PreAuthorize("#{}")
	public Mono<LivreDto> create() {
		Livre livre = livreService.create();
		LivreDto livreDto = livreMapper.mapToDto(livre);
		return Mono.just(livreDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/{id}".
	 * 
	 * @return LivreDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	// @PreAuthorize("hasRole('USAGER')")
	public ResponseEntity<Mono<LivreDto>> readById(@PathVariable Integer id) {
		Livre livre = livreService.findById(id);
		LivreDto livreDto = livreMapper.mapToDto(livre);
		return livreDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(livreDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/resultat/{id}".
	 * 
	 * @return LivreResultatDto avec l'id fournit.
	 */
	@GetMapping("/resultat/{id}")
	public ResponseEntity<Mono<LivreResultatDto>> readResultById(@PathVariable Integer id) {
		LivreDto livreDto = livreMapper.mapToDto(livreService.findById(id));
		LivreResultatDto livreResultatDto = livreService.getLivreResultatDto(livreDto);
		return livreResultatDto == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Mono.just(livreResultatDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/all".
	 * 
	 * @return Tous les LivreDto.
	 */
	@GetMapping("/all")
	// @PreAuthorize("hasRole('USAGER')")
	public ResponseEntity<Flux<LivreDto>> readAll() {
		List<Livre> livres = livreService.findAll();
		List<LivreDto> livresDto = livres.stream().map(livre -> livreMapper.mapToDto(livre))
				.collect(Collectors.toList());
		return livresDto == null || livresDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(livresDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/resultat/all".
	 * 
	 * @return Tous les LivreResultatDto.
	 */
	@GetMapping("/resultat/all")
	public ResponseEntity<Flux<LivreResultatDto>> readResultAll() {
		List<LivreDto> livresDto = livreService.findAll().stream().map(livre -> livreMapper.mapToDto(livre))
				.collect(Collectors.toList());
		List<LivreResultatDto> livreResultatsDto = livreService.getLivreResultatsDto(livresDto);
		return livreResultatsDto == null || livreResultatsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(livreResultatsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/livre".
	 * 
	 * @param livre Nouvel LivreDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'LivreDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody LivreDto livreDto) {
		Livre livre = livreMapper.mapToEntity(livreDto);
		livreService.save(livre);
		return livre == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/livre/{id}".
	 * 
	 * @param livreDtoSource LivreDto source pour la mise à jour.
	 * @param id             Id de l'Livre à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Livre à mettre à jour n'est pas trouvé ou si son id est null, sinon
	 *         renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody LivreDto livreDtoSource, @PathVariable Integer id) {
		Livre livreSource = livreMapper.mapToEntity(livreDtoSource);
		Livre livreTarget = livreService.update(livreSource, id);
		return livreTarget == null || livreTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/livre/{id}".
	 * 
	 * @param id Id de l'Livre à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	// @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		livreService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	// =====================================
	// --- SPECIFICS ENDPOINTS
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/rayon/{id}".
	 * 
	 * @return Tous les LivreDto du rayon spécifié.
	 */
	@GetMapping("/rayon/{id}")
	public ResponseEntity<Flux<LivreDto>> readByRayonId(@PathVariable Integer id, @RequestParam Integer page,
			@RequestParam Integer size) {
		Page<Livre> pageLivre = livreService.findAll(rayonIdEqual(id), PageRequest.of(page, size));
		List<LivreDto> livresDto = pageLivre.get().map(livreMapper::mapToDto).collect(Collectors.toList());
		return livresDto == null || livresDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(livresDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/resultat/rayon/{id}".
	 * 
	 * @return Tous les LivreResultatDto du rayon spécifié.
	 */
	@GetMapping("/resultat/rayon/{id}")
	public ResponseEntity<Flux<LivreResultatDto>> readResultByRayonId(@PathVariable Integer id,
			@RequestParam Integer page, @RequestParam Integer size) {
		Page<Livre> pageLivre = livreService.findAll(rayonIdEqual(id), PageRequest.of(page, size));
		List<LivreDto> livresDto = pageLivre.get().map(livreMapper::mapToDto).collect(Collectors.toList());
		List<LivreResultatDto> livreResultatsDto = livreService.getLivreResultatsDto(livresDto);
		return livreResultatsDto == null || livreResultatsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok().headers(Utils.getTotalCountFilterIdHeaders(pageLivre.getTotalElements(), id))
						.body(Flux.fromIterable(livreResultatsDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/genre/{id}".
	 * 
	 * @return Tous les LivreDto du genre spécifié.
	 */
	@GetMapping("/genre/{id}")
	public ResponseEntity<Flux<LivreDto>> readByGenreId(@PathVariable Integer id, @RequestParam Integer page,
			@RequestParam Integer size) {
		Page<Livre> pageLivre = livreService.findAll(genreIdEqual(id), PageRequest.of(page, size));
		List<LivreDto> livresDto = pageLivre.get().map(livreMapper::mapToDto).collect(Collectors.toList());
		return livresDto == null || livresDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(livresDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/livre/resultat/genre/{id}".
	 * 
	 * @return Tous les LivreResultatDto du genre spécifié.
	 */
	@GetMapping("/resultat/genre/{id}")
	public ResponseEntity<Flux<LivreResultatDto>> readResultByGenreId(@PathVariable Integer id,
			@RequestParam Integer page, @RequestParam Integer size) {
		Page<Livre> pageLivre = livreService.findAll(genreIdEqual(id), PageRequest.of(page, size));
		List<LivreDto> livresDto = pageLivre.get().map(livreMapper::mapToDto).collect(Collectors.toList());
		List<LivreResultatDto> livreResultatsDto = livreService.getLivreResultatsDto(livresDto);
		return livreResultatsDto == null || livreResultatsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok().headers(Utils.getTotalCountFilterIdHeaders(pageLivre.getTotalElements(), id))
						.body(Flux.fromIterable(livreResultatsDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI POST "/livre/resultat/search-criterias".
	 * 
	 * @return Tous les LivreResultatDto selon les critères de recherche.
	 */
	@PostMapping("/resultat/search-criterias")
	public ResponseEntity<Flux<LivreResultatDto>> readResultBySearchCriterias(
			@RequestBody LivreSearchCriteriasDto livreSearchCriteriasDto, @RequestParam Integer page,
			@RequestParam Integer size) {
		Page<Livre> pageLivre = livreService.findAll(searchCriteriasEqual(livreSearchCriteriasDto),
				PageRequest.of(page, size));
		List<LivreDto> livresDto = pageLivre.get().map(livreMapper::mapToDto).collect(Collectors.toList());
		List<LivreResultatDto> livreResultatsDto = livreService.getLivreResultatsDto(livresDto);
		return livreResultatsDto == null || livreResultatsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok()
						.headers(Utils.getSearchCriteriasHeaders(pageLivre.getTotalElements(), livreSearchCriteriasDto))
						.body(Flux.fromIterable(livreResultatsDto));
	}
}
