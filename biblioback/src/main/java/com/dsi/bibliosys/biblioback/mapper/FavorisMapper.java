package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.FavorisDto;
import com.dsi.bibliosys.biblioback.data.entity.Favoris;
import com.dsi.bibliosys.biblioback.service.LivreService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Favoris
 * et l'entité DTO FavorisDto.
 */
@Component
public class FavorisMapper extends AbstractMapper implements Mapper<Favoris, FavorisDto> {

	/**
	 * Constructeur.
	 * 
	 * @param usagerService Service de l'entité business Usager.
	 * @param livreService  Service de l'entité business Livre.
	 */
	public FavorisMapper(UsagerService usagerService, LivreService livreService) {
		this.usagerService = usagerService;
		this.livreService = livreService;
	}

	@Override
	public FavorisDto mapToDto(@NonNull Favoris source) {
		FavorisDto favorisDto = new FavorisDto();
		favorisDto.setId(source.getId());
		favorisDto.setUsagerId(source.getUsager().getId());
		favorisDto.setLivreId(source.getLivre().getId());
		return favorisDto;
	}

	@Override
	public Favoris mapToEntity(@NonNull FavorisDto source) {
		Favoris favoris = new Favoris();
		favoris.setUsager(usagerService.findById(source.getUsagerId()));
		favoris.setLivre(livreService.findById(source.getLivreId()));
		return favoris;
	}

}
