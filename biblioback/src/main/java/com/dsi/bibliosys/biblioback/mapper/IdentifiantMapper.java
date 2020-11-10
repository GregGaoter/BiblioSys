package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.IdentifiantDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business
 * Identifiant et l'entité DTO IdentifiantDto.
 */
@Component
public class IdentifiantMapper extends AbstractMapper implements Mapper<Identifiant, IdentifiantDto> {

	@Override
	public IdentifiantDto mapToDto(@NonNull Identifiant source) {
		IdentifiantDto identifiantDto = new IdentifiantDto();
		identifiantDto.setId(source.getId());
		identifiantDto.setEmail(source.getEmail());
		identifiantDto.setMotDePasse(source.getMotDePasse());
		identifiantDto.setIsActif(source.getIsActif());
		return identifiantDto;
	}

	@Override
	public Identifiant mapToEntity(@NonNull IdentifiantDto source) {
		Identifiant identifiant = new Identifiant();
		identifiant.setEmail(source.getEmail());
		identifiant.setMotDePasse(source.getMotDePasse());
		identifiant.setIsActif(source.getIsActif());
		return identifiant;
	}

}
