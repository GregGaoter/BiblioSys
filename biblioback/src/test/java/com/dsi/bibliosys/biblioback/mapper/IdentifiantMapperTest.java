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
import com.dsi.bibliosys.biblioback.data.dto.IdentifiantDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class IdentifiantMapperTest {

	private IdentifiantMapper identifiantMapper;

	private static Integer id;
	private static String email;
	private static String motDePasse;
	private static Boolean isActif;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		email = "alitalapresse@armyspy.com";
		motDePasse = "VZWcf7CkbjT4L8Xe";
		isActif = true;
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		email = null;
		motDePasse = null;
		isActif = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		identifiantMapper = new IdentifiantMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		identifiantMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Identifiant identifiant = new Identifiant();
		ReflectionTestUtils.setField(identifiant, Identifiant.ID, id);
		identifiant.setEmail(email);
		identifiant.setMotDePasse(motDePasse);
		identifiant.setIsActif(isActif);

		IdentifiantDto identifiantDto = identifiantMapper.mapToDto(identifiant);

		assertThat(identifiantDto.getId()).isEqualTo(identifiant.getId());
		assertThat(identifiantDto.getEmail()).isEqualTo(identifiant.getEmail());
		assertThat(identifiantDto.getMotDePasse()).isEqualTo(identifiant.getMotDePasse());
		assertThat(identifiantDto.getIsActif()).isEqualTo(identifiant.getIsActif());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> identifiantMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		IdentifiantDto identifiantDto = new IdentifiantDto();
		identifiantDto.setEmail(email);
		identifiantDto.setMotDePasse(motDePasse);
		identifiantDto.setIsActif(isActif);

		Identifiant identifiant = identifiantMapper.mapToEntity(identifiantDto);

		assertThat(identifiant.getId()).isNull();
		assertThat(identifiant.getEmail()).isEqualTo(identifiantDto.getEmail());
		assertThat(identifiant.getMotDePasse()).isEqualTo(identifiantDto.getMotDePasse());
		assertThat(identifiant.getIsActif()).isEqualTo(identifiantDto.getIsActif());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> identifiantMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
