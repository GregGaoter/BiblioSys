package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.PersonnelDto;
import com.dsi.bibliosys.biblioback.data.entity.Personnel;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business
 * Personnel et l'entité DTO PersonnelDto.
 */
@Component
public class PersonnelMapper extends AbstractMapper implements Mapper<Personnel, PersonnelDto> {

	/**
	 * 
	 * @param identifiantService  Service de l'entité business Identifiant.
	 * @param adresseService      Service de l'entité business Adresse.
	 * @param bibliothequeService Service de l'entité business Bibliotheque.
	 */
	public PersonnelMapper(IdentifiantService identifiantService, AdresseService adresseService,
			BibliothequeService bibliothequeService) {
		this.identifiantService = identifiantService;
		this.adresseService = adresseService;
		this.bibliothequeService = bibliothequeService;
	}

	@Override
	public PersonnelDto mapToDto(@NonNull Personnel source) {
		PersonnelDto personnelDto = new PersonnelDto();
		personnelDto.setId(source.getId());
		personnelDto.setPrenom(source.getPrenom());
		personnelDto.setNom(source.getNom());
		personnelDto.setDateNaissance(source.getDateNaissance());
		personnelDto.setIdentifiantId(source.getIdentifiant().getId());
		personnelDto.setAdresseId(source.getAdresse().getId());
		personnelDto.setBibliothequeId(source.getBibliotheque().getId());
		return personnelDto;
	}

	@Override
	public Personnel mapToEntity(@NonNull PersonnelDto source) {
		Personnel personnel = new Personnel();
		personnel.setPrenom(source.getPrenom());
		personnel.setNom(source.getNom());
		personnel.setDateNaissance(source.getDateNaissance());
		personnel.setIdentifiant(identifiantService.findById(source.getIdentifiantId()));
		personnel.setAdresse(adresseService.findById(source.getAdresseId()));
		personnel.setBibliotheque(bibliothequeService.findById(source.getBibliothequeId()));
		return personnel;
	}

}
