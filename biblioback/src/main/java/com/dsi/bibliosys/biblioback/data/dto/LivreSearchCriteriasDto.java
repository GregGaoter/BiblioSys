package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LivreSearchCriteriasDto {

	public static final String BIBLIOTHEQUE_NOM = "bibliothequeNom";
	public static final String RAYON_NOM = "rayonNom";
	public static final String GENRE_NOM = "genreNom";
	public static final String LIVRE_TITRE = "livreTitre";
	public static final String LIVRE_AUTEUR = "livreAuteur";
	public static final String LIVRE_DATE_PARUTION = "livreDateParution";
	public static final String[] FIELDS = { BIBLIOTHEQUE_NOM, RAYON_NOM, GENRE_NOM, LIVRE_TITRE, LIVRE_AUTEUR,
			LIVRE_DATE_PARUTION };

	private String bibliothequeNom;
	private String rayonNom;
	private String genreNom;
	private String livreTitre;
	private String livreAuteur;
	private String[] livreDateParution;

}
