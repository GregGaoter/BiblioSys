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
import com.dsi.bibliosys.biblioback.data.dto.BibliothequeDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.service.AdresseService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestCase.INTEGRATION)
public class BibliothequeMapperIT {

	private BibliothequeMapper bibliothequeMapper;

	private static Integer id;
	private static String nom;
	private static Adresse adresse;
	private static Integer adresseId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Autowired
	private AdresseService adresseService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "Reine Elisabeth";
		adresse = new Adresse();
		adresseId = 1;
		ReflectionTestUtils.setField(adresse, Adresse.ID, adresseId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		nom = null;
		adresse = null;
		adresseId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		bibliothequeMapper = new BibliothequeMapper(adresseService);
	}

	@AfterEach
	public void unDefAfterEach() {
		bibliothequeMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Bibliotheque bibliotheque = new Bibliotheque();
		ReflectionTestUtils.setField(bibliotheque, Bibliotheque.ID, id);
		bibliotheque.setNom(nom);
		bibliotheque.setAdresse(adresse);

		BibliothequeDto bibliothequeDto = bibliothequeMapper.mapToDto(bibliotheque);

		assertThat(bibliothequeDto.getId()).isEqualTo(bibliotheque.getId());
		assertThat(bibliothequeDto.getNom()).isEqualTo(bibliotheque.getNom());
		assertThat(bibliothequeDto.getAdresseId()).isEqualTo(bibliotheque.getAdresse().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		BibliothequeDto bibliothequeDto = new BibliothequeDto();
		bibliothequeDto.setNom(nom);
		bibliothequeDto.setAdresseId(adresseId);

		Bibliotheque bibliotheque = bibliothequeMapper.mapToEntity(bibliothequeDto);

		assertThat(bibliotheque.getId()).isNull();
		assertThat(bibliotheque.getNom()).isEqualTo(bibliothequeDto.getNom());
		assertThat(bibliotheque.getAdresse().getId()).isEqualTo(bibliothequeDto.getAdresseId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> bibliothequeMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
