package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdresseDto {

	public static final String ID = "id";
	public static final String NUMERO_RUE = "numeroRue";
	public static final String RUE = "rue";
	public static final String LIEU_ID = "lieuId";
	public static final String[] FIELDS = { ID, NUMERO_RUE, RUE, LIEU_ID };

	private Integer id;

	private Integer numeroRue;

	private String rue;

	private Integer lieuId;

}
