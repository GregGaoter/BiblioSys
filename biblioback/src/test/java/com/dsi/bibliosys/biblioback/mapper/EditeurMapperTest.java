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
import com.dsi.bibliosys.biblioback.data.dto.EditeurDto;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class EditeurMapperTest {

	private EditeurMapper editeurMapper;

	private static Integer id;
	private static String nom;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "Clair Bocage";
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
		editeurMapper = new EditeurMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		editeurMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Editeur editeur = new Editeur();
		ReflectionTestUtils.setField(editeur, Editeur.ID, id);
		editeur.setNom(nom);

		EditeurDto editeurDto = editeurMapper.mapToDto(editeur);

		assertThat(editeurDto.getId()).isEqualTo(editeur.getId());
		assertThat(editeurDto.getNom()).isEqualTo(editeur.getNom());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> editeurMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		EditeurDto editeurDto = new EditeurDto();
		editeurDto.setNom(nom);

		Editeur editeur = editeurMapper.mapToEntity(editeurDto);

		assertThat(editeur.getId()).isNull();
		assertThat(editeur.getNom()).isEqualTo(editeurDto.getNom());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> editeurMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
