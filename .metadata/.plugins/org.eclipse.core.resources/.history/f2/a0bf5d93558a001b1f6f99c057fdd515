package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.EcritureLivreDto;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.service.AuteurService;
import com.dsi.bibliosys.biblioback.service.LivreService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business
 * EcritureLivre et l'entité DTO EcritureLivreDto.
 */
@Component
public class EcritureLivreMapper extends AbstractMapper implements Mapper<EcritureLivre, EcritureLivreDto> {

	/**
	 * Constructeur.
	 * 
	 * @param livreService  Service de l'entité business Livre.
	 * @param auteurService Service de l'entité business Auteur.
	 */
	public EcritureLivreMapper(LivreService livreService, AuteurService auteurService) {
		this.livreService = livreService;
		this.auteurService = auteurService;
	}

	@Override
	public EcritureLivreDto mapToDto(@NonNull EcritureLivre source) {
		EcritureLivreDto ecritureLivreDto = new EcritureLivreDto();
		ecritureLivreDto.setId(source.getId());
		ecritureLivreDto.setLivreId(source.getLivre().getId());
		ecritureLivreDto.setAuteurId(source.getAuteur().getId());
		return ecritureLivreDto;
	}

	@Override
	public EcritureLivre mapToEntity(@NonNull EcritureLivreDto source) {
		EcritureLivre ecritureLivre = new EcritureLivre();
		ecritureLivre.setLivre(livreService.findById(source.getLivreId()));
		ecritureLivre.setAuteur(auteurService.findById(source.getAuteurId()));
		return ecritureLivre;
	}

}
