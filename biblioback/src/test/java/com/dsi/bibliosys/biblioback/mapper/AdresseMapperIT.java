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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.dto.AdresseDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.service.LieuService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestCase.INTEGRATION)
public class AdresseMapperIT {

	private AdresseMapper adresseMapper;

	private static Integer id;
	private static Integer numeroRue;
	private static String rue;
	private static Lieu lieu;
	private static Integer lieuId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Autowired
	private LieuService lieuService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		numeroRue = 6;
		rue = "quai Saint-Nicolas";
		lieu = new Lieu();
		lieuId = 1;
		ReflectionTestUtils.setField(lieu, Lieu.ID, lieuId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		numeroRue = null;
		rue = null;
		lieu = null;
		lieuId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		adresseMapper = new AdresseMapper(lieuService);
	}

	@AfterEach
	public void unDefAfterEach() {
		adresseMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Adresse adresse = new Adresse();
		ReflectionTestUtils.setField(adresse, Adresse.ID, id);
		adresse.setNumeroRue(numeroRue);
		adresse.setRue(rue);
		adresse.setLieu(lieu);

		AdresseDto adresseDto = adresseMapper.mapToDto(adresse);

		assertThat(adresseDto.getId()).isEqualTo(adresse.getId());
		assertThat(adresseDto.getNumeroRue()).isEqualTo(adresse.getNumeroRue());
		assertThat(adresseDto.getRue()).isEqualTo(adresse.getRue());
		assertThat(adresseDto.getLieuId()).isEqualTo(adresse.getLieu().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> adresseMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		AdresseDto adresseDto = new AdresseDto();
		adresseDto.setNumeroRue(numeroRue);
		adresseDto.setRue(rue);
		adresseDto.setLieuId(lieuId);

		Adresse adresse = adresseMapper.mapToEntity(adresseDto);

		assertThat(adresse.getId()).isNull();
		assertThat(adresse.getNumeroRue()).isEqualTo(adresseDto.getNumeroRue());
		assertThat(adresse.getRue()).isEqualTo(adresseDto.getRue());
		assertThat(adresse.getLieu().getId()).isEqualTo(adresseDto.getLieuId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> adresseMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
