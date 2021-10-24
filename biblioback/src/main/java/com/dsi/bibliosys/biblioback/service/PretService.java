package com.dsi.bibliosys.biblioback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.biblioback.app.Utils;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.repository.PretRepository;

/**
 * Classe fournissant les services de l'entité business Pret.
 */
@Service
public class PretService implements CrudService<Pret, Integer> {

	private final PretRepository pretRepository;

	@Autowired
	public PretService(PretRepository pretRepository) {
		this.pretRepository = pretRepository;
	}

	@Override
	public JpaRepository<Pret, Integer> getRepository() {
		return pretRepository;
	}

	@Override
	public Pret create() {
		return new Pret();
	}

	public List<Pret> findAll(Specification<Pret> spec) {
		return pretRepository.findAll(spec);
	}

	public List<Pret> findAllExpired() {
		List<Pret> prets = pretRepository.findAll();
		List<Pret> pretsExpired = prets.stream()
				.filter(pret -> Utils.getDateRetourPret(pret).isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());
		return pretsExpired;
	}

}
