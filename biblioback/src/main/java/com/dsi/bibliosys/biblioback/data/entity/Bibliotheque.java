package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Bibliotheque implements Serializable {

	private static final long serialVersionUID = 6689960135083120477L;

	public static final String ID = "id";
	public static final String NOM = "nom";
	public static final String ADRESSE = "adresse";
	public static final String[] FIELDS = { ID, NOM, ADRESSE };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private String nom;

	@ManyToOne
	@JoinColumn(name = "adresse_id", nullable = false)
	private Adresse adresse;

	@OneToMany(mappedBy = Personnel.BIBLIOTHEQUE, cascade = CascadeType.ALL)
	private List<Personnel> personnels;

	@OneToMany(mappedBy = Livre.BIBLIOTHEQUE, cascade = CascadeType.ALL)
	private List<Livre> livres;

}
