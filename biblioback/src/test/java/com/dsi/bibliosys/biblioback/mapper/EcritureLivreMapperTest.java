package com.dsi.bibliosys.biblioback.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
import com.dsi.bibliosys.biblioback.data.dto.EcritureLivreDto;
import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.service.AuteurService;
import com.dsi.bibliosys.biblioback.service.LivreService;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class EcritureLivreMapperTest {

	private EcritureLivreMapper ecritureLivreMapper;

	private static Integer id;
	private static Livre livre;
	private static Integer livreId;
	private static Auteur auteur;
	private static Integer auteurId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Mock
	private LivreService livreService;
	@Mock
	private AuteurService auteurService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		livre = new Livre();
		livreId = 2;
		ReflectionTestUtils.setField(livre, Livre.ID, livreId);
		auteur = new Auteur();
		auteurId = 3;
		ReflectionTestUtils.setField(auteur, Auteur.ID, auteurId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		livre = null;
		livreId = null;
		auteur = null;
		auteurId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		ecritureLivreMapper = new EcritureLivreMapper(livreService, auteurService);
	}

	@AfterEach
	public void unDefAfterEach() {
		ecritureLivreMapper = null;
	}

	@Test
	public void mapToDto_source() {
		EcritureLivre ecritureLivre = new EcritureLivre();
		ReflectionTestUtils.setField(ecritureLivre, EcritureLivre.ID, id);
		ecritureLivre.setLivre(livre);
		ecritureLivre.setAuteur(auteur);

		EcritureLivreDto ecritureLivreDto = ecritureLivreMapper.mapToDto(ecritureLivre);

		assertThat(ecritureLivreDto.getId()).isEqualTo(ecritureLivre.getId());
		assertThat(ecritureLivreDto.getLivreId()).isEqualTo(ecritureLivre.getLivre().getId());
		assertThat(ecritureLivreDto.getAuteurId()).isEqualTo(ecritureLivre.getAuteur().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		EcritureLivreDto ecritureLivreDto = new EcritureLivreDto();
		ecritureLivreDto.setLivreId(livreId);
		ecritureLivreDto.setAuteurId(auteurId);

		given(livreService.findById(anyInt())).willReturn(livre);
		given(auteurService.findById(anyInt())).willReturn(auteur);

		EcritureLivre ecritureLivre = ecritureLivreMapper.mapToEntity(ecritureLivreDto);

		verify(livreService).findById(ecritureLivreDto.getLivreId());
		verify(auteurService).findById(ecritureLivreDto.getAuteurId());
		assertThat(ecritureLivre.getId()).isNull();
		assertThat(ecritureLivre.getLivre().getId()).isEqualTo(ecritureLivreDto.getLivreId());
		assertThat(ecritureLivre.getAuteur().getId()).isEqualTo(ecritureLivreDto.getAuteurId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> ecritureLivreMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
