package com.dsi.bibliosys.biblioback.mapper;

import org.springframework.stereotype.Component;

import com.dsi.bibliosys.biblioback.data.dto.LivreDto;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.Collection;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;
import com.dsi.bibliosys.biblioback.service.CollectionService;
import com.dsi.bibliosys.biblioback.service.EditeurService;
import com.dsi.bibliosys.biblioback.service.GenreService;

import lombok.NonNull;

/**
 * Classe fournissant les opérations de mapping entre l'entité business Livre et
 * l'entité DTO LivreDto.
 */
@Component
public class LivreMapper extends AbstractMapper implements Mapper<Livre, LivreDto> {

	/**
	 * Constructeur.
	 * 
	 * @param bibliothequeService Service de l'entité business Bibliotheque.
	 * @param genreService        Service de l'entité business Genre.
	 * @param editeurService      Service de l'entité business Editeur.
	 * @param collectionService   Service de l'entité business Collection.
	 */
	public LivreMapper(BibliothequeService bibliothequeService, GenreService genreService,
			EditeurService editeurService, CollectionService collectionService) {
		this.bibliothequeService = bibliothequeService;
		this.genreService = genreService;
		this.editeurService = editeurService;
		this.collectionService = collectionService;
	}

	@Override
	public LivreDto mapToDto(@NonNull Livre source) {
		LivreDto livreDto = new LivreDto();
		Bibliotheque bibliotheque = source.getBibliotheque();
		Genre genre = source.getGenre();
		Editeur editeur = source.getEditeur();
		Collection collection = source.getCollection();
		livreDto.setId(source.getId());
		livreDto.setBibliothequeId(bibliotheque == null ? null : bibliotheque.getId());
		livreDto.setGenreId(genre == null ? null : genre.getId());
		livreDto.setNomImage(source.getNomImage());
		livreDto.setTitre(source.getTitre());
		livreDto.setEditeurId(editeur == null ? null : editeur.getId());
		livreDto.setCollectionId(collection == null ? null : collection.getId());
		livreDto.setDateParution(source.getDateParution());
		livreDto.setDimension(source.getDimension());
		livreDto.setNbPages(source.getNbPages());
		livreDto.setEan13(source.getEan13());
		livreDto.setNbExemplaires(source.getNbExemplaires());
		livreDto.setResume(source.getResume());
		return livreDto;
	}

	@Override
	public Livre mapToEntity(@NonNull LivreDto source) {
		Livre livre = new Livre();
		livre.setBibliotheque(bibliothequeService.findById(source.getBibliothequeId()));
		livre.setGenre(genreService.findById(source.getGenreId()));
		livre.setNomImage(source.getNomImage());
		livre.setTitre(source.getTitre());
		livre.setEditeur(editeurService.findById(source.getEditeurId()));
		livre.setCollection(collectionService.findById(source.getCollectionId()));
		livre.setDateParution(source.getDateParution());
		livre.setDimension(source.getDimension());
		livre.setNbPages(source.getNbPages());
		livre.setEan13(source.getEan13());
		livre.setNbExemplaires(source.getNbExemplaires());
		livre.setResume(source.getResume());
		return livre;
	}

}
