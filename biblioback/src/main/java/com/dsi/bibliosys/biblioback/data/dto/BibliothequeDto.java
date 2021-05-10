package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BibliothequeDto {

	public static final String ID = "id";
	public static final String NOM = "nom";
	public static final String ADRESSE_ID = "adresseId";
	public static final String DESCRIPTION = "description";
	public static final String IMAGE_FILE_NAME = "imageFileName";
	public static final String[] FIELDS = { ID, NOM, ADRESSE_ID, DESCRIPTION, IMAGE_FILE_NAME };

	private Integer id;

	private String nom;

	private Integer adresseId;

	private String description;

	private String imageFileName;

}
