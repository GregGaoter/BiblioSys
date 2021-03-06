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
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.repository.BibliothequeRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class BibliothequeServiceTest {

	private BibliothequeService bibliothequeService;

	@Mock
	private BibliothequeRepository bibliothequeRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		bibliothequeService = new BibliothequeService(bibliothequeRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		bibliothequeService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Bibliotheque, Integer> repository = bibliothequeService.getRepository();

		assertThat(repository).isEqualTo(bibliothequeRepository);
	}

	@Test
	public void create() {
		final Bibliotheque bibliotheque = bibliothequeService.create();

		assertThat(bibliotheque).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Bibliotheque bibliotheque = new Bibliotheque();

		given(bibliothequeRepository.saveAndFlush(bibliotheque)).willAnswer(invocation -> invocation.getArgument(0));

		final Bibliotheque bibliothequeSaved = bibliothequeService.save(bibliotheque);

		verify(bibliothequeRepository).saveAndFlush(bibliotheque);
		assertThat(bibliothequeSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Bibliotheque bibliothequeSource = new Bibliotheque();
		bibliothequeSource.setAdresse(new Adresse());
		bibliothequeSource.setNom("nom");

		final Integer idTarget = 1;

		final Optional<Bibliotheque> bibliothequeTargetOptional = Optional.of(new Bibliotheque());
		ReflectionTestUtils.setField(bibliothequeTargetOptional.get(), Bibliotheque.ID, idTarget);

		given(bibliothequeRepository.findById(idTarget)).willReturn(bibliothequeTargetOptional);
		given(bibliothequeRepository.saveAndFlush(any(Bibliotheque.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		final Bibliotheque bibliothequeUpdated = bibliothequeService.update(bibliothequeSource, idTarget);

		verify(bibliothequeRepository).findById(idTarget);
		verify(bibliothequeRepository).saveAndFlush(any(Bibliotheque.class));
		assertThat(bibliothequeUpdated.getId()).isEqualTo(idTarget);
		assertThat(bibliothequeUpdated).isEqualToIgnoringGivenFields(bibliothequeSource, Bibliotheque.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Bibliotheque(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Bibliotheque source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> bibliothequeService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Bibliotheque bibliotheque = new Bibliotheque();

		bibliothequeService.delete(bibliotheque);

		verify(bibliothequeRepository).delete(bibliotheque);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Bibliotheque> bibliothequeOptional = Optional.of(new Bibliotheque());
		ReflectionTestUtils.setField(bibliothequeOptional.get(), Bibliotheque.ID, id);

		given(bibliothequeRepository.findById(id)).willReturn(bibliothequeOptional);

		bibliothequeService.deleteById(1);

		verify(bibliothequeRepository).deleteById(bibliothequeOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> bibliothequeService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(bibliothequeRepository.count()).willReturn(nbExpected);

		final long nbActual = bibliothequeService.count();

		verify(bibliothequeRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Bibliotheque> bibliothequeOptional = Optional.of(new Bibliotheque());
		ReflectionTestUtils.setField(bibliothequeOptional.get(), Bibliotheque.ID, id);

		given(bibliothequeRepository.findById(id)).willReturn(bibliothequeOptional);

		final Bibliotheque bibliothequeActual = bibliothequeService.findById(id);

		verify(bibliothequeRepository).findById(id);
		assertThat(bibliothequeActual).isEqualToComparingFieldByField(bibliothequeOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Bibliotheque bibliotheque1 = new Bibliotheque();
		ReflectionTestUtils.setField(bibliotheque1, Bibliotheque.ID, 1);
		final Bibliotheque bibliotheque2 = new Bibliotheque();
		ReflectionTestUtils.setField(bibliotheque2, Bibliotheque.ID, 2);
		final Bibliotheque bibliotheque3 = new Bibliotheque();
		ReflectionTestUtils.setField(bibliotheque3, Bibliotheque.ID, 3);

		final List<Bibliotheque> bibliothequesExpected = new ArrayList<>(3);
		bibliothequesExpected.add(bibliotheque1);
		bibliothequesExpected.add(bibliotheque2);
		bibliothequesExpected.add(bibliotheque3);

		given(bibliothequeRepository.findAll()).willReturn(bibliothequesExpected);

		final List<Bibliotheque> bibliothequesActual = bibliothequeService.findAll();

		verify(bibliothequeRepository).findAll();
		assertThat(bibliothequesActual).containsExactlyInAnyOrderElementsOf(bibliothequesExpected);
	}
}
