package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.UsagerDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Usager
 * et l'entité DTO UsagerDto.
 */
@Component
public class UsagerMapper extends AbstractMapper implements Mapper<Usager, UsagerDto> {

	/**
	 * Constructeur.
	 * 
	 * @param identifiantService Service de l'entité business Identifiant.
	 * @param adresseService     Service de l'entité business Adresse.
	 */
	public UsagerMapper(IdentifiantService identifiantService, AdresseService adresseService) {
		this.identifiantService = identifiantService;
		this.adresseService = adresseService;
	}

	@Override
	public UsagerDto mapToDto(@NonNull Usager source) {
		UsagerDto usagerDto = new UsagerDto();
		Identifiant identifiant = source.getIdentifiant();
		Adresse adresse = source.getAdresse();
		usagerDto.setId(source.getId());
		usagerDto.setPrenom(source.getPrenom());
		usagerDto.setNom(source.getNom());
		usagerDto.setDateNaissance(source.getDateNaissance());
		usagerDto.setIdentifiantId(identifiant == null ? null : identifiant.getId());
		usagerDto.setAdresseId(adresse == null ? null : adresse.getId());
		return usagerDto;
	}

	@Override
	public Usager mapToEntity(@NonNull UsagerDto source) {
		Usager usager = new Usager();
		usager.setPrenom(source.getPrenom());
		usager.setNom(source.getNom());
		usager.setDateNaissance(source.getDateNaissance());
		usager.setIdentifiant(identifiantService.findById(source.getIdentifiantId()));
		usager.setAdresse(adresseService.findById(source.getAdresseId()));
		return usager;
	}

}
