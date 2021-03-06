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
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.repository.EditeurRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class EditeurServiceTest {

	private EditeurService editeurService;

	@Mock
	private EditeurRepository editeurRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		editeurService = new EditeurService(editeurRepository, null);
	}

	@AfterEach
	public void unDefAfterEach() {
		editeurService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Editeur, Integer> repository = editeurService.getRepository();

		assertThat(repository).isEqualTo(editeurRepository);
	}

	@Test
	public void create() {
		final Editeur editeur = editeurService.create();

		assertThat(editeur).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Editeur editeur = new Editeur();

		given(editeurRepository.saveAndFlush(editeur)).willAnswer(invocation -> invocation.getArgument(0));

		final Editeur editeurSaved = editeurService.save(editeur);

		verify(editeurRepository).saveAndFlush(editeur);
		assertThat(editeurSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Editeur editeurSource = new Editeur();
		editeurSource.setNom("nom");

		final Integer idTarget = 1;

		final Optional<Editeur> editeurTargetOptional = Optional.of(new Editeur());
		ReflectionTestUtils.setField(editeurTargetOptional.get(), Editeur.ID, idTarget);

		given(editeurRepository.findById(idTarget)).willReturn(editeurTargetOptional);
		given(editeurRepository.saveAndFlush(any(Editeur.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Editeur editeurUpdated = editeurService.update(editeurSource, idTarget);

		verify(editeurRepository).findById(idTarget);
		verify(editeurRepository).saveAndFlush(any(Editeur.class));
		assertThat(editeurUpdated.getId()).isEqualTo(idTarget);
		assertThat(editeurUpdated).isEqualToIgnoringGivenFields(editeurSource, Editeur.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Editeur(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Editeur source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Editeur editeur = new Editeur();

		editeurService.delete(editeur);

		verify(editeurRepository).delete(editeur);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Editeur> editeurOptional = Optional.of(new Editeur());
		ReflectionTestUtils.setField(editeurOptional.get(), Editeur.ID, id);

		given(editeurRepository.findById(id)).willReturn(editeurOptional);

		editeurService.deleteById(1);

		verify(editeurRepository).deleteById(editeurOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(editeurRepository.count()).willReturn(nbExpected);

		final long nbActual = editeurService.count();

		verify(editeurRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Editeur> editeurOptional = Optional.of(new Editeur());
		ReflectionTestUtils.setField(editeurOptional.get(), Editeur.ID, id);

		given(editeurRepository.findById(id)).willReturn(editeurOptional);

		final Editeur editeurActual = editeurService.findById(id);

		verify(editeurRepository).findById(id);
		assertThat(editeurActual).isEqualToComparingFieldByField(editeurOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Editeur editeur1 = new Editeur();
		ReflectionTestUtils.setField(editeur1, Editeur.ID, 1);
		final Editeur editeur2 = new Editeur();
		ReflectionTestUtils.setField(editeur2, Editeur.ID, 2);
		final Editeur editeur3 = new Editeur();
		ReflectionTestUtils.setField(editeur3, Editeur.ID, 3);

		final List<Editeur> editeursExpected = new ArrayList<>(3);
		editeursExpected.add(editeur1);
		editeursExpected.add(editeur2);
		editeursExpected.add(editeur3);

		given(editeurRepository.findAll()).willReturn(editeursExpected);

		final List<Editeur> editeursActual = editeurService.findAll();

		verify(editeurRepository).findAll();
		assertThat(editeursActual).containsExactlyInAnyOrderElementsOf(editeursExpected);
	}

}
