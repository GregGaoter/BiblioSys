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
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.repository.EcritureLivreRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class EcritureLivreServiceIT {

	private EcritureLivreService ecritureLivreService;

	@Autowired
	private EcritureLivreRepository ecritureLivreRepository;
	@Autowired
	private LivreService livreService;
	@Autowired
	private AuteurService auteurService;

	@BeforeEach
	public void setUpBeforeEach() {
		ecritureLivreService = new EcritureLivreService(ecritureLivreRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		ecritureLivreService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<EcritureLivre, Integer> repository = ecritureLivreService.getRepository();

		assertThat(repository).isEqualTo(ecritureLivreRepository);
	}

	@Test
	public void create() {
		final EcritureLivre ecritureLivre = ecritureLivreService.create();

		assertThat(ecritureLivre).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Integer livreId = 1;
		final Livre livre = livreService.findById(livreId);
		final Integer auteurId = 1;
		final Auteur auteur = auteurService.findById(auteurId);

		final EcritureLivre ecritureLivreExcpected = new EcritureLivre();
		ecritureLivreExcpected.setLivre(livre);
		ecritureLivreExcpected.setAuteur(auteur);

		final EcritureLivre ecritureLivreSaved = ecritureLivreService.saveAndFlush(ecritureLivreExcpected);
		final EcritureLivre ecritureLivreFind = ecritureLivreService.findById(ecritureLivreSaved.getId());

		assertThat(ecritureLivreFind).extracting(ecritureLivre -> ecritureLivre.getLivre().getId(),
				ecritureLivre -> ecritureLivre.getAuteur().getId()).containsExactly(livreId, auteurId);
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Integer livreId = 2;
		final Livre livre = livreService.findById(livreId);
		final Integer auteurId = 3;
		final Auteur auteur = auteurService.findById(auteurId);

		final EcritureLivre ecritureLivreSource = new EcritureLivre();
		ecritureLivreSource.setLivre(livre);
		ecritureLivreSource.setAuteur(auteur);

		final Integer idTarget = 1;

		final EcritureLivre ecritureLivreUpdated = ecritureLivreService.update(ecritureLivreSource, idTarget);

		assertThat(ecritureLivreUpdated).extracting(ecritureLivre -> ecritureLivre.getLivre().getId(),
				ecritureLivre -> ecritureLivre.getAuteur().getId()).containsExactly(livreId, auteurId);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new EcritureLivre(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(EcritureLivre source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> ecritureLivreService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final EcritureLivre ecritureLivre = ecritureLivreService.findById(id);

		ecritureLivreService.delete(ecritureLivre);

		assertThrows(EntityNotFoundException.class, () -> ecritureLivreService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		ecritureLivreService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> ecritureLivreService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> ecritureLivreService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = ecritureLivreService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer ecritureLivreId = 1;

		final EcritureLivre ecritureLivreActual = ecritureLivreService.findById(ecritureLivreId);

		assertThat(ecritureLivreActual).extracting(ecritureLivre -> ecritureLivre.getLivre().getId(),
				ecritureLivre -> ecritureLivre.getAuteur().getId()).containsExactly(1, 1);
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleEcritureLivre1 = tuple(1, 1);
		final Tuple tupleEcritureLivre2 = tuple(2, 2);
		final Tuple tupleEcritureLivre3 = tuple(3, 3);

		final List<EcritureLivre> ecritureLivresActual = ecritureLivreService.findAll();

		assertThat(ecritureLivresActual)
				.extracting(ecritureLivre -> ecritureLivre.getLivre().getId(),
						ecritureLivre -> ecritureLivre.getAuteur().getId())
				.containsOnly(tupleEcritureLivre1, tupleEcritureLivre2, tupleEcritureLivre3);
	}

}
