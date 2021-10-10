package com.dsi.bibliosys.bibliobatch.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PretDto {
	
	private Integer id;

	private Integer usagerId;

	private Integer livreId;

	private LocalDateTime datePret;

	private Integer nbProlongations;

	private Integer nbRelances;

}
