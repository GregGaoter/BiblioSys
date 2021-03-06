package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.AdresseDto;
import com.dsi.bibliosys.biblioback.data.entity.Adresse;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.service.LieuService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Adresse
 * et l'entité DTO AdresseDto.
 */
@Component
public class AdresseMapper extends AbstractMapper implements Mapper<Adresse, AdresseDto> {

	/**
	 * Constructeur.
	 * 
	 * @param lieuService Service de l'entité business Lieu.
	 */
	public AdresseMapper(LieuService lieuService) {
		this.lieuService = lieuService;
	}

	@Override
	public AdresseDto mapToDto(@NonNull Adresse source) {
		AdresseDto adresseDto = new AdresseDto();
		Lieu lieu = source.getLieu();
		adresseDto.setId(source.getId());
		adresseDto.setNumeroRue(source.getNumeroRue());
		adresseDto.setRue(source.getRue());
		adresseDto.setLieuId(lieu == null ? null : lieu.getId());
		return adresseDto;
	}

	@Override
	public Adresse mapToEntity(@NonNull AdresseDto source) {
		Adresse adresse = new Adresse();
		adresse.setNumeroRue(source.getNumeroRue());
		adresse.setRue(source.getRue());
		adresse.setLieu(lieuService.findById(source.getLieuId()));
		return adresse;
	}

}
