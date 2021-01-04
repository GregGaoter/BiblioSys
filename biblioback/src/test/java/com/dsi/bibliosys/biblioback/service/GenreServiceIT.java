package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.repository.GenreRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class GenreServiceIT {

	private GenreService genreService;

	@Autowired
	private GenreRepository genreRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		genreService = new GenreService(genreRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		genreService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Genre, Integer> repository = genreService.getRepository();

		assertThat(repository).isEqualTo(genreRepository);
	}

	@Test
	public void create() {
		final Genre genre = genreService.create();

		assertThat(genre).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Genre genreExcpected = new Genre();
		genreExcpected.setNom("Nom");

		final Genre genreSaved = genreService.save(genreExcpected);
		final Genre genreFind = genreService.findById(genreSaved.getId());

		assertThat(genreFind.getNom()).isEqualTo(genreExcpected.getNom());
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Genre genreSource = new Genre();
		genreSource.setNom("Nom");

		final Integer idTarget = 1;

		final Genre genreUpdated = genreService.update(genreSource, idTarget);

		assertThat(genreUpdated).extracting(Genre::getId, Genre::getNom).containsExactly(genreUpdated.getId(),
				genreSource.getNom());
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Genre(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Genre source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final Genre genre = genreService.findById(id);

		genreService.delete(genre);

		assertThrows(EntityNotFoundException.class, () -> genreService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		genreService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> genreService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = genreService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer genreId = 1;

		final Genre genreActual = genreService.findById(genreId);

		assertThat(genreActual).extracting(Genre::getId, Genre::getNom).containsExactly(genreId, "Genre 1");
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleGenre1 = tuple(1, "Genre 1");
		final Tuple tupleGenre2 = tuple(2, "Genre 2");
		final Tuple tupleGenre3 = tuple(3, "Genre 3");

		final List<Genre> genresActual = genreService.findAll();

		assertThat(genresActual).extracting(Genre::getId, Genre::getNom).containsOnly(tupleGenre1, tupleGenre2,
				tupleGenre3);
	}

}
