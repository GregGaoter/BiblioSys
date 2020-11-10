package com.dsi.bibliosys.biblioback.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonnelDto {

	public static final String ID = "id";
	public static final String PRENOM = "prenom";
	public static final String NOM = "nom";
	public static final String DATE_NAISSANCE = "dateNaissance";
	public static final String IDENTIFIANT_ID = "identifiantId";
	public static final String ADRESSE_ID = "adresseId";
	public static final String BIBLIOTHEQUE_ID = "bibliothequeId";
	public static final String[] FIELDS = { ID, PRENOM, NOM, DATE_NAISSANCE, IDENTIFIANT_ID, ADRESSE_ID,
			BIBLIOTHEQUE_ID };

	private Integer id;

	private String prenom;

	private String nom;

	private LocalDateTime dateNaissance;

	private Integer identifiantId;

	private Integer adresseId;

	private Integer bibliothequeId;

}
