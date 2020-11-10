package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.RoleDto;
import com.dsi.bibliosys.biblioback.data.entity.Role;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Role et
 * l'entité DTO RoleDto.
 */
@Component
public class RoleMapper extends AbstractMapper implements Mapper<Role, RoleDto> {

	@Override
	public RoleDto mapToDto(@NonNull Role source) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(source.getId());
		roleDto.setNom(source.getNom());
		return roleDto;
	}

	@Override
	public Role mapToEntity(@NonNull RoleDto source) {
		Role role = new Role();
		role.setNom(source.getNom());
		return role;
	}

}
