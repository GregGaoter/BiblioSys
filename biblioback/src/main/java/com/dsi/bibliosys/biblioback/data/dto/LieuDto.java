package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LieuDto {

	public static final String ID = "id";
	public static final String REGION = "region";
	public static final String DEPARTEMENT = "departement";
	public static final String CODE_POSTAL = "codePostal";
	public static final String VILLE = "ville";
	public static final String[] FIELDS = { ID, REGION, DEPARTEMENT, CODE_POSTAL, VILLE };

	private Integer id;

	private String region;

	private String departement;

	private String codePostal;

	private String ville;

}
