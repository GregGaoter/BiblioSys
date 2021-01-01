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
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.repository.CollectionRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class CollectionServiceIT {

	private CollectionService collectionService;

	@Autowired
	private CollectionRepository collectionRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		collectionService = new CollectionService(collectionRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		collectionService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Collection, Integer> repository = collectionService.getRepository();

		assertThat(repository).isEqualTo(collectionRepository);
	}

	@Test
	public void create() {
		final Collection collection = collectionService.create();

		assertThat(collection).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Collection collectionExcpected = new Collection();
		collectionExcpected.setNom("Nom");

		final Collection collectionSaved = collectionService.save(collectionExcpected);
		final Collection collectionFind = collectionService.findById(collectionSaved.getId());

		assertThat(collectionFind.getNom()).isEqualTo(collectionExcpected.getNom());
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Collection collectionSource = new Collection();
		collectionSource.setNom("Nom");

		final Integer idTarget = 1;

		final Collection collectionUpdated = collectionService.update(collectionSource, idTarget);

		assertThat(collectionUpdated).extracting(Collection::getId, Collection::getNom)
				.containsExactly(collectionUpdated.getId(), collectionSource.getNom());
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Collection(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Collection source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class,
				() -> collectionService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final Collection collection = collectionService.findById(id);

		collectionService.delete(collection);

		assertThrows(EntityNotFoundException.class, () -> collectionService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		collectionService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> collectionService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = collectionService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer collectionId = 1;

		final Collection collectionActual = collectionService.findById(collectionId);

		assertThat(collectionActual).extracting(Collection::getId, Collection::getNom).containsExactly(collectionId,
				"Collection 1");
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleCollection1 = tuple(1, "Collection 1");
		final Tuple tupleCollection2 = tuple(2, "Collection 2");
		final Tuple tupleCollection3 = tuple(3, "Collection 3");

		final List<Collection> collectionsActual = collectionService.findAll();

		assertThat(collectionsActual).extracting(Collection::getId, Collection::getNom).containsOnly(tupleCollection1,
				tupleCollection2, tupleCollection3);
	}

}
