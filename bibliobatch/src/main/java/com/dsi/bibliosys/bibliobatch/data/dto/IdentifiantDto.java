package com.dsi.bibliosys.bibliobatch.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IdentifiantDto {

	private Integer id;

	private String email;

	private String motDePasse;

	private Boolean isActif;

}
