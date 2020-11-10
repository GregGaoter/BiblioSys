package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.GenreDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Genre et
 * l'entité DTO GenreDto.
 */
@Component
public class GenreMapper extends AbstractMapper implements Mapper<Genre, GenreDto> {

	@Override
	public GenreDto mapToDto(@NonNull Genre source) {
		GenreDto genreDto = new GenreDto();
		genreDto.setId(source.getId());
		genreDto.setNom(source.getNom());
		return genreDto;
	}

	@Override
	public Genre mapToEntity(@NonNull GenreDto source) {
		Genre genre = new Genre();
		genre.setNom(source.getNom());
		return genre;
	}

}
