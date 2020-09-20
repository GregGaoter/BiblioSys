package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;

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
public class Adresse implements Serializable {

	private static final long serialVersionUID = 3348389607292879440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private int id;

	@Column(nullable = false)
	private int numeroRue;

	@Column(nullable = false)
	private String rue;

	@ManyToOne
	@JoinColumn(name = "lieu_id", nullable = false)
	private Lieu lieu;

}
