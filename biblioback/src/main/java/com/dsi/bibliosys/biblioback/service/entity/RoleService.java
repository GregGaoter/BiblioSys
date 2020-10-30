package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.repository.RoleRepository;

/**
 * Classe fournissant les services de l'entité business Role.
 */
@Service
public class RoleService implements CrudService<Role, Integer> {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public JpaRepository<Role, Integer> getRepository() {
		return roleRepository;
	}

	@Override
	public Role create() {
		return new Role();
	}

}
