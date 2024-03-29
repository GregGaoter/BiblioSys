package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.dto.LivreDto;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;
import com.dsi.bibliosys.biblioback.service.CollectionService;
import com.dsi.bibliosys.biblioback.service.EditeurService;
import com.dsi.bibliosys.biblioback.service.GenreService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestCase.INTEGRATION)
public class LivreMapperIT {

	private LivreMapper livreMapper;

	private static Integer id;
	private static Bibliotheque bibliotheque;
	private static Integer bibliothequeId;
	private static Genre genre;
	private static Integer genreId;
	private static String nomImage;
	private static String titre;
	private static Editeur editeur;
	private static Integer editeurId;
	private static Collection collection;
	private static Integer collectionId;
	private static LocalDateTime dateParution;
	private static String dimension;
	private static Integer nbPages;
	private static String ean13;
	private static Integer nbExemplaires;
	private static String resume;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Autowired
	private BibliothequeService bibliothequeService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private EditeurService editeurService;
	@Autowired
	private CollectionService collectionService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		bibliotheque = new Bibliotheque();
		bibliothequeId = 1;
		ReflectionTestUtils.setField(bibliotheque, Bibliotheque.ID, bibliothequeId);
		genre = new Genre();
		genreId = 1;
		ReflectionTestUtils.setField(genre, Genre.ID, genreId);
		nomImage = "les-vies-de-papier.jpg";
		titre = "Les vies de papier";
		editeur = new Editeur();
		editeurId = 1;
		ReflectionTestUtils.setField(editeur, Editeur.ID, editeurId);
		collection = new Collection();
		collectionId = 1;
		ReflectionTestUtils.setField(collection, Collection.ID, collectionId);
		dateParution = LocalDateTime.of(2016, 8, 15, 10, 30);
		dimension = "22.5 x 14 x 2.8 cm";
		nbPages = 330;
		ean13 = "9782365692069";
		nbExemplaires = 3;
		resume = "Aaliya, ancienne libraire de 72 ans, marginale aux cheveux bleus et personnage non conventionnel, a toujours lutté contre les carcans imposés par la société libanaise.";
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		bibliotheque = null;
		bibliothequeId = null;
		genre = null;
		genreId = null;
		nomImage = null;
		titre = null;
		editeur = null;
		editeurId = null;
		collection = null;
		collectionId = null;
		dateParution = null;
		dimension = null;
		nbPages = null;
		ean13 = null;
		nbExemplaires = null;
		resume = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		livreMapper = new LivreMapper(bibliothequeService, genreService, editeurService, collectionService);
	}

	@AfterEach
	public void unDefAfterEach() {
		livreMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Livre livre = new Livre();
		ReflectionTestUtils.setField(livre, Livre.ID, id);
		livre.setBibliotheque(bibliotheque);
		livre.setGenre(genre);
		livre.setNomImage(nomImage);
		livre.setTitre(titre);
		livre.setEditeur(editeur);
		livre.setCollection(collection);
		livre.setDateParution(dateParution);
		livre.setDimension(dimension);
		livre.setNbPages(nbPages);
		livre.setEan13(ean13);
		livre.setNbExemplaires(nbExemplaires);
		livre.setResume(resume);

		LivreDto livreDto = livreMapper.mapToDto(livre);

		assertThat(livreDto.getId()).isEqualTo(livre.getId());
		assertThat(livreDto.getBibliothequeId()).isEqualTo(livre.getBibliotheque().getId());
		assertThat(livreDto.getGenreId()).isEqualTo(livre.getGenre().getId());
		assertThat(livreDto.getNomImage()).isEqualTo(livre.getNomImage());
		assertThat(livreDto.getTitre()).isEqualTo(livre.getTitre());
		assertThat(livreDto.getEditeurId()).isEqualTo(livre.getEditeur().getId());
		assertThat(livreDto.getCollectionId()).isEqualTo(livre.getCollection().getId());
		assertThat(livreDto.getDateParution()).isEqualTo(livre.getDateParution());
		assertThat(livreDto.getDimension()).isEqualTo(livre.getDimension());
		assertThat(livreDto.getNbPages()).isEqualTo(livre.getNbPages());
		assertThat(livreDto.getEan13()).isEqualTo(livre.getEan13());
		assertThat(livreDto.getNbExemplaires()).isEqualTo(livre.getNbExemplaires());
		assertThat(livreDto.getResume()).isEqualTo(livre.getResume());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> livreMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		LivreDto livreDto = new LivreDto();
		livreDto.setBibliothequeId(bibliothequeId);
		livreDto.setGenreId(genreId);
		livreDto.setNomImage(nomImage);
		livreDto.setTitre(titre);
		livreDto.setEditeurId(editeurId);
		livreDto.setCollectionId(collectionId);
		livreDto.setDateParution(dateParution);
		livreDto.setDimension(dimension);
		livreDto.setNbPages(nbPages);
		livreDto.setEan13(ean13);
		livreDto.setNbExemplaires(nbExemplaires);
		livreDto.setResume(resume);

		Livre livre = livreMapper.mapToEntity(livreDto);

		assertThat(livre.getId()).isNull();
		assertThat(livre.getBibliotheque().getId()).isEqualTo(livreDto.getBibliothequeId());
		assertThat(livre.getGenre().getId()).isEqualTo(livreDto.getGenreId());
		assertThat(livre.getNomImage()).isEqualTo(livreDto.getNomImage());
		assertThat(livre.getTitre()).isEqualTo(livreDto.getTitre());
		assertThat(livre.getEditeur().getId()).isEqualTo(livreDto.getEditeurId());
		assertThat(livre.getCollection().getId()).isEqualTo(livreDto.getCollectionId());
		assertThat(livre.getDateParution()).isEqualTo(livreDto.getDateParution());
		assertThat(livre.getDimension()).isEqualTo(livreDto.getDimension());
		assertThat(livre.getNbPages()).isEqualTo(livreDto.getNbPages());
		assertThat(livre.getEan13()).isEqualTo(livreDto.getEan13());
		assertThat(livre.getNbExemplaires()).isEqualTo(livreDto.getNbExemplaires());
		assertThat(livre.getResume()).isEqualTo(livreDto.getResume());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> livreMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
