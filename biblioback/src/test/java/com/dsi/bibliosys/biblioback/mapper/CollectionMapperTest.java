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
import com.dsi.bibliosys.biblioback.data.dto.CollectionDto;
import com.dsi.bibliosys.biblioback.data.entity.Collection;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class CollectionMapperTest {

	private CollectionMapper collectionMapper;

	private static Integer id;
	private static String nom;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "Provence";
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
		collectionMapper = new CollectionMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		collectionMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Collection collection = new Collection();
		ReflectionTestUtils.setField(collection, Collection.ID, id);
		collection.setNom(nom);

		CollectionDto collectionDto = collectionMapper.mapToDto(collection);

		assertThat(collectionDto.getId()).isEqualTo(collection.getId());
		assertThat(collectionDto.getNom()).isEqualTo(collection.getNom());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> collectionMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		CollectionDto collectionDto = new CollectionDto();
		collectionDto.setNom(nom);

		Collection collection = collectionMapper.mapToEntity(collectionDto);

		assertThat(collection.getId()).isNull();
		assertThat(collection.getNom()).isEqualTo(collectionDto.getNom());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> collectionMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
