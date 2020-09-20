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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pret implements Serializable {

	private static final long serialVersionUID = 2985854164633876421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private int id;

	@ManyToOne
	@JoinColumn(name = "usager_id", nullable = false)
	private Usager usager;

	@ManyToOne
	@JoinColumn(name = "livre_id", nullable = false)
	private Livre livre;

	@Column(nullable = false)
	private LocalDateTime datePret;

	@Column(nullable = false)
	private int nbProlongations;

	@Column(nullable = false)
	private int nbRelances;

}
