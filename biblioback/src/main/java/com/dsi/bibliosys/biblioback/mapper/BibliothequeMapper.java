package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.BibliothequeDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.service.AdresseService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business
 * Bibliotheque et l'entité DTO BibliothequeDto.
 */
@Component
public class BibliothequeMapper extends AbstractMapper implements Mapper<Bibliotheque, BibliothequeDto> {

	/**
	 * Constructeur.
	 * 
	 * @param adresseService Service de l'entité business Adresse.
	 */
	public BibliothequeMapper(AdresseService adresseService) {
		this.adresseService = adresseService;
	}

	@Override
	public BibliothequeDto mapToDto(@NonNull Bibliotheque source) {
		BibliothequeDto bibliothequeDto = new BibliothequeDto();
		Adresse adresse = source.getAdresse();
		bibliothequeDto.setId(source.getId());
		bibliothequeDto.setNom(source.getNom());
		bibliothequeDto.setAdresseId(adresse == null ? null : adresse.getId());
		return bibliothequeDto;
	}

	@Override
	public Bibliotheque mapToEntity(@NonNull BibliothequeDto source) {
		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom(source.getNom());
		bibliotheque.setAdresse(adresseService.findById(source.getAdresseId()));
		return bibliotheque;
	}

}
