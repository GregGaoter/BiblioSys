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
import org.junit.jupiter.api.Disabled;
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
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.repository.IdentifiantRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class IdentifiantServiceIT {

	private IdentifiantService identifiantService;

	@Autowired
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
		final Identifiant identifiantExcpected = new Identifiant();
		identifiantExcpected.setEmail("email4@domaine4.fr");
		identifiantExcpected.setMotDePasse("mdp4");
		identifiantExcpected.setIsActif(false);

		final Identifiant identifiantSaved = identifiantService.save(identifiantExcpected);
		final Identifiant identifiantFind = identifiantService.findById(identifiantSaved.getId());

		assertThat(identifiantFind)
				.extracting(Identifiant::getEmail, Identifiant::getMotDePasse, Identifiant::getIsActif)
				.containsExactly(identifiantExcpected.getEmail(), identifiantExcpected.getMotDePasse(),
						identifiantExcpected.getIsActif());
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Identifiant identifiantSource = new Identifiant();
		identifiantSource.setEmail("email1@domaine1.fr");
		identifiantSource.setMotDePasse("mdp1");
		identifiantSource.setIsActif(false);

		final Integer idTarget = 1;

		final Identifiant identifiantUpdated = identifiantService.update(identifiantSource, idTarget);

		assertThat(identifiantUpdated).extracting(Identifiant::getId, Identifiant::getIsActif)
				.containsExactly(identifiantUpdated.getId(), identifiantSource.getIsActif());
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

	@Disabled("Fix this : do not throw exception !")
	@Test
	public void delete() {
		final Integer id = 1;
		final Identifiant identifiant = identifiantService.findById(id);

		identifiantService.delete(identifiant);

		assertThrows(EntityNotFoundException.class, () -> identifiantService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Disabled("Fix this : do not throw exception !")
	@Test
	public void deleteById() {
		final Integer id = 1;

		identifiantService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> identifiantService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = identifiantService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer identifiantId = 1;

		final Identifiant identifiantActual = identifiantService.findById(identifiantId);

		assertThat(identifiantActual).extracting(Identifiant::getId, Identifiant::getEmail, Identifiant::getMotDePasse,
				Identifiant::getIsActif).containsExactly(identifiantId, "email1@domaine1.fr", "mdp1", true);
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> identifiantService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleIdentifiant1 = tuple(1, "email1@domaine1.fr", "mdp1", true);
		final Tuple tupleIdentifiant2 = tuple(2, "email2@domaine2.fr", "mdp2", false);
		final Tuple tupleIdentifiant3 = tuple(3, "email3@domaine3.fr", "mdp3", true);

		final List<Identifiant> identifiantsActual = identifiantService.findAll();

		assertThat(identifiantsActual).extracting(Identifiant::getId, Identifiant::getEmail, Identifiant::getMotDePasse,
				Identifiant::getIsActif).containsOnly(tupleIdentifiant1, tupleIdentifiant2, tupleIdentifiant3);
	}

}
