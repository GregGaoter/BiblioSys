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
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.UsagerRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class UsagerServiceTest {

	private UsagerService usagerService;

	@Mock
	private UsagerRepository usagerRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		usagerService = new UsagerService(usagerRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		usagerService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Usager, Integer> repository = usagerService.getRepository();

		assertThat(repository).isEqualTo(usagerRepository);
	}

	@Test
	public void create() {
		final Usager usager = usagerService.create();

		assertThat(usager).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Usager usager = new Usager();

		given(usagerRepository.saveAndFlush(usager)).willAnswer(invocation -> invocation.getArgument(0));

		final Usager usagerSaved = usagerService.saveAndFlush(usager);

		verify(usagerRepository).saveAndFlush(usager);
		assertThat(usagerSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> usagerService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Usager usagerSource = new Usager();
		usagerSource.setAdresse(new Adresse());
		usagerSource.setDateNaissance(LocalDateTime.of(2020, 11, 11, 15, 24));
		usagerSource.setIdentifiant(new Identifiant());
		usagerSource.setNom("nom");
		usagerSource.setPrenom("prenom");

		final Integer idTarget = 1;

		final Optional<Usager> usagerTargetOptional = Optional.of(new Usager());
		ReflectionTestUtils.setField(usagerTargetOptional.get(), Usager.ID, idTarget);

		given(usagerRepository.findById(idTarget)).willReturn(usagerTargetOptional);
		given(usagerRepository.saveAndFlush(any(Usager.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Usager usagerUpdated = usagerService.update(usagerSource, idTarget);

		verify(usagerRepository).findById(idTarget);
		verify(usagerRepository).saveAndFlush(any(Usager.class));
		assertThat(usagerUpdated.getId()).isEqualTo(idTarget);
		assertThat(usagerUpdated).isEqualToIgnoringGivenFields(usagerSource, Usager.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Usager(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Usager source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> usagerService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Usager usager = new Usager();

		usagerService.delete(usager);

		verify(usagerRepository).delete(usager);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> usagerService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Usager> usagerOptional = Optional.of(new Usager());
		ReflectionTestUtils.setField(usagerOptional.get(), Usager.ID, id);

		given(usagerRepository.findById(id)).willReturn(usagerOptional);

		usagerService.deleteById(1);

		verify(usagerRepository).deleteById(usagerOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> usagerService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(usagerRepository.count()).willReturn(nbExpected);

		final long nbActual = usagerService.count();

		verify(usagerRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Usager> usagerOptional = Optional.of(new Usager());
		ReflectionTestUtils.setField(usagerOptional.get(), Usager.ID, id);

		given(usagerRepository.findById(id)).willReturn(usagerOptional);

		final Usager usagerActual = usagerService.findById(id);

		verify(usagerRepository).findById(id);
		assertThat(usagerActual).isEqualToComparingFieldByField(usagerOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> usagerService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Usager usager1 = new Usager();
		ReflectionTestUtils.setField(usager1, Usager.ID, 1);
		final Usager usager2 = new Usager();
		ReflectionTestUtils.setField(usager2, Usager.ID, 2);
		final Usager usager3 = new Usager();
		ReflectionTestUtils.setField(usager3, Usager.ID, 3);

		final List<Usager> usagersExpected = new ArrayList<>(3);
		usagersExpected.add(usager1);
		usagersExpected.add(usager2);
		usagersExpected.add(usager3);

		given(usagerRepository.findAll()).willReturn(usagersExpected);

		final List<Usager> usagersActual = usagerService.findAll();

		verify(usagerRepository).findAll();
		assertThat(usagersActual).containsExactlyInAnyOrderElementsOf(usagersExpected);
	}

}
