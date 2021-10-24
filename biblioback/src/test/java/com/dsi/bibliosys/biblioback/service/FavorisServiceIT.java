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
import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.FavorisRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class FavorisServiceIT {

	private FavorisService favorisService;

	@Autowired
	private FavorisRepository favorisRepository;
	@Autowired
	private LivreService livreService;
	@Autowired
	private UsagerService usagerService;

	@BeforeEach
	public void setUpBeforeEach() {
		favorisService = new FavorisService(favorisRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		favorisService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Favoris, Integer> repository = favorisService.getRepository();

		assertThat(repository).isEqualTo(favorisRepository);
	}

	@Test
	public void create() {
		final Favoris favoris = favorisService.create();

		assertThat(favoris).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Integer livreId = 1;
		final Livre livre = livreService.findById(livreId);
		final Integer usagerId = 1;
		final Usager usager = usagerService.findById(usagerId);

		final Favoris favorisExcpected = new Favoris();
		favorisExcpected.setLivre(livre);
		favorisExcpected.setUsager(usager);

		final Favoris favorisSaved = favorisService.saveAndFlush(favorisExcpected);
		final Favoris favorisFind = favorisService.findById(favorisSaved.getId());

		assertThat(favorisFind)
				.extracting(favoris -> favoris.getLivre().getId(), favoris -> favoris.getUsager().getId())
				.containsExactly(livreId, usagerId);
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.saveAndFlush(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Integer livreId = 2;
		final Livre livre = livreService.findById(livreId);
		final Integer usagerId = 3;
		final Usager usager = usagerService.findById(usagerId);

		final Favoris favorisSource = new Favoris();
		favorisSource.setLivre(livre);
		favorisSource.setUsager(usager);

		final Integer idTarget = 1;

		final Favoris favorisUpdated = favorisService.update(favorisSource, idTarget);

		assertThat(favorisUpdated)
				.extracting(favoris -> favoris.getLivre().getId(), favoris -> favoris.getUsager().getId())
				.containsExactly(livreId, usagerId);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Favoris(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Favoris source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final Favoris favoris = favorisService.findById(id);

		favorisService.delete(favoris);

		assertThrows(EntityNotFoundException.class, () -> favorisService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		favorisService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> favorisService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = favorisService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer favorisId = 1;

		final Favoris favorisActual = favorisService.findById(favorisId);

		assertThat(favorisActual)
				.extracting(favoris -> favoris.getLivre().getId(), favoris -> favoris.getUsager().getId())
				.containsExactly(1, 1);
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleFavoris1 = tuple(1, 1);
		final Tuple tupleFavoris2 = tuple(2, 2);
		final Tuple tupleFavoris3 = tuple(3, 3);

		final List<Favoris> favorissActual = favorisService.findAll();

		assertThat(favorissActual)
				.extracting(favoris -> favoris.getLivre().getId(), favoris -> favoris.getUsager().getId())
				.containsOnly(tupleFavoris1, tupleFavoris2, tupleFavoris3);
	}

}
