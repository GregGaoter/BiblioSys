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
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.repository.CollectionRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class CollectionServiceTest {

	private CollectionService collectionService;

	@Mock
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
		final Collection collection = new Collection();

		given(collectionRepository.saveAndFlush(collection)).willAnswer(invocation -> invocation.getArgument(0));

		final Collection collectionSaved = collectionService.save(collection);

		verify(collectionRepository).saveAndFlush(collection);
		assertThat(collectionSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Collection collectionSource = new Collection();
		collectionSource.setNom("nom");

		final Integer idTarget = 1;

		final Optional<Collection> collectionTargetOptional = Optional.of(new Collection());
		ReflectionTestUtils.setField(collectionTargetOptional.get(), Collection.ID, idTarget);

		given(collectionRepository.findById(idTarget)).willReturn(collectionTargetOptional);
		given(collectionRepository.saveAndFlush(any(Collection.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		final Collection collectionUpdated = collectionService.update(collectionSource, idTarget);

		verify(collectionRepository).findById(idTarget);
		verify(collectionRepository).saveAndFlush(any(Collection.class));
		assertThat(collectionUpdated.getId()).isEqualTo(idTarget);
		assertThat(collectionUpdated).isEqualToIgnoringGivenFields(collectionSource, Collection.ID);
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
		final Collection collection = new Collection();

		collectionService.delete(collection);

		verify(collectionRepository).delete(collection);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Collection> collectionOptional = Optional.of(new Collection());
		ReflectionTestUtils.setField(collectionOptional.get(), Collection.ID, id);

		given(collectionRepository.findById(id)).willReturn(collectionOptional);

		collectionService.deleteById(1);

		verify(collectionRepository).deleteById(collectionOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(collectionRepository.count()).willReturn(nbExpected);

		final long nbActual = collectionService.count();

		verify(collectionRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Collection> collectionOptional = Optional.of(new Collection());
		ReflectionTestUtils.setField(collectionOptional.get(), Collection.ID, id);

		given(collectionRepository.findById(id)).willReturn(collectionOptional);

		final Collection collectionActual = collectionService.findById(id);

		verify(collectionRepository).findById(id);
		assertThat(collectionActual).isEqualToComparingFieldByField(collectionOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> collectionService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Collection collection1 = new Collection();
		ReflectionTestUtils.setField(collection1, Collection.ID, 1);
		final Collection collection2 = new Collection();
		ReflectionTestUtils.setField(collection2, Collection.ID, 2);
		final Collection collection3 = new Collection();
		ReflectionTestUtils.setField(collection3, Collection.ID, 3);

		final List<Collection> collectionsExpected = new ArrayList<>(3);
		collectionsExpected.add(collection1);
		collectionsExpected.add(collection2);
		collectionsExpected.add(collection3);

		given(collectionRepository.findAll()).willReturn(collectionsExpected);

		final List<Collection> collectionsActual = collectionService.findAll();

		verify(collectionRepository).findAll();
		assertThat(collectionsActual).containsExactlyInAnyOrderElementsOf(collectionsExpected);
	}

}
