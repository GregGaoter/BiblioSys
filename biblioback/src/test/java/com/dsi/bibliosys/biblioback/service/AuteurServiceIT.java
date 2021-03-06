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
import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.repository.AuteurRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class AuteurServiceIT {

	private AuteurService auteurService;

	@Autowired
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
		final Auteur auteurExcpected = new Auteur();
		auteurExcpected.setPrenomNom("Prénom Nom");

		final Auteur auteurSaved = auteurService.save(auteurExcpected);
		final Auteur auteurFind = auteurService.findById(auteurSaved.getId());

		assertThat(auteurFind.getPrenomNom()).isEqualTo(auteurExcpected.getPrenomNom());
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Auteur auteurSource = new Auteur();
		auteurSource.setPrenomNom("Prénom Nom");

		final Integer idTarget = 1;

		final Auteur auteurUpdated = auteurService.update(auteurSource, idTarget);

		assertThat(auteurUpdated).extracting(Auteur::getId, Auteur::getPrenomNom).containsExactly(auteurUpdated.getId(),
				auteurSource.getPrenomNom());
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
		final Integer id = 1;
		final Auteur auteur = auteurService.findById(id);

		auteurService.delete(auteur);

		assertThrows(EntityNotFoundException.class, () -> auteurService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		auteurService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> auteurService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = auteurService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer auteurId = 1;

		final Auteur auteurActual = auteurService.findById(auteurId);

		assertThat(auteurActual).extracting(Auteur::getId, Auteur::getPrenomNom).containsExactly(auteurId, "Auteur 1");
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> auteurService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleAuteur1 = tuple(1, "Auteur 1");
		final Tuple tupleAuteur2 = tuple(2, "Auteur 2");
		final Tuple tupleAuteur3 = tuple(3, "Auteur 3");

		final List<Auteur> auteursActual = auteurService.findAll();

		assertThat(auteursActual).extracting(Auteur::getId, Auteur::getPrenomNom).containsOnly(tupleAuteur1,
				tupleAuteur2, tupleAuteur3);
	}

}
