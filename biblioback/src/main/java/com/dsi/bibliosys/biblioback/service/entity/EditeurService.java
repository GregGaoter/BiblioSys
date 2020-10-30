package com.dsi.bibliosys.biblioback.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.repository.EditeurRepository;

/**
 * Classe fournissant les services de l'entité business Editeur.
 */
@Service
public class EditeurService implements CrudService<Editeur, Integer> {

	private final EditeurRepository editeurRepository;

	@Autowired
	public EditeurService(EditeurRepository editeurRepository) {
		this.editeurRepository = editeurRepository;
	}

	@Override
	public JpaRepository<Editeur, Integer> getRepository() {
		return editeurRepository;
	}

	@Override
	public Editeur create() {
		return new Editeur();
	}

}
