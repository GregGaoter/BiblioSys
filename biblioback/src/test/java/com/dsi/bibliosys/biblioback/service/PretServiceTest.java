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
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.PretRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class PretServiceTest {

	private PretService pretService;

	@Mock
	private PretRepository pretRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		pretService = new PretService(pretRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		pretService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Pret, Integer> repository = pretService.getRepository();

		assertThat(repository).isEqualTo(pretRepository);
	}

	@Test
	public void create() {
		final Pret pret = pretService.create();

		assertThat(pret).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Pret pret = new Pret();

		given(pretRepository.saveAndFlush(pret)).willAnswer(invocation -> invocation.getArgument(0));

		final Pret pretSaved = pretService.saveAndFlush(pret);

		verify(pretRepository).saveAndFlush(pret);
		assertThat(pretSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> pretService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Pret pretSource = new Pret();
		pretSource.setDatePret(LocalDateTime.of(2020, 11, 11, 15, 18));
		pretSource.setLivre(new Livre());
		pretSource.setNbProlongations(0);
		pretSource.setNbRelances(1);
		pretSource.setUsager(new Usager());

		final Integer idTarget = 1;

		final Optional<Pret> pretTargetOptional = Optional.of(new Pret());
		ReflectionTestUtils.setField(pretTargetOptional.get(), Pret.ID, idTarget);

		given(pretRepository.findById(idTarget)).willReturn(pretTargetOptional);
		given(pretRepository.saveAndFlush(any(Pret.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Pret pretUpdated = pretService.update(pretSource, idTarget);

		verify(pretRepository).findById(idTarget);
		verify(pretRepository).saveAndFlush(any(Pret.class));
		assertThat(pretUpdated.getId()).isEqualTo(idTarget);
		assertThat(pretUpdated).isEqualToIgnoringGivenFields(pretSource, Pret.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Pret(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Pret source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> pretService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Pret pret = new Pret();

		pretService.delete(pret);

		verify(pretRepository).delete(pret);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> pretService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Pret> pretOptional = Optional.of(new Pret());
		ReflectionTestUtils.setField(pretOptional.get(), Pret.ID, id);

		given(pretRepository.findById(id)).willReturn(pretOptional);

		pretService.deleteById(1);

		verify(pretRepository).deleteById(pretOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> pretService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(pretRepository.count()).willReturn(nbExpected);

		final long nbActual = pretService.count();

		verify(pretRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Pret> pretOptional = Optional.of(new Pret());
		ReflectionTestUtils.setField(pretOptional.get(), Pret.ID, id);

		given(pretRepository.findById(id)).willReturn(pretOptional);

		final Pret pretActual = pretService.findById(id);

		verify(pretRepository).findById(id);
		assertThat(pretActual).isEqualToComparingFieldByField(pretOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> pretService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Pret pret1 = new Pret();
		ReflectionTestUtils.setField(pret1, Pret.ID, 1);
		final Pret pret2 = new Pret();
		ReflectionTestUtils.setField(pret2, Pret.ID, 2);
		final Pret pret3 = new Pret();
		ReflectionTestUtils.setField(pret3, Pret.ID, 3);

		final List<Pret> pretsExpected = new ArrayList<>(3);
		pretsExpected.add(pret1);
		pretsExpected.add(pret2);
		pretsExpected.add(pret3);

		given(pretRepository.findAll()).willReturn(pretsExpected);

		final List<Pret> pretsActual = pretService.findAll();

		verify(pretRepository).findAll();
		assertThat(pretsActual).containsExactlyInAnyOrderElementsOf(pretsExpected);
	}

}
