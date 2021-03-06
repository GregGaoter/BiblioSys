package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LivreResultatDto {

	public static final String LIVRE = "livre";
	public static final String AUTEUR = "auteur";
	public static final String EDITEUR = "editeur";
	public static final String[] FIELDS = { LIVRE, AUTEUR, EDITEUR };

	private LivreDto livre;

	private AuteurDto auteur;

	private EditeurDto editeur;

}
