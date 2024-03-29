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
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.repository.ProfilRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class ProfilServiceTest {

	private ProfilService profilService;

	@Mock
	private ProfilRepository profilRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		profilService = new ProfilService(profilRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		profilService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Profil, Integer> repository = profilService.getRepository();

		assertThat(repository).isEqualTo(profilRepository);
	}

	@Test
	public void create() {
		final Profil profil = profilService.create();

		assertThat(profil).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Profil profil = new Profil();

		given(profilRepository.saveAndFlush(profil)).willAnswer(invocation -> invocation.getArgument(0));

		final Profil profilSaved = profilService.saveAndFlush(profil);

		verify(profilRepository).saveAndFlush(profil);
		assertThat(profilSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> profilService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Profil profilSource = new Profil();
		profilSource.setIdentifiant(new Identifiant());
		profilSource.setRole(new Role());

		final Integer idTarget = 1;

		final Optional<Profil> profilTargetOptional = Optional.of(new Profil());
		ReflectionTestUtils.setField(profilTargetOptional.get(), Profil.ID, idTarget);

		given(profilRepository.findById(idTarget)).willReturn(profilTargetOptional);
		given(profilRepository.saveAndFlush(any(Profil.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Profil profilUpdated = profilService.update(profilSource, idTarget);

		verify(profilRepository).findById(idTarget);
		verify(profilRepository).saveAndFlush(any(Profil.class));
		assertThat(profilUpdated.getId()).isEqualTo(idTarget);
		assertThat(profilUpdated).isEqualToIgnoringGivenFields(profilSource, Profil.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Profil(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Profil source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> profilService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Profil profil = new Profil();

		profilService.delete(profil);

		verify(profilRepository).delete(profil);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> profilService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Profil> profilOptional = Optional.of(new Profil());
		ReflectionTestUtils.setField(profilOptional.get(), Profil.ID, id);

		given(profilRepository.findById(id)).willReturn(profilOptional);

		profilService.deleteById(1);

		verify(profilRepository).deleteById(profilOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> profilService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(profilRepository.count()).willReturn(nbExpected);

		final long nbActual = profilService.count();

		verify(profilRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Profil> profilOptional = Optional.of(new Profil());
		ReflectionTestUtils.setField(profilOptional.get(), Profil.ID, id);

		given(profilRepository.findById(id)).willReturn(profilOptional);

		final Profil profilActual = profilService.findById(id);

		verify(profilRepository).findById(id);
		assertThat(profilActual).isEqualToComparingFieldByField(profilOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> profilService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Profil profil1 = new Profil();
		ReflectionTestUtils.setField(profil1, Profil.ID, 1);
		final Profil profil2 = new Profil();
		ReflectionTestUtils.setField(profil2, Profil.ID, 2);
		final Profil profil3 = new Profil();
		ReflectionTestUtils.setField(profil3, Profil.ID, 3);

		final List<Profil> profilsExpected = new ArrayList<>(3);
		profilsExpected.add(profil1);
		profilsExpected.add(profil2);
		profilsExpected.add(profil3);

		given(profilRepository.findAll()).willReturn(profilsExpected);

		final List<Profil> profilsActual = profilService.findAll();

		verify(profilRepository).findAll();
		assertThat(profilsActual).containsExactlyInAnyOrderElementsOf(profilsExpected);
	}

}
