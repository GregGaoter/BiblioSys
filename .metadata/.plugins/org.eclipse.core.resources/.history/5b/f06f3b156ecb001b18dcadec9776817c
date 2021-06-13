package com.dsi.bibliosys.biblioback.service;

import static com.dsi.bibliosys.biblioback.repository.specification.GenreSpecification.rayonIdEqual;
import static com.dsi.bibliosys.biblioback.repository.specification.LivreSpecification.genreIdEqual;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.dto.AuteurDto;
import com.dsi.bibliosys.biblioback.data.dto.LivreDto;
import com.dsi.bibliosys.biblioback.data.dto.LivreResultatDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.mapper.AuteurMapper;
import com.dsi.bibliosys.biblioback.repository.GenreRepository;
import com.dsi.bibliosys.biblioback.repository.LivreRepository;

/**
 * Classe fournissant les services de l'entité business Livre.
 */
@Service
public class LivreService implements CrudService<Livre, Integer> {

	private final LivreRepository livreRepository;
	private final GenreRepository genreRepository;

	@Autowired
	private AuteurService auteurService;

	@Autowired
	private AuteurMapper auteurMapper;

	@Autowired
	public LivreService(LivreRepository livreRepository, GenreRepository genreRepository) {
		this.livreRepository = livreRepository;
		this.genreRepository = genreRepository;
	}

	@Override
	public JpaRepository<Livre, Integer> getRepository() {
		return livreRepository;
	}

	@Override
	public Livre create() {
		return new Livre();
	}

	public List<Livre> findAll(Specification<Livre> spec) {
		return livreRepository.findAll(spec);
	}

	public List<Livre> findByRayonId(Integer id) {
		List<Genre> genres = genreRepository.findAll(rayonIdEqual(id));
		return genres.stream().flatMap(genre -> findAll(genreIdEqual(genre.getId())).stream())
				.collect(Collectors.toList());
	}

	public List<Livre> findByGenreId(Integer id) {
		return livreRepository.findAll(genreIdEqual(id));
	}

	public LivreResultatDto getLivreResultatDto(LivreDto livreDto) {
		AuteurDto auteurDto = auteurMapper.mapToDto(auteurService.findByLivreId(livreDto.getId()));
		LivreResultatDto livreResultatDto = new LivreResultatDto();
		livreResultatDto.setLivre(livreDto);
		livreResultatDto.setAuteur(auteurDto);
		return livreResultatDto;
	}
	
	public List<LivreResultatDto> getLivreResultatsDto(List<LivreDto> livresDto) {
		return livresDto.stream().map(livreDto -> {
			AuteurDto auteurDto = auteurMapper.mapToDto(auteurService.findByLivreId(livreDto.getId()));
			LivreResultatDto livreResultatDto = new LivreResultatDto();
			livreResultatDto.setLivre(livreDto);
			livreResultatDto.setAuteur(auteurDto);
			return livreResultatDto;
		}).collect(Collectors.toList());
	}

}
