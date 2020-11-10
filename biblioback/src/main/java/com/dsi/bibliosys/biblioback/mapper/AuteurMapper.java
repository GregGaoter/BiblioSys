package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.AuteurDto;
import com.dsi.bibliosys.biblioback.data.entity.Auteur;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Auteur
 * et l'entité DTO AuteurDto.
 */
@Component
public class AuteurMapper extends AbstractMapper implements Mapper<Auteur, AuteurDto> {

	@Override
	public AuteurDto mapToDto(@NonNull Auteur source) {
		AuteurDto auteurDto = new AuteurDto();
		auteurDto.setId(source.getId());
		auteurDto.setPrenomNom(source.getPrenomNom());
		return auteurDto;
	}

	@Override
	public Auteur mapToEntity(@NonNull AuteurDto source) {
		Auteur auteur = new Auteur();
		auteur.setPrenomNom(source.getPrenomNom());
		return auteur;
	}

}
