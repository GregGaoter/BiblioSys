package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pret implements Serializable {

	private static final long serialVersionUID = 2985854164633876421L;

	public static final String ID = "id";
	public static final String USAGER = "usager";
	public static final String LIVRE = "livre";
	public static final String DATE_PRET = "datePret";
	public static final String NB_PROLONGATIONS = "nbProlongations";
	public static final String NB_RELANCES = "nbRelances";
	public static final String[] FIELDS = { ID, USAGER, LIVRE, DATE_PRET, NB_PROLONGATIONS, NB_RELANCES };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usager_id", nullable = false)
	private Usager usager;

	@ManyToOne
	@JoinColumn(name = "livre_id", nullable = false)
	private Livre livre;

	@Column(nullable = false)
	private LocalDateTime datePret;

	@Column(nullable = false)
	private Integer nbProlongations;

	@Column(nullable = false)
	private Integer nbRelances;

}
