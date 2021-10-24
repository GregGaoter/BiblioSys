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

import com.dsi.bibliosys.biblioback.app.Utils;
import com.dsi.bibliosys.biblioback.data.dto.AdresseDto;
import com.dsi.bibliosys.biblioback.data.dto.CurrentUsagerDto;
import com.dsi.bibliosys.biblioback.data.dto.EmpruntsResultatDto;
import com.dsi.bibliosys.biblioback.data.dto.IdentifiantDto;
import com.dsi.bibliosys.biblioback.data.dto.LieuDto;
import com.dsi.bibliosys.biblioback.data.dto.UsagerDto;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.mapper.AdresseMapper;
import com.dsi.bibliosys.biblioback.mapper.IdentifiantMapper;
import com.dsi.bibliosys.biblioback.mapper.LieuMapper;
import com.dsi.bibliosys.biblioback.mapper.UsagerMapper;
import com.dsi.bibliosys.biblioback.repository.specification.EcritureLivreSpecification;
import com.dsi.bibliosys.biblioback.repository.specification.PretSpecification;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.EcritureLivreService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.LieuService;
import com.dsi.bibliosys.biblioback.service.PretService;
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
	 * Service de l'entité business Usager.
	 */
	@Autowired
	private PretService pretService;

	/**
	 * Service de l'entité business Identifiant.
	 */
	@Autowired
	private IdentifiantService identifiantService;

	/**
	 * Service de l'entité business Adresse.
	 */
	@Autowired
	private AdresseService adresseService;

	/**
	 * Service de l'entité business Lieu.
	 */
	@Autowired
	private LieuService lieuService;

	/**
	 * Service de l'entité business EcritureLivre.
	 */
	@Autowired
	private EcritureLivreService ecritureLivreService;

	/**
	 * Mapper entre l'entité business Usager et l'entité DTO UsagerDto.
	 */
	@Autowired
	private UsagerMapper usagerMapper;

	/**
	 * Mapper entre l'entité business Identifiant et l'entité DTO IdentifiantDto.
	 */
	@Autowired
	private IdentifiantMapper identifiantMapper;

	/**
	 * Mapper entre l'entité business Adresse et l'entité DTO AdresseDto.
	 */
	@Autowired
	private AdresseMapper adresseMapper;

	/**
	 * Mapper entre l'entité business Lieu et l'entité DTO LieuDto.
	 */
	@Autowired
	private LieuMapper lieuMapper;

	// =====================================
	// --- GET
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager".
	 * 
	 * @return Un UsagerDto vide
	 */
	@GetMapping("/nouveau")
	public Mono<UsagerDto> create() {
		Usager usager = usagerService.create();
		UsagerDto usagerDto = usagerMapper.mapToDto(usager);
		return Mono.just(usagerDto);
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/{id}".
	 * 
	 * @return UsagerDto avec l'id fournit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Mono<UsagerDto>> readById(@PathVariable Integer id) {
		Usager usager = usagerService.findById(id);
		UsagerDto usagerDto = usagerMapper.mapToDto(usager);
		return usagerDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(usagerDto));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/all".
	 * 
	 * @return Tous les UsagerDto.
	 */
	@GetMapping("/all")
	public ResponseEntity<Flux<UsagerDto>> readAll() {
		List<Usager> usagers = usagerService.findAll();
		List<UsagerDto> usagersDto = usagers.stream().map(usager -> usagerMapper.mapToDto(usager))
				.collect(Collectors.toList());
		return usagersDto == null || usagersDto.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(usagersDto));
	}

	// =====================================
	// --- POST
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI POST "/usager".
	 * 
	 * @param usager Nouvel UsagerDto à créer.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'id de l'UsagerDto nouvellement créé est null, sinon renvoie le code
	 *         "201 Created" avec un corps de réponse null.
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsagerDto usagerDto) {
		Usager usager = usagerMapper.mapToEntity(usagerDto);
		usagerService.saveAndFlush(usager);
		return usager == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
	}

	// =====================================
	// --- PUT
	// =====================================
	/**
	 * Méthode exécutée à l'appel de l'URI PUT "/usager/{id}".
	 * 
	 * @param usagerDtoSource UsagerDto source pour la mise à jour.
	 * @param id              Id de l'Usager à mettre à jour.
	 * @return Renvoie le code "204 No Content" avec un corps de réponse null si
	 *         l'Usager à mettre à jour n'est pas trouvé ou si son id est null,
	 *         sinon renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UsagerDto usagerDtoSource, @PathVariable Integer id) {
		Usager usagerSource = usagerMapper.mapToEntity(usagerDtoSource);
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
	 * @param id Id de l'Usager à supprimer.
	 * @return Renvoie le code "200 OK" avec un corps de réponse null.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usagerService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/emprunts".
	 * 
	 * @return Tous les EmpruntsResultatDto.
	 */
	@GetMapping("/emprunts")
	public ResponseEntity<Flux<EmpruntsResultatDto>> readEmprunts() {
		Usager usager = usagerService.findAuthenticateUsager();
		List<Pret> prets = pretService.findAll(PretSpecification.usagerEqual(usager));
		List<EmpruntsResultatDto> emprunts = prets.stream().map(pret -> {
			List<String> auteursPrenomNom = ecritureLivreService
					.findAll(EcritureLivreSpecification.livreIdEqual(pret.getLivre().getId())).stream()
					.map(ecritureLivre -> ecritureLivre.getAuteur().getPrenomNom()).collect(Collectors.toList());
			EmpruntsResultatDto empruntsResultatDto = new EmpruntsResultatDto();
			empruntsResultatDto.setPretId(pret.getId());
			empruntsResultatDto.setBibliotheque(pret.getLivre().getBibliotheque().getNom());
			empruntsResultatDto.setTitre(pret.getLivre().getTitre());
			empruntsResultatDto.setAuteursPrenomNom(auteursPrenomNom);
			empruntsResultatDto.setDateRetour(Utils.getDateRetourPretFormat(pret));
			empruntsResultatDto.setProlongations(pret.getNbProlongations());
			empruntsResultatDto.setRelances(pret.getNbRelances());
			return empruntsResultatDto;
		}).collect(Collectors.toList());
		return emprunts == null || emprunts.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(Flux.fromIterable(emprunts));
	}

	/**
	 * Méthode exécutée à l'appel de l'URI GET "/usager/current".
	 * 
	 * @return CurrentUsagerDto authentifié.
	 */
	@GetMapping("/current")
	public ResponseEntity<Mono<CurrentUsagerDto>> readCurrent() {
		UsagerDto usagerDto = usagerMapper.mapToDto(usagerService.findAuthenticateUsager());
		IdentifiantDto identifiantDto = identifiantMapper
				.mapToDto(identifiantService.findById(usagerDto.getIdentifiantId()));
		AdresseDto adresseDto = adresseMapper.mapToDto(adresseService.findById(usagerDto.getAdresseId()));
		LieuDto lieuDto = lieuMapper.mapToDto(lieuService.findById(adresseDto.getLieuId()));

		CurrentUsagerDto currentUsagerDto = new CurrentUsagerDto();
		currentUsagerDto.setUsagerId(usagerDto.getId());
		currentUsagerDto.setPrenom(usagerDto.getPrenom());
		currentUsagerDto.setNom(usagerDto.getNom());
		currentUsagerDto.setDateNaissance(usagerDto.getDateNaissance());
		currentUsagerDto.setIdentifiantId(identifiantDto.getId());
		currentUsagerDto.setEmail(identifiantDto.getEmail());
		currentUsagerDto.setIsActif(identifiantDto.getIsActif());
		currentUsagerDto.setAdresseId(adresseDto.getId());
		currentUsagerDto.setNumeroRue(adresseDto.getNumeroRue());
		currentUsagerDto.setRue(adresseDto.getRue());
		currentUsagerDto.setLieuId(lieuDto.getId());
		currentUsagerDto.setRegion(lieuDto.getRegion());
		currentUsagerDto.setDepartement(lieuDto.getDepartement());
		currentUsagerDto.setCodePostal(lieuDto.getCodePostal());
		currentUsagerDto.setVille(lieuDto.getVille());

		return usagerDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(Mono.just(currentUsagerDto));
	}
}
