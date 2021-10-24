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
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.repository.BibliothequeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class BibliothequeServiceIT {

	private BibliothequeService bibliothequeService;

	@Autowired
	private AdresseService adresseService;
	@Autowired
	private BibliothequeRepository bibliothequeRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		bibliothequeService = new BibliothequeService(bibliothequeRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		bibliothequeService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Bibliotheque, Integer> repository = bibliothequeService.getRepository();

		assertThat(repository).isEqualTo(bibliothequeRepository);
	}

	@Test
	public void create() {
		final Bibliotheque bibliotheque = bibliothequeService.create();

		assertThat(bibliotheque).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Integer adresseId = 1;
		final Adresse adresse = adresseService.findById(adresseId);

		final Bibliotheque bibliothequeExcpected = new Bibliotheque();
		bibliothequeExcpected.setNom("Bibliothèque 1");
		bibliothequeExcpected.setAdresse(adresse);

		final Bibliotheque bibliothequeSaved = bibliothequeService.saveAndFlush(bibliothequeExcpected);
		final Bibliotheque bibliothequeFind = bibliothequeService.findById(bibliothequeSaved.getId());

		assertThat(bibliothequeFind).extracting(Bibliotheque::getNom, bibliotheque -> bibliotheque.getAdresse().getId())
				.containsExactly(bibliothequeExcpected.getNom(), adresseId);
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Adresse adresse = adresseService.findById(1);

		final Bibliotheque bibliothequeSource = new Bibliotheque();
		bibliothequeSource.setNom("Bibliothèque source");
		bibliothequeSource.setAdresse(adresse);

		final Integer idTarget = 1;

		final Bibliotheque bibliothequeUpdated = bibliothequeService.update(bibliothequeSource, idTarget);

		assertThat(bibliothequeUpdated)
				.extracting(Bibliotheque::getNom, bibliotheque -> bibliotheque.getAdresse().getId())
				.containsExactly(bibliothequeSource.getNom(), bibliothequeSource.getAdresse().getId());
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Bibliotheque(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Bibliotheque source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> bibliothequeService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final Bibliotheque bibliotheque = bibliothequeService.findById(id);

		bibliothequeService.delete(bibliotheque);

		assertThrows(EntityNotFoundException.class, () -> bibliothequeService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		bibliothequeService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> bibliothequeService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> bibliothequeService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = bibliothequeService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer bibliothequeId = 1;

		final Bibliotheque bibliothequeActual = bibliothequeService.findById(bibliothequeId);

		assertThat(bibliothequeActual)
				.extracting(Bibliotheque::getId, Bibliotheque::getNom,
						bibliotheque -> bibliotheque.getAdresse().getId())
				.containsExactly(bibliothequeId, "Bibliothèque 1", 1);
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleBibliotheque1 = tuple(1, "Bibliothèque 1", 1);
		final Tuple tupleBibliotheque2 = tuple(2, "Bibliothèque 2", 2);
		final Tuple tupleBibliotheque3 = tuple(3, "Bibliothèque 3", 3);

		final List<Bibliotheque> bibliothequesActual = bibliothequeService.findAll();

		assertThat(bibliothequesActual)
				.extracting(Bibliotheque::getId, Bibliotheque::getNom,
						bibliotheque -> bibliotheque.getAdresse().getId())
				.containsOnly(tupleBibliotheque1, tupleBibliotheque2, tupleBibliotheque3);
	}

}
