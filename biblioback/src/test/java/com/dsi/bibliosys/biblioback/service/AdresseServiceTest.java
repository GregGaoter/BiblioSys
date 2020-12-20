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
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.repository.AdresseRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class AdresseServiceTest {

	private AdresseService adresseService;

	@Mock
	private AdresseRepository adresseRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		adresseService = new AdresseService(adresseRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		adresseService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Adresse, Integer> repository = adresseService.getRepository();

		assertThat(repository).isEqualTo(adresseRepository);
	}

	@Test
	public void create() {
		final Adresse adresse = adresseService.create();

		assertThat(adresse).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Adresse adresse = new Adresse();

		given(adresseRepository.saveAndFlush(adresse)).willAnswer(invocation -> invocation.getArgument(0));

		final Adresse adresseSaved = adresseService.save(adresse);

		verify(adresseRepository).saveAndFlush(adresse);
		assertThat(adresseSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Adresse adresseSource = new Adresse();
		adresseSource.setNumeroRue(1);
		adresseSource.setRue("Rue source");
		adresseSource.setLieu(new Lieu());

		final Integer idTarget = 1;

		final Optional<Adresse> adresseTargetOptional = Optional.of(new Adresse());
		ReflectionTestUtils.setField(adresseTargetOptional.get(), Adresse.ID, idTarget);

		given(adresseRepository.findById(idTarget)).willReturn(adresseTargetOptional);
		given(adresseRepository.saveAndFlush(any(Adresse.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Adresse adresseUpdated = adresseService.update(adresseSource, idTarget);

		verify(adresseRepository).findById(idTarget);
		verify(adresseRepository).saveAndFlush(any(Adresse.class));
		assertThat(adresseUpdated.getId()).isEqualTo(idTarget);
		assertThat(adresseUpdated).isEqualToIgnoringGivenFields(adresseSource, Adresse.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Adresse(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Adresse source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Adresse adresse = new Adresse();

		adresseService.delete(adresse);

		verify(adresseRepository).delete(adresse);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Adresse> adresseOptional = Optional.of(new Adresse());
		ReflectionTestUtils.setField(adresseOptional.get(), Adresse.ID, id);

		given(adresseRepository.findById(id)).willReturn(adresseOptional);

		adresseService.deleteById(1);

		verify(adresseRepository).deleteById(adresseOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(adresseRepository.count()).willReturn(nbExpected);

		final long nbActual = adresseService.count();

		verify(adresseRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Adresse> adresseOptional = Optional.of(new Adresse());
		ReflectionTestUtils.setField(adresseOptional.get(), Adresse.ID, id);

		given(adresseRepository.findById(id)).willReturn(adresseOptional);

		final Adresse adresseActual = adresseService.findById(id);

		verify(adresseRepository).findById(id);
		assertThat(adresseActual).isEqualToComparingFieldByField(adresseOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Adresse adresse1 = new Adresse();
		ReflectionTestUtils.setField(adresse1, Adresse.ID, 1);
		final Adresse adresse2 = new Adresse();
		ReflectionTestUtils.setField(adresse2, Adresse.ID, 2);
		final Adresse adresse3 = new Adresse();
		ReflectionTestUtils.setField(adresse3, Adresse.ID, 3);

		final List<Adresse> adressesExpected = new ArrayList<>(3);
		adressesExpected.add(adresse1);
		adressesExpected.add(adresse2);
		adressesExpected.add(adresse3);

		given(adresseRepository.findAll()).willReturn(adressesExpected);

		final List<Adresse> adressesActual = adresseService.findAll();

		verify(adresseRepository).findAll();
		assertThat(adressesActual).containsExactlyInAnyOrderElementsOf(adressesExpected);
	}

}
