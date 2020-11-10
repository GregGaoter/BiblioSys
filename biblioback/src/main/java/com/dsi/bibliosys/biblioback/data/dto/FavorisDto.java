package com.dsi.bibliosys.biblioback.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FavorisDto {

	public static final String ID = "id";
	public static final String USAGER_ID = "usagerId";
	public static final String LIVRE_ID = "livreId";
	public static final String[] FIELDS = { ID, USAGER_ID, LIVRE_ID };

	private Integer id;

	private Integer usagerId;

	private Integer livreId;

}
