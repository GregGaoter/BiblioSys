package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.LieuDto;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Lieu et
 * l'entité DTO LieuDto.
 */
@Component
public class LieuMapper extends AbstractMapper implements Mapper<Lieu, LieuDto> {

	@Override
	public LieuDto mapToDto(@NonNull Lieu source) {
		LieuDto lieuDto = new LieuDto();
		lieuDto.setId(source.getId());
		lieuDto.setRegion(source.getRegion());
		lieuDto.setDepartement(source.getDepartement());
		lieuDto.setCodePostal(source.getCodePostal());
		lieuDto.setVille(source.getVille());
		return lieuDto;
	}

	@Override
	public Lieu mapToEntity(@NonNull LieuDto source) {
		Lieu lieu = new Lieu();
		lieu.setRegion(source.getRegion());
		lieu.setDepartement(source.getDepartement());
		lieu.setCodePostal(source.getCodePostal());
		lieu.setVille(source.getVille());
		return lieu;
	}

}
