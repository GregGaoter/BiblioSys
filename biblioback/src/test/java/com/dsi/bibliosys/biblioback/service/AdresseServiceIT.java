package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.repository.AdresseRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class AdresseServiceIT {

	private AdresseService adresseService;

	@Autowired
	private LieuService lieuService;
	@Autowired
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
		final Integer lieuId = 1;
		final Lieu lieu = lieuService.findById(lieuId);

		final Adresse adresseExcpected = new Adresse();
		adresseExcpected.setNumeroRue(4);
		adresseExcpected.setRue("Rue 4");
		adresseExcpected.setLieu(lieu);

		final Adresse adresseSaved = adresseService.save(adresseExcpected);
		final Adresse adresseFind = adresseService.findById(adresseSaved.getId());

		assertThat(adresseFind).extracting(Adresse::getNumeroRue, Adresse::getRue, adresse -> adresse.getLieu().getId())
				.containsExactly(adresseExcpected.getNumeroRue(), adresseExcpected.getRue(), lieuId);
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Lieu lieu = lieuService.findById(1);

		final Adresse adresseSource = new Adresse();
		adresseSource.setNumeroRue(1);
		adresseSource.setRue("Rue source");
		adresseSource.setLieu(lieu);

		final Integer idTarget = 1;

		final Adresse adresseUpdated = adresseService.update(adresseSource, idTarget);

		assertThat(adresseUpdated).extracting(Adresse::getId, Adresse::getNumeroRue, Adresse::getRue,
				adresse -> adresse.getLieu().getId()).containsExactly(idTarget, adresseSource.getNumeroRue(),
						adresseSource.getRue(), adresseSource.getLieu().getId());
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
		final Integer id = 1;
		final Adresse adresse = adresseService.findById(id);

		adresseService.delete(adresse);

		assertThrows(EntityNotFoundException.class, () -> adresseService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		adresseService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> adresseService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = adresseService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer adresseId = 1;

		final Adresse adresseActual = adresseService.findById(adresseId);

		assertThat(adresseActual).extracting(Adresse::getId, Adresse::getNumeroRue, Adresse::getRue,
				adresse -> adresse.getLieu().getId()).containsExactly(adresseId, 1, "Rue 1", 1);
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> adresseService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleAdresse1 = tuple(1, 1, "Rue 1", 1);
		final Tuple tupleAdresse2 = tuple(2, 2, "Rue 2", 2);
		final Tuple tupleAdresse3 = tuple(3, 3, "Rue 3", 3);

		final List<Adresse> adressesActual = adresseService.findAll();

		assertThat(adressesActual).extracting(Adresse::getId, Adresse::getNumeroRue, Adresse::getRue,
				adresse -> adresse.getLieu().getId()).containsOnly(tupleAdresse1, tupleAdresse2, tupleAdresse3);
	}

}
