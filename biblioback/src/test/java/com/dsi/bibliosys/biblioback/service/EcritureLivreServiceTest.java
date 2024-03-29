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
import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.repository.EcritureLivreRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class EcritureLivreServiceTest {

	private EcritureLivreService ecritureLivreService;

	@Mock
	private EcritureLivreRepository ecritureLivreRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		ecritureLivreService = new EcritureLivreService(ecritureLivreRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		ecritureLivreService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<EcritureLivre, Integer> repository = ecritureLivreService.getRepository();

		assertThat(repository).isEqualTo(ecritureLivreRepository);
	}

	@Test
	public void create() {
		final EcritureLivre ecritureLivre = ecritureLivreService.create();

		assertThat(ecritureLivre).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final EcritureLivre ecritureLivre = new EcritureLivre();

		given(ecritureLivreRepository.saveAndFlush(ecritureLivre)).willAnswer(invocation -> invocation.getArgument(0));

		final EcritureLivre ecritureLivreSaved = ecritureLivreService.saveAndFlush(ecritureLivre);

		verify(ecritureLivreRepository).saveAndFlush(ecritureLivre);
		assertThat(ecritureLivreSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final EcritureLivre ecritureLivreSource = new EcritureLivre();
		ecritureLivreSource.setAuteur(new Auteur());
		ecritureLivreSource.setLivre(new Livre());

		final Integer idTarget = 1;

		final Optional<EcritureLivre> ecritureLivreTargetOptional = Optional.of(new EcritureLivre());
		ReflectionTestUtils.setField(ecritureLivreTargetOptional.get(), EcritureLivre.ID, idTarget);

		given(ecritureLivreRepository.findById(idTarget)).willReturn(ecritureLivreTargetOptional);
		given(ecritureLivreRepository.saveAndFlush(any(EcritureLivre.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		final EcritureLivre ecritureLivreUpdated = ecritureLivreService.update(ecritureLivreSource, idTarget);

		verify(ecritureLivreRepository).findById(idTarget);
		verify(ecritureLivreRepository).saveAndFlush(any(EcritureLivre.class));
		assertThat(ecritureLivreUpdated.getId()).isEqualTo(idTarget);
		assertThat(ecritureLivreUpdated).isEqualToIgnoringGivenFields(ecritureLivreSource, EcritureLivre.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new EcritureLivre(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(EcritureLivre source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> ecritureLivreService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final EcritureLivre ecritureLivre = new EcritureLivre();

		ecritureLivreService.delete(ecritureLivre);

		verify(ecritureLivreRepository).delete(ecritureLivre);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<EcritureLivre> ecritureLivreOptional = Optional.of(new EcritureLivre());
		ReflectionTestUtils.setField(ecritureLivreOptional.get(), EcritureLivre.ID, id);

		given(ecritureLivreRepository.findById(id)).willReturn(ecritureLivreOptional);

		ecritureLivreService.deleteById(1);

		verify(ecritureLivreRepository).deleteById(ecritureLivreOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> ecritureLivreService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(ecritureLivreRepository.count()).willReturn(nbExpected);

		final long nbActual = ecritureLivreService.count();

		verify(ecritureLivreRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<EcritureLivre> ecritureLivreOptional = Optional.of(new EcritureLivre());
		ReflectionTestUtils.setField(ecritureLivreOptional.get(), EcritureLivre.ID, id);

		given(ecritureLivreRepository.findById(id)).willReturn(ecritureLivreOptional);

		final EcritureLivre ecritureLivreActual = ecritureLivreService.findById(id);

		verify(ecritureLivreRepository).findById(id);
		assertThat(ecritureLivreActual).isEqualToComparingFieldByField(ecritureLivreOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final EcritureLivre ecritureLivre1 = new EcritureLivre();
		ReflectionTestUtils.setField(ecritureLivre1, EcritureLivre.ID, 1);
		final EcritureLivre ecritureLivre2 = new EcritureLivre();
		ReflectionTestUtils.setField(ecritureLivre2, EcritureLivre.ID, 2);
		final EcritureLivre ecritureLivre3 = new EcritureLivre();
		ReflectionTestUtils.setField(ecritureLivre3, EcritureLivre.ID, 3);

		final List<EcritureLivre> ecritureLivresExpected = new ArrayList<>(3);
		ecritureLivresExpected.add(ecritureLivre1);
		ecritureLivresExpected.add(ecritureLivre2);
		ecritureLivresExpected.add(ecritureLivre3);

		given(ecritureLivreRepository.findAll()).willReturn(ecritureLivresExpected);

		final List<EcritureLivre> ecritureLivresActual = ecritureLivreService.findAll();

		verify(ecritureLivreRepository).findAll();
		assertThat(ecritureLivresActual).containsExactlyInAnyOrderElementsOf(ecritureLivresExpected);
	}

}
