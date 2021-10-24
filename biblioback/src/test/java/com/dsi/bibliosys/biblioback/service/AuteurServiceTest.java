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
import com.dsi.bibliosys.biblioback.repository.AuteurRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class AuteurServiceTest {

	private AuteurService auteurService;

	@Mock
	private AuteurRepository auteurRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		auteurService = new AuteurService(auteurRepository, null);
	}

	@AfterEach
	public void unDefAfterEach() {
		auteurService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Auteur, Integer> repository = auteurService.getRepository();

		assertThat(repository).isEqualTo(auteurRepository);
	}

	@Test
	public void create() {
		final Auteur auteur = auteurService.create();

		assertThat(auteur).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Auteur auteur = new Auteur();

		given(auteurRepository.saveAndFlush(auteur)).willAnswer(invocation -> invocation.getArgument(0));

		final Auteur auteurSaved = auteurService.saveAndFlush(auteur);

		verify(auteurRepository).saveAndFlush(auteur);
		assertThat(auteurSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Auteur auteurSource = new Auteur();
		auteurSource.setPrenomNom("prenomNom");

		final Integer idTarget = 1;

		final Optional<Auteur> auteurTargetOptional = Optional.of(new Auteur());
		ReflectionTestUtils.setField(auteurTargetOptional.get(), Auteur.ID, idTarget);

		given(auteurRepository.findById(idTarget)).willReturn(auteurTargetOptional);
		given(auteurRepository.saveAndFlush(any(Auteur.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Auteur auteurUpdated = auteurService.update(auteurSource, idTarget);

		verify(auteurRepository).findById(idTarget);
		verify(auteurRepository).saveAndFlush(any(Auteur.class));
		assertThat(auteurUpdated.getId()).isEqualTo(idTarget);
		assertThat(auteurUpdated).isEqualToIgnoringGivenFields(auteurSource, Auteur.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Auteur(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Auteur source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Auteur auteur = new Auteur();

		auteurService.delete(auteur);

		verify(auteurRepository).delete(auteur);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Auteur> auteurOptional = Optional.of(new Auteur());
		ReflectionTestUtils.setField(auteurOptional.get(), Auteur.ID, id);

		given(auteurRepository.findById(id)).willReturn(auteurOptional);

		auteurService.deleteById(1);

		verify(auteurRepository).deleteById(auteurOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(auteurRepository.count()).willReturn(nbExpected);

		final long nbActual = auteurService.count();

		verify(auteurRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Auteur> auteurOptional = Optional.of(new Auteur());
		ReflectionTestUtils.setField(auteurOptional.get(), Auteur.ID, id);

		given(auteurRepository.findById(id)).willReturn(auteurOptional);

		final Auteur auteurActual = auteurService.findById(id);

		verify(auteurRepository).findById(id);
		assertThat(auteurActual).isEqualToComparingFieldByField(auteurOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Auteur auteur1 = new Auteur();
		ReflectionTestUtils.setField(auteur1, Auteur.ID, 1);
		final Auteur auteur2 = new Auteur();
		ReflectionTestUtils.setField(auteur2, Auteur.ID, 2);
		final Auteur auteur3 = new Auteur();
		ReflectionTestUtils.setField(auteur3, Auteur.ID, 3);

		final List<Auteur> auteursExpected = new ArrayList<>(3);
		auteursExpected.add(auteur1);
		auteursExpected.add(auteur2);
		auteursExpected.add(auteur3);

		given(auteurRepository.findAll()).willReturn(auteursExpected);

		final List<Auteur> auteursActual = auteurService.findAll();

		verify(auteurRepository).findAll();
		assertThat(auteursActual).containsExactlyInAnyOrderElementsOf(auteursExpected);
	}
}
