package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.GenreDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Rayon;
import com.dsi.bibliosys.biblioback.service.RayonService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Genre et
 * l'entité DTO GenreDto.
 */
@Component
public class GenreMapper extends AbstractMapper implements Mapper<Genre, GenreDto> {

	/**
	 * Constructeur.
	 * 
	 * @param rayonService Service de l'entité business Rayon.
	 */
	public GenreMapper(RayonService rayonService) {
		this.rayonService = rayonService;
	}

	@Override
	public GenreDto mapToDto(@NonNull Genre source) {
		GenreDto genreDto = new GenreDto();
		Rayon rayon = source.getRayon();
		genreDto.setId(source.getId());
		genreDto.setNom(source.getNom());
		genreDto.setRayonId(rayon == null ? null : rayon.getId());
		return genreDto;
	}

	@Override
	public Genre mapToEntity(@NonNull GenreDto source) {
		Genre genre = new Genre();
		genre.setNom(source.getNom());
		genre.setRayon(rayonService.findById(source.getRayonId()));
		return genre;
	}

}
