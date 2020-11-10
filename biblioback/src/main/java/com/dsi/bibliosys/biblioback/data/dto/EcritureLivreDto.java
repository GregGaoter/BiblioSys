package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EcritureLivreDto {

	public static final String ID = "id";
	public static final String LIVRE_ID = "livreId";
	public static final String AUTEUR_ID = "auteurId";
	public static final String[] FIELDS = { ID, LIVRE_ID, AUTEUR_ID };

	private Integer id;

	private Integer livreId;

	private Integer auteurId;

}
