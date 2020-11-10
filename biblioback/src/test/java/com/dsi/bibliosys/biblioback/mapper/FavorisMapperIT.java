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
import com.dsi.bibliosys.biblioback.data.dto.FavorisDto;
import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.LivreService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestCase.INTEGRATION)
public class FavorisMapperIT {

	private FavorisMapper favorisMapper;

	private static Integer id;
	private static Usager usager;
	private static Integer usagerId;
	private static Livre livre;
	private static Integer livreId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Autowired
	private UsagerService usagerService;
	@Autowired
	private LivreService livreService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		usager = new Usager();
		usagerId = 1;
		ReflectionTestUtils.setField(usager, Usager.ID, usagerId);
		livre = new Livre();
		livreId = 1;
		ReflectionTestUtils.setField(livre, Livre.ID, livreId);
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
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		favorisMapper = new FavorisMapper(usagerService, livreService);
	}

	@AfterEach
	public void unDefAfterEach() {
		favorisMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Favoris favoris = new Favoris();
		ReflectionTestUtils.setField(favoris, Favoris.ID, id);
		favoris.setUsager(usager);
		favoris.setLivre(livre);

		FavorisDto favorisDto = favorisMapper.mapToDto(favoris);

		assertThat(favorisDto.getId()).isEqualTo(favoris.getId());
		assertThat(favorisDto.getUsagerId()).isEqualTo(favoris.getUsager().getId());
		assertThat(favorisDto.getLivreId()).isEqualTo(favoris.getLivre().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> favorisMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		FavorisDto favorisDto = new FavorisDto();
		favorisDto.setUsagerId(usagerId);
		favorisDto.setLivreId(livreId);

		Favoris favoris = favorisMapper.mapToEntity(favorisDto);

		assertThat(favoris.getId()).isNull();
		assertThat(favoris.getUsager().getId()).isEqualTo(favorisDto.getUsagerId());
		assertThat(favoris.getLivre().getId()).isEqualTo(favorisDto.getLivreId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> favorisMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
