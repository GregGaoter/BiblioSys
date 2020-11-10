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
import com.dsi.bibliosys.biblioback.data.dto.RoleDto;
import com.dsi.bibliosys.biblioback.data.entity.Role;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class RoleMapperTest {

	private RoleMapper roleMapper;

	private static Integer id;
	private static String nom;
	private static String mapToDtoExceptionMessage;
	private static String mapToEntityExceptionMessage;

	@BeforeAll
	public static void setUpBeforeAll() {
		id = 1;
		nom = "ADMIN";
		mapToDtoExceptionMessage = "source";
		mapToEntityExceptionMessage = "source";
	}

	@AfterAll
	public static void unDefAfterAll() {
		id = null;
		nom = null;
		mapToDtoExceptionMessage = null;
		mapToEntityExceptionMessage = null;
	}

	@BeforeEach
	public void setUpBeforeEach() {
		roleMapper = new RoleMapper();
	}

	@AfterEach
	public void unDefAfterEach() {
		roleMapper = null;
	}

	@Test
	public void mapToDto_source() {
		Role role = new Role();
		ReflectionTestUtils.setField(role, Role.ID, id);
		role.setNom(nom);

		RoleDto roleDto = roleMapper.mapToDto(role);

		assertThat(roleDto.getId()).isEqualTo(role.getId());
		assertThat(roleDto.getNom()).isEqualTo(role.getNom());
	}

	@Test
	public void mapToDto_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> roleMapper.mapToDto(null));

		assertThat(exception.getMessage().contains(mapToDtoExceptionMessage)).isTrue();
	}

	@Test
	public void mapToEntity_source() {
		RoleDto roleDto = new RoleDto();
		roleDto.setNom(nom);

		Role role = roleMapper.mapToEntity(roleDto);

		assertThat(role.getId()).isNull();
		assertThat(role.getId()).isEqualTo(roleDto.getId());
		assertThat(role.getNom()).isEqualTo(roleDto.getNom());
	}

	@Test
	public void mapToEntity_null() {
		Exception exception = assertThrows(NullPointerException.class, () -> roleMapper.mapToEntity(null));

		assertThat(exception.getMessage().contains(mapToEntityExceptionMessage)).isTrue();
	}

}
