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
import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.repository.FavorisRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class FavorisServiceTest {

	private FavorisService favorisService;

	@Mock
	private FavorisRepository favorisRepository;

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
		final Favoris favoris = new Favoris();

		given(favorisRepository.saveAndFlush(favoris)).willAnswer(invocation -> invocation.getArgument(0));

		final Favoris favorisSaved = favorisService.save(favoris);

		verify(favorisRepository).saveAndFlush(favoris);
		assertThat(favorisSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Favoris favorisSource = new Favoris();
		favorisSource.setLivre(new Livre());
		favorisSource.setUsager(new Usager());

		final Integer idTarget = 1;

		final Optional<Favoris> favorisTargetOptional = Optional.of(new Favoris());
		ReflectionTestUtils.setField(favorisTargetOptional.get(), Favoris.ID, idTarget);

		given(favorisRepository.findById(idTarget)).willReturn(favorisTargetOptional);
		given(favorisRepository.saveAndFlush(any(Favoris.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Favoris favorisUpdated = favorisService.update(favorisSource, idTarget);

		verify(favorisRepository).findById(idTarget);
		verify(favorisRepository).saveAndFlush(any(Favoris.class));
		assertThat(favorisUpdated.getId()).isEqualTo(idTarget);
		assertThat(favorisUpdated).isEqualToIgnoringGivenFields(favorisSource, Favoris.ID);
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
		final Favoris favoris = new Favoris();

		favorisService.delete(favoris);

		verify(favorisRepository).delete(favoris);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Favoris> favorisOptional = Optional.of(new Favoris());
		ReflectionTestUtils.setField(favorisOptional.get(), Favoris.ID, id);

		given(favorisRepository.findById(id)).willReturn(favorisOptional);

		favorisService.deleteById(1);

		verify(favorisRepository).deleteById(favorisOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(favorisRepository.count()).willReturn(nbExpected);

		final long nbActual = favorisService.count();

		verify(favorisRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Favoris> favorisOptional = Optional.of(new Favoris());
		ReflectionTestUtils.setField(favorisOptional.get(), Favoris.ID, id);

		given(favorisRepository.findById(id)).willReturn(favorisOptional);

		final Favoris favorisActual = favorisService.findById(id);

		verify(favorisRepository).findById(id);
		assertThat(favorisActual).isEqualToComparingFieldByField(favorisOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> favorisService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Favoris favoris1 = new Favoris();
		ReflectionTestUtils.setField(favoris1, Favoris.ID, 1);
		final Favoris favoris2 = new Favoris();
		ReflectionTestUtils.setField(favoris2, Favoris.ID, 2);
		final Favoris favoris3 = new Favoris();
		ReflectionTestUtils.setField(favoris3, Favoris.ID, 3);

		final List<Favoris> favorissExpected = new ArrayList<>(3);
		favorissExpected.add(favoris1);
		favorissExpected.add(favoris2);
		favorissExpected.add(favoris3);

		given(favorisRepository.findAll()).willReturn(favorissExpected);

		final List<Favoris> favorissActual = favorisService.findAll();

		verify(favorisRepository).findAll();
		assertThat(favorissActual).containsExactlyInAnyOrderElementsOf(favorissExpected);
	}

}
