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
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.repository.LieuRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class LieuServiceTest {

	private LieuService lieuService;

	@Mock
	private LieuRepository lieuRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		lieuService = new LieuService(lieuRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		lieuService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Lieu, Integer> repository = lieuService.getRepository();

		assertThat(repository).isEqualTo(lieuRepository);
	}

	@Test
	public void create() {
		final Lieu lieu = lieuService.create();

		assertThat(lieu).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Lieu lieu = new Lieu();

		given(lieuRepository.saveAndFlush(lieu)).willAnswer(invocation -> invocation.getArgument(0));

		final Lieu lieuSaved = lieuService.save(lieu);

		verify(lieuRepository).saveAndFlush(lieu);
		assertThat(lieuSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> lieuService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Lieu lieuSource = new Lieu();
		lieuSource.setCodePostal("codePostal");
		lieuSource.setDepartement("departement");
		lieuSource.setRegion("region");
		lieuSource.setVille("ville");

		final Integer idTarget = 1;

		final Optional<Lieu> lieuTargetOptional = Optional.of(new Lieu());
		ReflectionTestUtils.setField(lieuTargetOptional.get(), Lieu.ID, idTarget);

		given(lieuRepository.findById(idTarget)).willReturn(lieuTargetOptional);
		given(lieuRepository.saveAndFlush(any(Lieu.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Lieu lieuUpdated = lieuService.update(lieuSource, idTarget);

		verify(lieuRepository).findById(idTarget);
		verify(lieuRepository).saveAndFlush(any(Lieu.class));
		assertThat(lieuUpdated.getId()).isEqualTo(idTarget);
		assertThat(lieuUpdated).isEqualToIgnoringGivenFields(lieuSource, Lieu.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Lieu(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Lieu source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> lieuService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Lieu lieu = new Lieu();

		lieuService.delete(lieu);

		verify(lieuRepository).delete(lieu);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> lieuService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Lieu> lieuOptional = Optional.of(new Lieu());
		ReflectionTestUtils.setField(lieuOptional.get(), Lieu.ID, id);

		given(lieuRepository.findById(id)).willReturn(lieuOptional);

		lieuService.deleteById(1);

		verify(lieuRepository).deleteById(lieuOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> lieuService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(lieuRepository.count()).willReturn(nbExpected);

		final long nbActual = lieuService.count();

		verify(lieuRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Lieu> lieuOptional = Optional.of(new Lieu());
		ReflectionTestUtils.setField(lieuOptional.get(), Lieu.ID, id);

		given(lieuRepository.findById(id)).willReturn(lieuOptional);

		final Lieu lieuActual = lieuService.findById(id);

		verify(lieuRepository).findById(id);
		assertThat(lieuActual).isEqualToComparingFieldByField(lieuOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> lieuService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Lieu lieu1 = new Lieu();
		ReflectionTestUtils.setField(lieu1, Lieu.ID, 1);
		final Lieu lieu2 = new Lieu();
		ReflectionTestUtils.setField(lieu2, Lieu.ID, 2);
		final Lieu lieu3 = new Lieu();
		ReflectionTestUtils.setField(lieu3, Lieu.ID, 3);

		final List<Lieu> lieusExpected = new ArrayList<>(3);
		lieusExpected.add(lieu1);
		lieusExpected.add(lieu2);
		lieusExpected.add(lieu3);

		given(lieuRepository.findAll()).willReturn(lieusExpected);

		final List<Lieu> lieusActual = lieuService.findAll();

		verify(lieuRepository).findAll();
		assertThat(lieusActual).containsExactlyInAnyOrderElementsOf(lieusExpected);
	}

}
