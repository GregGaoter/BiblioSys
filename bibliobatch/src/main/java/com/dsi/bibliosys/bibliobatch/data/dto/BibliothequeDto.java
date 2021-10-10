package com.dsi.bibliosys.bibliobatch.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BibliothequeDto {
	
	private Integer id;

	private String nom;

	private Integer adresseId;

	private String description;

	private String imageFileName;

}
