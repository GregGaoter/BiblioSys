package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationDto {

	private String prenom;
	private String nom;
	private String dateNaissance;
	private Integer numeroRue;
	private String rue;
	private String codePostal;
	private String ville;
	private String username;
	private String password;

}
