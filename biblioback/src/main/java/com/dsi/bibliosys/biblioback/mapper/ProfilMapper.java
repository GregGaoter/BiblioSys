package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.ProfilDto;
import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Profil;
import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.RoleService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Profil
 * et l'entité DTO ProfilDto.
 */
@Component
public class ProfilMapper extends AbstractMapper implements Mapper<Profil, ProfilDto> {

	/**
	 * Constructeur.
	 * 
	 * @param identifiantService Service de l'entité business Identifiant.
	 * @param roleService        Service de l'entité business Role.
	 */
	public ProfilMapper(IdentifiantService identifiantService, RoleService roleService) {
		this.identifiantService = identifiantService;
		this.roleService = roleService;
	}

	@Override
	public ProfilDto mapToDto(@NonNull Profil source) {
		ProfilDto profilDto = new ProfilDto();
		Identifiant identifiant = source.getIdentifiant();
		Role role = source.getRole();
		profilDto.setId(source.getId());
		profilDto.setIdentifiantId(identifiant == null ? null : identifiant.getId());
		profilDto.setRoleId(role == null ? null : role.getId());
		return profilDto;
	}

	@Override
	public Profil mapToEntity(@NonNull ProfilDto source) {
		Profil profil = new Profil();
		profil.setIdentifiant(identifiantService.findById(source.getIdentifiantId()));
		profil.setRole(roleService.findById(source.getRoleId()));
		return profil;
	}

}
