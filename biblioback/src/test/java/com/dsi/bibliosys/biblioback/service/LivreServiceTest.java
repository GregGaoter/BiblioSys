package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
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
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.repository.LivreRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class LivreServiceTest {

	private LivreService livreService;

	@Mock
	private LivreRepository livreRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		livreService = new LivreService(livreRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		livreService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Livre, Integer> repository = livreService.getRepository();

		assertThat(repository).isEqualTo(livreRepository);
	}

	@Test
	public void create() {
		final Livre livre = livreService.create();

		assertThat(livre).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Livre livre = new Livre();

		given(livreRepository.saveAndFlush(livre)).willAnswer(invocation -> invocation.getArgument(0));

		final Livre livreSaved = livreService.save(livre);

		verify(livreRepository).saveAndFlush(livre);
		assertThat(livreSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> livreService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Livre livreSource = new Livre();
		livreSource.setBibliotheque(new Bibliotheque());
		livreSource.setCollection(new Collection());
		livreSource.setDateParution(LocalDateTime.of(2020, 11, 11, 15, 12));
		livreSource.setDimension("dimension");
		livreSource.setEan13("ean13");
		livreSource.setEditeur(new Editeur());
		livreSource.setGenre(new Genre());
		livreSource.setNbExemplaires(4);
		livreSource.setNbPages(330);
		livreSource.setNomImage("nomImage");
		livreSource.setResume("resume");
		livreSource.setTitre("titre");

		final Integer idTarget = 1;

		final Optional<Livre> livreTargetOptional = Optional.of(new Livre());
		ReflectionTestUtils.setField(livreTargetOptional.get(), Livre.ID, idTarget);

		given(livreRepository.findById(idTarget)).willReturn(livreTargetOptional);
		given(livreRepository.saveAndFlush(any(Livre.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Livre livreUpdated = livreService.update(livreSource, idTarget);

		verify(livreRepository).findById(idTarget);
		verify(livreRepository).saveAndFlush(any(Livre.class));
		assertThat(livreUpdated.getId()).isEqualTo(idTarget);
		assertThat(livreUpdated).isEqualToIgnoringGivenFields(livreSource, Livre.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Livre(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Livre source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> livreService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Livre livre = new Livre();

		livreService.delete(livre);

		verify(livreRepository).delete(livre);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> livreService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Livre> livreOptional = Optional.of(new Livre());
		ReflectionTestUtils.setField(livreOptional.get(), Livre.ID, id);

		given(livreRepository.findById(id)).willReturn(livreOptional);

		livreService.deleteById(1);

		verify(livreRepository).deleteById(livreOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> livreService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(livreRepository.count()).willReturn(nbExpected);

		final long nbActual = livreService.count();

		verify(livreRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Livre> livreOptional = Optional.of(new Livre());
		ReflectionTestUtils.setField(livreOptional.get(), Livre.ID, id);

		given(livreRepository.findById(id)).willReturn(livreOptional);

		final Livre livreActual = livreService.findById(id);

		verify(livreRepository).findById(id);
		assertThat(livreActual).isEqualToComparingFieldByField(livreOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> livreService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Livre livre1 = new Livre();
		ReflectionTestUtils.setField(livre1, Livre.ID, 1);
		final Livre livre2 = new Livre();
		ReflectionTestUtils.setField(livre2, Livre.ID, 2);
		final Livre livre3 = new Livre();
		ReflectionTestUtils.setField(livre3, Livre.ID, 3);

		final List<Livre> livresExpected = new ArrayList<>(3);
		livresExpected.add(livre1);
		livresExpected.add(livre2);
		livresExpected.add(livre3);

		given(livreRepository.findAll()).willReturn(livresExpected);

		final List<Livre> livresActual = livreService.findAll();

		verify(livreRepository).findAll();
		assertThat(livresActual).containsExactlyInAnyOrderElementsOf(livresExpected);
	}

}
