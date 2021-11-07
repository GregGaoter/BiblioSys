package com.dsi.bibliosys.biblioback.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.biblioback.data.dto.IdentifiantDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.mapper.IdentifiantMapper;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.PretService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrôleur REST de l'entité business Identifiant.
 */
@RestController
@CrossOrigin
@RequestMapping("/identifiant")
public class IdentifiantController {

	/**
	 * Service de l'entité business Identifiant.
	 */
	@Autowired
	private IdentifiantService identifiantService;

	/**
	 * Mapper entre l'entité business Identifiant et l'entité DTO IdentifiantDto.
	 */
	@Autowired
	private IdentifiantMapper identifiantMapper;

	/**
	 * Service de l'entité business Pret.
	 */
	@Autowired
	private PretService pretService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant".
	 * 
	 * @return Un IdentifiantDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<IdentifiantDto> create() {
		Identifiant identifiant = identifiantService.create();
		IdentifiantDto identifiantDto = identifiantMapper.mapToDto(identifiant);
		return Mono.just(identifiantDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant/{id}".
	 * 
	 * @return IdentifiantDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<IdentifiantDto>> readById(@PathVariable Integer id) {
		Identifiant identifiant = identifiantService.findById(id);
		IdentifiantDto identifiantDto = identifiantMapper.mapToDto(identifiant);
		return identifiantDto == null ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Mono.just(identifiantDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant/all".
	 * 
	 * @return Tous les IdentifiantDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<IdentifiantDto>> readAll() {
		List<Identifiant> identifiants = identifiantService.findAll();
		List<IdentifiantDto> identifiantsDto = identifiants.stream()
				.map(identifiant -> identifiantMapper.mapToDto(identifiant)).collect(Collectors.toList());
		return identifiantsDto == null || identifiantsDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(identifiantsDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/identifiant".
	 * 
	 * @param identifiant Nouvel IdentifiantDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'IdentifiantDto nouvellement créé est null, sinon renvoie le
	 *         code "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody IdentifiantDto identifiantDto) {
		Identifiant identifiant = identifiantMapper.mapToEntity(identifiantDto);
		identifiantService.saveAndFlush(identifiant);
		return identifiant == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/identifiant/{id}".
	 * 
	 * @param identifiantDtoSource IdentifiantDto source pour la mise à jour.
	 * @param id                   Id de l'Identifiant à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Identifiant à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody IdentifiantDto identifiantDtoSource, @PathVariable Integer id) {
		Identifiant identifiantSource = identifiantMapper.mapToEntity(identifiantDtoSource);
		Identifiant identifiantTarget = identifiantService.update(identifiantSource, id);
		return identifiantTarget == null || identifiantTarget.getId() == null ? ResponseEntity.noContent().build()
				: ResponseEntity.ok().build();
	}

	// =====================================
	// --- DELETE
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI DELETE "/identifiant/{id}".
	 * 
	 * @param id Id de l'Identifiant à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		identifiantService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	// =====================================
	// --- Endpoints spécifiques
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/identifiant/pretExpired".
	 * 
	 * @return La liste des emails qui ont des prêts expirés
	 */
	@GetMapping("/pretExpired")
	public List<String> getEmailPretExpired() {
		List<Pret> pretsExpired = pretService.findAllExpired();
		List<String> emails = pretsExpired.stream().map(pret -> pret.getUsager().getIdentifiant().getEmail())
				.collect(Collectors.toList());
		return emails;
	}

	@GetMapping("/password")
	public void generatePassword() {
		char[] listeCaracteresMotDePasse = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
				'5', '6', '7', '8', '9' };
		List<String> listeMotsDePasse = new ArrayList<>(1889);
		for (int i = 0; i < 1889; i++) {
			String[] motDePasse = new String[2];
			String motDePasseClair = "", motDePasseCrypte;
			Random randomLongueurMotDePasse = new Random();
			int longueurMotDePasse = randomLongueurMotDePasse.ints(1, 8, 17).sum();
			for (int j = 0; j < longueurMotDePasse; j++) {
				Random randomCaractere = new Random();
				motDePasseClair += listeCaracteresMotDePasse[randomCaractere.nextInt(listeCaracteresMotDePasse.length)];
			}
			motDePasseCrypte = passwordEncoder.encode(motDePasseClair);
			motDePasse[0] = motDePasseClair;
			motDePasse[1] = motDePasseCrypte;
			listeMotsDePasse.add(convertToCsv(motDePasse));
		}
		try {
			FileWriter fichierMotDePasse = new FileWriter(
					"C:\\Users\\gregg\\Documents\\openclassrooms\\developpeur_applications_java\\07_developper_systeme_bibliotheque\\doc\\passwords.csv");
			PrintWriter printWriter = new PrintWriter(fichierMotDePasse);
			listeMotsDePasse.stream().forEach(printWriter::println);
			printWriter.close();
		} catch (IOException e) {
		}
	}

	private String convertToCsv(String[] data) {
		return Stream.of(data).collect(Collectors.joining(","));
	}

}
