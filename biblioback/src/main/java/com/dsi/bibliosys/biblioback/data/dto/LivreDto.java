package com.dsi.bibliosys.biblioback.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LivreDto {

	public static final String ID = "id";
	public static final String BIBLIOTHEQUE_ID = "bibliothequeId";
	public static final String GENRE_ID = "genreId";
	public static final String NOM_IMAGE = "nomImage";
	public static final String TITRE = "titre";
	public static final String EDITEUR_ID = "editeurId";
	public static final String COLLECTION_ID = "collectionId";
	public static final String DATE_PARUTION = "dateParution";
	public static final String DIMENSION = "dimension";
	public static final String NB_PAGES = "nbPages";
	public static final String EAN13 = "ean13";
	public static final String NB_EXEMPLAIRES = "nbExemplaires";
	public static final String RESUME = "resume";
	public static final String[] FIELDS = { ID, BIBLIOTHEQUE_ID, GENRE_ID, NOM_IMAGE, TITRE, EDITEUR_ID, COLLECTION_ID,
			DATE_PARUTION, DIMENSION, NB_PAGES, EAN13, NB_EXEMPLAIRES, RESUME };

	private Integer id;

	private Integer bibliothequeId;

	private Integer genreId;

	private String nomImage;

	private String titre;

	private Integer editeurId;

	private Integer collectionId;

	private LocalDateTime dateParution;

	private String dimension;

	private Integer nbPages;

	private String ean13;

	private Integer nbExemplaires;

	private String resume;

}
