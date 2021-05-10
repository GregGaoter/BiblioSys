package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.dto.GenreDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Rayon;
import com.dsi.bibliosys.biblioback.service.RayonService;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class GenreMapperTest {

	private GenreMapper genreMapper;

	private static Integer id;
	private static String nom;
	private static Rayon rayon;
	private static Integer rayonId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Mock
	private RayonService rayonService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "Nature";
		rayon = new Rayon();
		rayonId = 1;
		ReflectionTestUtils.setField(rayon, Rayon.ID, rayonId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		nom = null;
		rayon = null;
		rayonId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		genreMapper = new GenreMapper(rayonService);
	}

	@AfterEach
	public void unDefAfterEach() {
		genreMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Genre genre = new Genre();
		ReflectionTestUtils.setField(genre, Genre.ID, id);
		genre.setNom(nom);
		genre.setRayon(rayon);

		GenreDto genreDto = genreMapper.mapToDto(genre);

		assertThat(genreDto.getId()).isEqualTo(genre.getId());
		assertThat(genreDto.getNom()).isEqualTo(genre.getNom());
		assertThat(genreDto.getRayonId()).isEqualTo(genre.getRayon().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> genreMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		GenreDto genreDto = new GenreDto();
		genreDto.setNom(nom);
		genreDto.setRayonId(rayonId);

		given(rayonService.findById(anyInt())).willReturn(rayon);

		Genre genre = genreMapper.mapToEntity(genreDto);

		verify(rayonService).findById(genreDto.getRayonId());
		assertThat(genre.getId()).isNull();
		assertThat(genre.getNom()).isEqualTo(genreDto.getNom());
		assertThat(genre.getRayon().getId()).isEqualTo(genreDto.getRayonId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> genreMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
