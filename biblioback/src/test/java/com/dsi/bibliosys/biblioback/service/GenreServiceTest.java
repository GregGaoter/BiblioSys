package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.repository.GenreRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class GenreServiceTest {

	private GenreService genreService;

	@Mock
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
		final Genre genre = new Genre();

		given(genreRepository.saveAndFlush(genre)).willAnswer(invocation -> invocation.getArgument(0));

		final Genre genreSaved = genreService.save(genre);

		verify(genreRepository).saveAndFlush(genre);
		assertThat(genreSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Genre genreSource = new Genre();
		genreSource.setNom("nom");

		final Integer idTarget = 1;

		final Optional<Genre> genreTargetOptional = Optional.of(new Genre());
		ReflectionTestUtils.setField(genreTargetOptional.get(), Genre.ID, idTarget);

		given(genreRepository.findById(idTarget)).willReturn(genreTargetOptional);
		given(genreRepository.saveAndFlush(any(Genre.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Genre genreUpdated = genreService.update(genreSource, idTarget);

		verify(genreRepository).findById(idTarget);
		verify(genreRepository).saveAndFlush(any(Genre.class));
		assertThat(genreUpdated.getId()).isEqualTo(idTarget);
		assertThat(genreUpdated).isEqualToIgnoringGivenFields(genreSource, Genre.ID);
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
		final Genre genre = new Genre();

		genreService.delete(genre);

		verify(genreRepository).delete(genre);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Genre> genreOptional = Optional.of(new Genre());
		ReflectionTestUtils.setField(genreOptional.get(), Genre.ID, id);

		given(genreRepository.findById(id)).willReturn(genreOptional);

		genreService.deleteById(1);

		verify(genreRepository).deleteById(genreOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(genreRepository.count()).willReturn(nbExpected);

		final long nbActual = genreService.count();

		verify(genreRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Genre> genreOptional = Optional.of(new Genre());
		ReflectionTestUtils.setField(genreOptional.get(), Genre.ID, id);

		given(genreRepository.findById(id)).willReturn(genreOptional);

		final Genre genreActual = genreService.findById(id);

		verify(genreRepository).findById(id);
		assertThat(genreActual).isEqualToComparingFieldByField(genreOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> genreService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Genre genre1 = new Genre();
		ReflectionTestUtils.setField(genre1, Genre.ID, 1);
		final Genre genre2 = new Genre();
		ReflectionTestUtils.setField(genre2, Genre.ID, 2);
		final Genre genre3 = new Genre();
		ReflectionTestUtils.setField(genre3, Genre.ID, 3);

		final List<Genre> genresExpected = new ArrayList<>(3);
		genresExpected.add(genre1);
		genresExpected.add(genre2);
		genresExpected.add(genre3);

		given(genreRepository.findAll()).willReturn(genresExpected);

		final List<Genre> genresActual = genreService.findAll();

		verify(genreRepository).findAll();
		assertThat(genresActual).containsExactlyInAnyOrderElementsOf(genresExpected);
	}

}
