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
import com.dsi.bibliosys.biblioback.data.dto.ProfilDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.RoleService;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class ProfilMapperTest {

	private ProfilMapper profilMapper;

	private static Integer id;
	private static Identifiant identifiant;
	private static Integer identifiantId;
	private static Role role;
	private static Integer roleId;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@Mock
	private IdentifiantService identifiantService;
	@Mock
	private RoleService roleService;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		identifiant = new Identifiant();
		identifiantId = 2;
		ReflectionTestUtils.setField(identifiant, Identifiant.ID, identifiantId);
		role = new Role();
		roleId = 3;
		ReflectionTestUtils.setField(role, Role.ID, roleId);
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		identifiant = null;
		identifiantId = null;
		role = null;
		roleId = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		profilMapper = new ProfilMapper(identifiantService, roleService);
	}

	@AfterEach
	public void unDefAfterEach() {
		profilMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Profil profil = new Profil();
		ReflectionTestUtils.setField(profil, Profil.ID, id);
		profil.setIdentifiant(identifiant);
		profil.setRole(role);

		ProfilDto profilDto = profilMapper.mapToDto(profil);

		assertThat(profilDto.getId()).isEqualTo(profil.getId());
		assertThat(profilDto.getIdentifiantId()).isEqualTo(profil.getIdentifiant().getId());
		assertThat(profilDto.getRoleId()).isEqualTo(profil.getRole().getId());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> profilMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		ProfilDto profilDto = new ProfilDto();
		profilDto.setIdentifiantId(identifiantId);
		profilDto.setRoleId(roleId);

		given(identifiantService.findById(anyInt())).willReturn(identifiant);
		given(roleService.findById(anyInt())).willReturn(role);

		Profil profil = profilMapper.mapToEntity(profilDto);

		verify(identifiantService).findById(profilDto.getIdentifiantId());
		verify(roleService).findById(profilDto.getRoleId());
		assertThat(profil.getId()).isNull();
		assertThat(profil.getIdentifiant().getId()).isEqualTo(profilDto.getIdentifiantId());
		assertThat(profil.getRole().getId()).isEqualTo(profilDto.getRoleId());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> profilMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
