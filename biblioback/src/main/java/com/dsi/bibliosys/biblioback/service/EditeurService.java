package com.dsi.bibliosys.biblioback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.repository.EditeurRepository;
import com.dsi.bibliosys.biblioback.repository.LivreRepository;

/**
 * Classe fournissant les services de l'entité business Editeur.
 */
@Service
public class EditeurService implements CrudService<Editeur, Integer> {

	private final EditeurRepository editeurRepository;

	private final LivreRepository livreRepository;

	@Autowired
	public EditeurService(EditeurRepository editeurRepository, LivreRepository livreRepository) {
		this.editeurRepository = editeurRepository;
		this.livreRepository = livreRepository;
	}

	@Override
	public JpaRepository<Editeur, Integer> getRepository() {
		return editeurRepository;
	}

	@Override
	public Editeur create() {
		return new Editeur();
	}

	public Editeur findByLivreId(Integer id) {
		Livre livre = livreRepository.findById(id).orElseThrow();
		return livre.getEditeur();
	}

}
