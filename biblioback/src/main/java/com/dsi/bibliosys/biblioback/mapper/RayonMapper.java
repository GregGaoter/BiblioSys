package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.RayonDto;
import com.dsi.bibliosys.biblioback.data.entity.Rayon;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Rayon et
 * l'entité DTO RayonDto.
 */
@Component
public class RayonMapper extends AbstractMapper implements Mapper<Rayon, RayonDto> {

	@Override
	public RayonDto mapToDto(@NonNull Rayon source) {
		RayonDto rayonDto = new RayonDto();
		rayonDto.setId(source.getId());
		rayonDto.setNom(source.getNom());
		return rayonDto;
	}

	@Override
	public Rayon mapToEntity(@NonNull RayonDto source) {
		Rayon rayon = new Rayon();
		rayon.setNom(source.getNom());
		return rayon;
	}

}
