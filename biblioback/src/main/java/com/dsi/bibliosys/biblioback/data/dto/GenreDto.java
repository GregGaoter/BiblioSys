package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GenreDto {

	public static final String ID = "id";
	public static final String NOM = "nom";
	public static final String RAYON_ID = "rayonId";
	public static final String[] FIELDS = { ID, NOM };

	private Integer id;

	private String nom;
	
	private Integer rayonId;

}
