package com.dsi.bibliosys.biblioback.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.biblioback.data.dto.RegistrationDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.LieuService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

/**
 * Contrôleur REST de l'inscription.
 */
@RestController
public class RegistrationController {

	private IdentifiantService identifiantService;
	private LieuService lieuService;
	private AdresseService adresseService;
	private UsagerService usagerService;
	private PasswordEncoder passwordEncoder;

	private RegistrationController(IdentifiantService identifiantService, LieuService lieuService,
			AdresseService adresseService, UsagerService usagerService, PasswordEncoder passwordEncoder) {
		this.identifiantService = identifiantService;
		this.lieuService = lieuService;
		this.adresseService = adresseService;
		this.usagerService = usagerService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/registration")
	public ResponseEntity<Void> signUp(@RequestBody RegistrationDto registrationDto) {
		Identifiant identifiant = new Identifiant();
		identifiant.setEmail(registrationDto.getUsername());
		identifiant.setMotDePasse(passwordEncoder.encode(registrationDto.getPassword()));
		identifiant.setIsActif(true);
		identifiantService.saveAndFlush(identifiant);

		Lieu lieu = new Lieu();
		lieu.setCodePostal(registrationDto.getCodePostal());
		lieu.setDepartement("Inconnu");
		lieu.setRegion("Inconnu");
		lieu.setVille(registrationDto.getVille());
		lieuService.saveAndFlush(lieu);

		Adresse adresse = new Adresse();
		adresse.setNumeroRue(registrationDto.getNumeroRue());
		adresse.setRue(registrationDto.getRue());
		adresse.setLieu(lieu);
		adresseService.saveAndFlush(adresse);

		Usager usager = new Usager();
		usager.setPrenom(registrationDto.getPrenom());
		usager.setNom(registrationDto.getNom());
		usager.setDateNaissance(LocalDateTime.parse(registrationDto.getDateNaissance().replace("Z", "")));
		usager.setIdentifiant(identifiant);
		usager.setAdresse(adresse);
		usagerService.saveAndFlush(usager);

		return ResponseEntity.ok().build();
	}

}
