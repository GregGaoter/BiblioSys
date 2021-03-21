package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.PretDto;
import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.service.LivreService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Pret et
 * l'entité DTO PretDto.
 */
@Component
public class PretMapper extends AbstractMapper implements Mapper<Pret, PretDto> {

	/**
	 * Constructeur.
	 * 
	 * @param usagerService Service de l'entité business Usager.
	 * @param livreService  Service de l'entité business Livre.
	 */
	public PretMapper(UsagerService usagerService, LivreService livreService) {
		this.usagerService = usagerService;
		this.livreService = livreService;
	}

	@Override
	public PretDto mapToDto(@NonNull Pret source) {
		PretDto pretDto = new PretDto();
		pretDto.setId(source.getId());
		pretDto.setUsagerId(source.getUsager().getId());
		pretDto.setLivreId(source.getLivre().getId());
		pretDto.setDatePret(source.getDatePret());
		pretDto.setNbProlongations(source.getNbProlongations());
		pretDto.setNbRelances(source.getNbRelances());
		return pretDto;
	}

	@Override
	public Pret mapToEntity(@NonNull PretDto source) {
		Pret pret = new Pret();
		pret.setUsager(usagerService.findById(source.getUsagerId()));
		pret.setLivre(livreService.findById(source.getLivreId()));
		pret.setDatePret(source.getDatePret());
		pret.setNbProlongations(source.getNbProlongations());
		pret.setNbRelances(source.getNbRelances());
		return pret;
	}

}
