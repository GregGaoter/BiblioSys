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
import com.dsi.bibliosys.biblioback.repository.IdentifiantRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class IdentifiantServiceTest {

	private IdentifiantService identifiantService;

	@Mock
	private IdentifiantRepository identifiantRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		identifiantService = new IdentifiantService(identifiantRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		identifiantService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Identifiant, Integer> repository = identifiantService.getRepository();

		assertThat(repository).isEqualTo(identifiantRepository);
	}

	@Test
	public void create() {
		final Identifiant identifiant = identifiantService.create();

		assertThat(identifiant).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Identifiant identifiant = new Identifiant();

		given(identifiantRepository.saveAndFlush(identifiant)).willAnswer(invocation -> invocation.getArgument(0));

		final Identifiant identifiantSaved = identifiantService.saveAndFlush(identifiant);

		verify(identifiantRepository).saveAndFlush(identifiant);
		assertThat(identifiantSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Identifiant identifiantSource = new Identifiant();
		identifiantSource.setEmail("email");
		identifiantSource.setIsActif(true);
		identifiantSource.setMotDePasse("motDePasse");

		final Integer idTarget = 1;

		final Optional<Identifiant> identifiantTargetOptional = Optional.of(new Identifiant());
		ReflectionTestUtils.setField(identifiantTargetOptional.get(), Identifiant.ID, idTarget);

		given(identifiantRepository.findById(idTarget)).willReturn(identifiantTargetOptional);
		given(identifiantRepository.saveAndFlush(any(Identifiant.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		final Identifiant identifiantUpdated = identifiantService.update(identifiantSource, idTarget);

		verify(identifiantRepository).findById(idTarget);
		verify(identifiantRepository).saveAndFlush(any(Identifiant.class));
		assertThat(identifiantUpdated.getId()).isEqualTo(idTarget);
		assertThat(identifiantUpdated).isEqualToIgnoringGivenFields(identifiantSource, Identifiant.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Identifiant(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Identifiant source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> identifiantService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Identifiant identifiant = new Identifiant();

		identifiantService.delete(identifiant);

		verify(identifiantRepository).delete(identifiant);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Identifiant> identifiantOptional = Optional.of(new Identifiant());
		ReflectionTestUtils.setField(identifiantOptional.get(), Identifiant.ID, id);

		given(identifiantRepository.findById(id)).willReturn(identifiantOptional);

		identifiantService.deleteById(1);

		verify(identifiantRepository).deleteById(identifiantOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(identifiantRepository.count()).willReturn(nbExpected);

		final long nbActual = identifiantService.count();

		verify(identifiantRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Identifiant> identifiantOptional = Optional.of(new Identifiant());
		ReflectionTestUtils.setField(identifiantOptional.get(), Identifiant.ID, id);

		given(identifiantRepository.findById(id)).willReturn(identifiantOptional);

		final Identifiant identifiantActual = identifiantService.findById(id);

		verify(identifiantRepository).findById(id);
		assertThat(identifiantActual).isEqualToComparingFieldByField(identifiantOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Identifiant identifiant1 = new Identifiant();
		ReflectionTestUtils.setField(identifiant1, Identifiant.ID, 1);
		final Identifiant identifiant2 = new Identifiant();
		ReflectionTestUtils.setField(identifiant2, Identifiant.ID, 2);
		final Identifiant identifiant3 = new Identifiant();
		ReflectionTestUtils.setField(identifiant3, Identifiant.ID, 3);

		final List<Identifiant> identifiantsExpected = new ArrayList<>(3);
		identifiantsExpected.add(identifiant1);
		identifiantsExpected.add(identifiant2);
		identifiantsExpected.add(identifiant3);

		given(identifiantRepository.findAll()).willReturn(identifiantsExpected);

		final List<Identifiant> identifiantsActual = identifiantService.findAll();

		verify(identifiantRepository).findAll();
		assertThat(identifiantsActual).containsExactlyInAnyOrderElementsOf(identifiantsExpected);
	}

}
