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
import com.dsi.bibliosys.biblioback.data.dto.PretDto;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.LivreService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class PretMapperTest {

	private PretMapper pretMapper;

	private static Integer id;
	private static Usager usager;
	private static Integer usagerId;
	private static Livre livre;
	private static Integer livreId;
	private static LocalDateTime datePret;
	private static Integer nbProlongations;
	private static Integer nbRelances;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Mock
	private UsagerService usagerService;
	@Mock
	private LivreService livreService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		usager = new Usager();
		usagerId = 2;
		ReflectionTestUtils.setField(usager, Usager.ID, usagerId);
		livre = new Livre();
		livreId = 3;
		ReflectionTestUtils.setField(livre, Livre.ID, livreId);
		datePret = LocalDateTime.of(2020, 11, 6, 10, 30);
		nbProlongations = 0;
		nbRelances = 4;
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		usager = null;
		usagerId = null;
		livre = null;
		livreId = null;
		datePret = null;
		nbProlongations = null;
		nbRelances = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		pretMapper = new PretMapper(usagerService, livreService);
	}

	@AfterEach
	public void unDefAfterEach() {
		pretMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Pret pret = new Pret();
		ReflectionTestUtils.setField(pret, Pret.ID, id);
		pret.setUsager(usager);
		pret.setLivre(livre);
		pret.setDatePret(datePret);
		pret.setNbProlongations(nbProlongations);
		pret.setNbRelances(nbRelances);

		PretDto pretDto = pretMapper.mapToDto(pret);

		assertThat(pretDto.getId()).isEqualTo(pret.getId());
		assertThat(pretDto.getUsagerId()).isEqualTo(pret.getUsager().getId());
		assertThat(pretDto.getLivreId()).isEqualTo(pret.getLivre().getId());
		assertThat(pretDto.getDatePret()).isEqualTo(pret.getDatePret());
		assertThat(pretDto.getNbProlongations()).isEqualTo(pret.getNbProlongations());
		assertThat(pretDto.getNbRelances()).isEqualTo(pret.getNbRelances());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> pretMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		PretDto pretDto = new PretDto();
		pretDto.setUsagerId(usagerId);
		pretDto.setLivreId(livreId);
		pretDto.setDatePret(datePret);
		pretDto.setNbProlongations(nbProlongations);
		pretDto.setNbRelances(nbRelances);

		given(usagerService.findById(anyInt())).willReturn(usager);
		given(livreService.findById(anyInt())).willReturn(livre);

		Pret pret = pretMapper.mapToEntity(pretDto);

		verify(usagerService).findById(pretDto.getUsagerId());
		verify(livreService).findById(pretDto.getLivreId());
		assertThat(pret.getId()).isNull();
		assertThat(pret.getUsager().getId()).isEqualTo(pretDto.getUsagerId());
		assertThat(pret.getLivre().getId()).isEqualTo(pretDto.getLivreId());
		assertThat(pret.getDatePret()).isEqualTo(pretDto.getDatePret());
		assertThat(pret.getNbProlongations()).isEqualTo(pretDto.getNbProlongations());
		assertThat(pret.getNbRelances()).isEqualTo(pretDto.getNbRelances());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> pretMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
