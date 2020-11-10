package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

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
import com.dsi.bibliosys.biblioback.data.dto.PersonnelDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Personnel;
import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestCase.INTEGRATION)
public class PersonnelMapperIT {

	private PersonnelMapper personnelMapper;

	private static Integer id;
	private static String prenom;
	private static String nom;
	private static LocalDateTime dateNaissance;
	private static Identifiant identifiant;
	private static Integer identifiantId;
	private static Adresse adresse;
	private static Integer adresseId;
	private static Bibliotheque bibliotheque;
	private static Integer bibliothequeId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Autowired
	private IdentifiantService identifiantService;
	@Autowired
	private AdresseService adresseService;
	@Autowired
	private BibliothequeService bibliothequeService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		prenom = "Eugenia";
		nom = "Lizotte";
		dateNaissance = LocalDateTime.of(1935, 1, 2, 10, 30);
		identifiant = new Identifiant();
		identifiantId = 1;
		ReflectionTestUtils.setField(identifiant, Identifiant.ID, identifiantId);
		adresse = new Adresse();
		adresseId = 1;
		ReflectionTestUtils.setField(adresse, Adresse.ID, adresseId);
		bibliotheque = new Bibliotheque();
		bibliothequeId = 1;
		ReflectionTestUtils.setField(bibliotheque, Bibliotheque.ID, bibliothequeId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		prenom = null;
		nom = null;
		dateNaissance = null;
		identifiant = null;
		identifiantId = null;
		adresse = null;
		adresseId = null;
		bibliotheque = null;
		bibliothequeId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		personnelMapper = new PersonnelMapper(identifiantService, adresseService, bibliothequeService);
	}

	@AfterEach
	public void unDefAfterEach() {
		personnelMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Personnel personnel = new Personnel();
		ReflectionTestUtils.setField(personnel, Personnel.ID, id);
		personnel.setPrenom(prenom);
		personnel.setNom(nom);
		personnel.setDateNaissance(dateNaissance);
		personnel.setIdentifiant(identifiant);
		personnel.setAdresse(adresse);
		personnel.setBibliotheque(bibliotheque);

		PersonnelDto personnelDto = personnelMapper.mapToDto(personnel);

		assertThat(personnelDto.getId()).isEqualTo(personnel.getId());
		assertThat(personnelDto.getPrenom()).isEqualTo(personnel.getPrenom());
		assertThat(personnelDto.getNom()).isEqualTo(personnel.getNom());
		assertThat(personnelDto.getDateNaissance()).isEqualTo(personnel.getDateNaissance());
		assertThat(personnelDto.getIdentifiantId()).isEqualTo(personnel.getIdentifiant().getId());
		assertThat(personnelDto.getAdresseId()).isEqualTo(personnel.getAdresse().getId());
		assertThat(personnelDto.getBibliothequeId()).isEqualTo(personnel.getBibliotheque().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> personnelMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		PersonnelDto personnelDto = new PersonnelDto();
		personnelDto.setPrenom(prenom);
		personnelDto.setNom(nom);
		personnelDto.setDateNaissance(dateNaissance);
		personnelDto.setIdentifiantId(identifiantId);
		personnelDto.setAdresseId(adresseId);
		personnelDto.setBibliothequeId(bibliothequeId);

		Personnel personnel = personnelMapper.mapToEntity(personnelDto);

		assertThat(personnel.getId()).isNull();
		assertThat(personnel.getPrenom()).isEqualTo(personnelDto.getPrenom());
		assertThat(personnel.getNom()).isEqualTo(personnelDto.getNom());
		assertThat(personnel.getDateNaissance()).isEqualTo(personnelDto.getDateNaissance());
		assertThat(personnel.getIdentifiant().getId()).isEqualTo(personnelDto.getIdentifiantId());
		assertThat(personnel.getAdresse().getId()).isEqualTo(personnelDto.getAdresseId());
		assertThat(personnel.getBibliotheque().getId()).isEqualTo(personnelDto.getBibliothequeId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> personnelMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
