package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.repository.GenreRepository;

/**
 * Classe fournissant les services de l'entité business Genre.
 */
@Service
public class GenreService implements CrudService<Genre, Integer> {

	private final GenreRepository genreRepository;

	@Autowired
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public JpaRepository<Genre, Integer> getRepository() {
		return genreRepository;
	}

	@Override
	public Genre create() {
		return new Genre();
	}

}
