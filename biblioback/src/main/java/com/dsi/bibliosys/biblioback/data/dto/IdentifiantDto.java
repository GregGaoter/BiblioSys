package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IdentifiantDto {

	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String MOT_DE_PASSE = "motDePasse";
	public static final String IS_ACTIF = "isActif";
	public static final String[] FIELDS = { ID, EMAIL, MOT_DE_PASSE, IS_ACTIF };

	private Integer id;

	private String email;

	private String motDePasse;

	private Boolean isActif;

}
