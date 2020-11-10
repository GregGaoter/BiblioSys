package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfilDto {

	public static final String ID = "id";
	public static final String IDENTIFIANT_ID = "identifiantId";
	public static final String ROLE_ID = "roleId";
	public static final String[] FIELDS = { ID, IDENTIFIANT_ID, ROLE_ID };

	private Integer id;

	private Integer identifiantId;

	private Integer roleId;

}
