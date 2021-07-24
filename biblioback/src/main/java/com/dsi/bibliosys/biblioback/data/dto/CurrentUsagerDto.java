package com.dsi.bibliosys.biblioback.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CurrentUsagerDto {

	public static final String USAGER_ID = "usagerId";
	public static final String PRENOM = "prenom";
	public static final String NOM = "nom";
	public static final String DATE_NAISSANCE = "dateNaissance";
	public static final String IDENTIFIANT_ID = "identifiantId";
	public static final String ADRESSE_ID = "adresseId";
	public static final String EMAIL = "email";
	public static final String IS_ACTIF = "isActif";
	public static final String NUMERO_RUE = "numeroRue";
	public static final String RUE = "rue";
	public static final String LIEU_ID = "lieuId";
	public static final String REGION = "region";
	public static final String DEPARTEMENT = "departement";
	public static final String CODE_POSTAL = "codePostal";
	public static final String VILLE = "ville";
	public static final String[] FIELDS = { USAGER_ID, PRENOM, NOM, DATE_NAISSANCE, IDENTIFIANT_ID, ADRESSE_ID, EMAIL,
			IS_ACTIF, NUMERO_RUE, RUE, LIEU_ID, REGION, DEPARTEMENT, CODE_POSTAL, VILLE };

	private Integer usagerId;

	private String prenom;

	private String nom;

	private LocalDateTime dateNaissance;

	private Integer identifiantId;

	private String email;

	private Boolean isActif;

	private Integer adresseId;

	private Integer numeroRue;

	private String rue;

	private Integer lieuId;

	private String region;

	private String departement;

	private String codePostal;

	private String ville;

}
