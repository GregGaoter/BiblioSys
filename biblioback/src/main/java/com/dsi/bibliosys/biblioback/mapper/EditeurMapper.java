package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.EditeurDto;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Editeur
 * et l'entité DTO EditeurDto.
 */
@Component
public class EditeurMapper extends AbstractMapper implements Mapper<Editeur, EditeurDto> {

	@Override
	public EditeurDto mapToDto(@NonNull Editeur source) {
		EditeurDto editeurDto = new EditeurDto();
		editeurDto.setId(source.getId());
		editeurDto.setNom(source.getNom());
		return editeurDto;
	}

	@Override
	public Editeur mapToEntity(@NonNull EditeurDto source) {
		Editeur editeur = new Editeur();
		editeur.setNom(source.getNom());
		return editeur;
	}

}
