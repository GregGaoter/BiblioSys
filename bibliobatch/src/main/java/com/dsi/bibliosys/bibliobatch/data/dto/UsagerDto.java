package com.dsi.bibliosys.bibliobatch.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsagerDto {
	
	private Integer id;

	private String prenom;

	private String nom;

	private LocalDateTime dateNaissance;

	private Integer identifiantId;

	private Integer adresseId;

}
