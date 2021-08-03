package com.dsi.bibliosys.biblioback.data.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmpruntsResultatDto {

	public static final String PRET_ID = "pretId";
	public static final String BIBLIOTHEQUE = "bibliotheque";
	public static final String TITRE = "titre";
	public static final String AUTEURS_PRENOM_NOM = "auteursPrenomNom";
	public static final String DATERETOUR = "dateRetour";
	public static final String PROLONGATIONS = "prolongations";
	public static final String RELANCES = "relances";
	public static final String[] FIELDS = { PRET_ID, BIBLIOTHEQUE, TITRE, AUTEURS_PRENOM_NOM, DATERETOUR, PROLONGATIONS,
			RELANCES };

	private Integer pretId;
	private String bibliotheque;
	private String titre;
	private List<String> auteursPrenomNom;
	private String dateRetour;
	private int prolongations;
	private int relances;

}
