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
import javax.persistence.OneToOne;

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
public class Personnel implements Serializable {

	private static final long serialVersionUID = 5341656277537489528L;

	public static final String ID = "id";
	public static final String PRENOM = "prenom";
	public static final String NOM = "nom";
	public static final String DATE_NAISSANCE = "dateNaissance";
	public static final String IDENTIFIANT = "identifiant";
	public static final String ADRESSE = "adresse";
	public static final String BIBLIOTHEQUE = "bibliotheque";
	public static final String[] FIELDS = { ID, PRENOM, NOM, DATE_NAISSANCE, IDENTIFIANT, ADRESSE, BIBLIOTHEQUE };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private String prenom;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private LocalDateTime dateNaissance;

	@OneToOne
	@JoinColumn(name = "identifiant_id", referencedColumnName = "id", nullable = false)
	private Identifiant identifiant;

	@ManyToOne
	@JoinColumn(name = "adresse_id", nullable = false)
	private Adresse adresse;

	@ManyToOne
	@JoinColumn(name = "bibliotheque_id", nullable = false)
	private Bibliotheque bibliotheque;

}
