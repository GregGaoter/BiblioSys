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
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Personnel;
import com.dsi.bibliosys.biblioback.repository.PersonnelRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class PersonnelServiceTest {

	private PersonnelService personnelService;

	@Mock
	private PersonnelRepository personnelRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		personnelService = new PersonnelService(personnelRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		personnelService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Personnel, Integer> repository = personnelService.getRepository();

		assertThat(repository).isEqualTo(personnelRepository);
	}

	@Test
	public void create() {
		final Personnel personnel = personnelService.create();

		assertThat(personnel).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Personnel personnel = new Personnel();

		given(personnelRepository.saveAndFlush(personnel)).willAnswer(invocation -> invocation.getArgument(0));

		final Personnel personnelSaved = personnelService.save(personnel);

		verify(personnelRepository).saveAndFlush(personnel);
		assertThat(personnelSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> personnelService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Personnel personnelSource = new Personnel();
		personnelSource.setAdresse(new Adresse());
		personnelSource.setBibliotheque(new Bibliotheque());
		personnelSource.setDateNaissance(LocalDateTime.of(2020, 11, 11, 15, 16));
		personnelSource.setIdentifiant(new Identifiant());
		personnelSource.setNom("nom");
		personnelSource.setPrenom("prenom");

		final Integer idTarget = 1;

		final Optional<Personnel> personnelTargetOptional = Optional.of(new Personnel());
		ReflectionTestUtils.setField(personnelTargetOptional.get(), Personnel.ID, idTarget);

		given(personnelRepository.findById(idTarget)).willReturn(personnelTargetOptional);
		given(personnelRepository.saveAndFlush(any(Personnel.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		final Personnel personnelUpdated = personnelService.update(personnelSource, idTarget);

		verify(personnelRepository).findById(idTarget);
		verify(personnelRepository).saveAndFlush(any(Personnel.class));
		assertThat(personnelUpdated.getId()).isEqualTo(idTarget);
		assertThat(personnelUpdated).isEqualToIgnoringGivenFields(personnelSource, Personnel.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Personnel(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Personnel source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> personnelService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Personnel personnel = new Personnel();

		personnelService.delete(personnel);

		verify(personnelRepository).delete(personnel);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> personnelService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Personnel> personnelOptional = Optional.of(new Personnel());
		ReflectionTestUtils.setField(personnelOptional.get(), Personnel.ID, id);

		given(personnelRepository.findById(id)).willReturn(personnelOptional);

		personnelService.deleteById(1);

		verify(personnelRepository).deleteById(personnelOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> personnelService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(personnelRepository.count()).willReturn(nbExpected);

		final long nbActual = personnelService.count();

		verify(personnelRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Personnel> personnelOptional = Optional.of(new Personnel());
		ReflectionTestUtils.setField(personnelOptional.get(), Personnel.ID, id);

		given(personnelRepository.findById(id)).willReturn(personnelOptional);

		final Personnel personnelActual = personnelService.findById(id);

		verify(personnelRepository).findById(id);
		assertThat(personnelActual).isEqualToComparingFieldByField(personnelOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> personnelService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Personnel personnel1 = new Personnel();
		ReflectionTestUtils.setField(personnel1, Personnel.ID, 1);
		final Personnel personnel2 = new Personnel();
		ReflectionTestUtils.setField(personnel2, Personnel.ID, 2);
		final Personnel personnel3 = new Personnel();
		ReflectionTestUtils.setField(personnel3, Personnel.ID, 3);

		final List<Personnel> personnelsExpected = new ArrayList<>(3);
		personnelsExpected.add(personnel1);
		personnelsExpected.add(personnel2);
		personnelsExpected.add(personnel3);

		given(personnelRepository.findAll()).willReturn(personnelsExpected);

		final List<Personnel> personnelsActual = personnelService.findAll();

		verify(personnelRepository).findAll();
		assertThat(personnelsActual).containsExactlyInAnyOrderElementsOf(personnelsExpected);
	}

}
