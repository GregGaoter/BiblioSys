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
import com.dsi.bibliosys.biblioback.data.dto.AuteurDto;
import com.dsi.bibliosys.biblioback.data.entity.Auteur;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class AuteurMapperTest {

	private AuteurMapper auteurMapper;

	private static Integer id;
	private static String prenomNom;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		prenomNom = "Brie Vertefeuille";
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		prenomNom = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		auteurMapper = new AuteurMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		auteurMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Auteur auteur = new Auteur();
		ReflectionTestUtils.setField(auteur, Auteur.ID, id);
		auteur.setPrenomNom(prenomNom);

		AuteurDto auteurDto = auteurMapper.mapToDto(auteur);

		assertThat(auteurDto.getId()).isEqualTo(auteur.getId());
		assertThat(auteurDto.getPrenomNom()).isEqualTo(auteur.getPrenomNom());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> auteurMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		AuteurDto auteurDto = new AuteurDto();
		auteurDto.setPrenomNom(prenomNom);

		Auteur auteur = auteurMapper.mapToEntity(auteurDto);

		assertThat(auteur.getId()).isNull();
		assertThat(auteur.getPrenomNom()).isEqualTo(auteurDto.getPrenomNom());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> auteurMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
