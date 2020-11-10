package com.dsi.bibliosys.biblioback.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PretDto {

	public static final String ID = "id";
	public static final String USAGER_ID = "usagerId";
	public static final String LIVRE_ID = "livreId";
	public static final String DATE_PRET = "datePret";
	public static final String NB_PROLONGATIONS = "nbProlongations";
	public static final String NB_RELANCES = "nbRelances";
	public static final String[] FIELDS = { ID, USAGER_ID, LIVRE_ID, DATE_PRET, NB_PROLONGATIONS, NB_RELANCES };

	private Integer id;

	private Integer usagerId;

	private Integer livreId;

	private LocalDateTime datePret;

	private Integer nbProlongations;

	private Integer nbRelances;

}
