package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

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
import com.dsi.bibliosys.biblioback.data.dto.UsagerDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class UsagerMapperTest {

	private UsagerMapper usagerMapper;

	private static Identifiant identifiant;
	private static Adresse adresse;
	private static Integer identifiantId;
	private static Integer adresseId;
	private static Integer id;
	private static String prenom;
	private static String nom;
	private static LocalDateTime dateNaissance;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Mock
	private IdentifiantService identifiantService;
	@Mock
	private AdresseService adresseService;

	@BeforeAll
	public static void setUpBeforeAll() {
		identifiantId = 1;
		adresseId = 2;
		id = 3;
		prenom = "Vincent";
		nom = "Senapus";
		dateNaissance = LocalDateTime.of(1979, 6, 5, 23, 37);
		identifiant = new Identifiant();
		ReflectionTestUtils.setField(identifiant, Identifiant.ID, identifiantId);
		adresse = new Adresse();
		ReflectionTestUtils.setField(adresse, Adresse.ID, adresseId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		identifiantId = null;
		adresseId = null;
		id = null;
		prenom = null;
		nom = null;
		dateNaissance = null;
		identifiant = null;
		adresse = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		usagerMapper = new UsagerMapper(identifiantService, adresseService);
	}

	@AfterEach
	public void unDefAfterEach() {
		usagerMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Usager usager = new Usager();
		ReflectionTestUtils.setField(usager, Usager.ID, id);
		usager.setPrenom(prenom);
		usager.setNom(nom);
		usager.setDateNaissance(dateNaissance);
		usager.setIdentifiant(identifiant);
		usager.setAdresse(adresse);

		UsagerDto usagerDto = usagerMapper.mapToDto(usager);

		assertThat(usagerDto.getId()).isEqualTo(usager.getId());
		assertThat(usagerDto.getPrenom()).isEqualTo(usager.getPrenom());
		assertThat(usagerDto.getNom()).isEqualTo(usager.getNom());
		assertThat(usagerDto.getDateNaissance()).isEqualTo(usager.getDateNaissance());
		assertThat(usagerDto.getIdentifiantId()).isEqualTo(usager.getIdentifiant().getId());
		assertThat(usagerDto.getAdresseId()).isEqualTo(usager.getAdresse().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> usagerMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		UsagerDto usagerDto = new UsagerDto();
		usagerDto.setPrenom(prenom);
		usagerDto.setNom(nom);
		usagerDto.setDateNaissance(dateNaissance);
		usagerDto.setIdentifiantId(identifiantId);
		usagerDto.setAdresseId(adresseId);

		given(identifiantService.findById(anyInt())).willReturn(identifiant);
		given(adresseService.findById(anyInt())).willReturn(adresse);

		Usager usager = usagerMapper.mapToEntity(usagerDto);

		verify(identifiantService).findById(usagerDto.getIdentifiantId());
		verify(adresseService).findById(usagerDto.getAdresseId());
		assertThat(usager.getId()).isNull();
		assertThat(usager.getPrenom()).isEqualTo(usagerDto.getPrenom());
		assertThat(usager.getNom()).isEqualTo(usagerDto.getNom());
		assertThat(usager.getDateNaissance()).isEqualTo(usagerDto.getDateNaissance());
		assertThat(usager.getIdentifiant().getId()).isEqualTo(usagerDto.getIdentifiantId());
		assertThat(usager.getAdresse().getId()).isEqualTo(usagerDto.getAdresseId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> usagerMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
