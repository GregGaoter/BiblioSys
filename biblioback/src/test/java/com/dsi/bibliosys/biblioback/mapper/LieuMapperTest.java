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
import com.dsi.bibliosys.biblioback.data.dto.LieuDto;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class LieuMapperTest {

	private LieuMapper lieuMapper;

	private static Integer id;
	private static String region;
	private static String departement;
	private static String codePostal;
	private static String ville;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		region = "Bretagne";
		departement = "Ille-et-Vilaine";
		codePostal = "07000";
		ville = "Privas";
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		region = null;
		departement = null;
		codePostal = null;
		ville = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		lieuMapper = new LieuMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		lieuMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Lieu lieu = new Lieu();
		ReflectionTestUtils.setField(lieu, Lieu.ID, id);
		lieu.setRegion(region);
		lieu.setDepartement(departement);
		lieu.setCodePostal(codePostal);
		lieu.setVille(ville);

		LieuDto lieuDto = lieuMapper.mapToDto(lieu);

		assertThat(lieuDto.getId()).isEqualTo(lieu.getId());
		assertThat(lieuDto.getRegion()).isEqualTo(lieu.getRegion());
		assertThat(lieuDto.getDepartement()).isEqualTo(lieu.getDepartement());
		assertThat(lieuDto.getCodePostal()).isEqualTo(lieu.getCodePostal());
		assertThat(lieuDto.getVille()).isEqualTo(lieu.getVille());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> lieuMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		LieuDto lieuDto = new LieuDto();
		lieuDto.setRegion(region);
		lieuDto.setDepartement(departement);
		lieuDto.setCodePostal(codePostal);
		lieuDto.setVille(ville);

		Lieu lieu = lieuMapper.mapToEntity(lieuDto);

		assertThat(lieu.getId()).isNull();
		assertThat(lieu.getRegion()).isEqualTo(lieuDto.getRegion());
		assertThat(lieu.getDepartement()).isEqualTo(lieuDto.getDepartement());
		assertThat(lieu.getCodePostal()).isEqualTo(lieuDto.getCodePostal());
		assertThat(lieu.getVille()).isEqualTo(lieuDto.getVille());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> lieuMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
