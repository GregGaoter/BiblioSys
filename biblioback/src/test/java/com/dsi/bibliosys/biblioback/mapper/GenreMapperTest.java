package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.dto.GenreDto;
import com.dsi.bibliosys.biblioback.data.entity.Genre;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class GenreMapperTest {

	private GenreMapper genreMapper;

	private static Integer id;
	private static String nom;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "Nature";
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		nom = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		genreMapper = new GenreMapper();
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

		GenreDto genreDto = genreMapper.mapToDto(genre);

		assertThat(genreDto.getId()).isEqualTo(genre.getId());
		assertThat(genreDto.getNom()).isEqualTo(genre.getNom());
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

		Genre genre = genreMapper.mapToEntity(genreDto);

		assertThat(genre.getId()).isNull();
		assertThat(genre.getNom()).isEqualTo(genreDto.getNom());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> genreMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
