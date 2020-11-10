package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuteurDto {

	public static final String ID = "id";
	public static final String PRENOM_NOM = "prenomNom";
	public static final String[] FIELDS = { ID, PRENOM_NOM };

	private Integer id;

	private String prenomNom;

}
