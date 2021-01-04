package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Usager implements Serializable {

	private static final long serialVersionUID = 173178456745033978L;

	public static final String ID = "id";
	public static final String PRENOM = "prenom";
	public static final String NOM = "nom";
	public static final String DATE_NAISSANCE = "dateNaissance";
	public static final String IDENTIFIANT = "identifiant";
	public static final String ADRESSE = "adresse";
	public static final String[] FIELDS = { ID, PRENOM, NOM, DATE_NAISSANCE, IDENTIFIANT, ADRESSE };

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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "identifiant_id", referencedColumnName = "id", nullable = false)
	private Identifiant identifiant;

	@ManyToOne
	@JoinColumn(name = "adresse_id", nullable = false)
	private Adresse adresse;

	@OneToMany(mappedBy = Favoris.USAGER, cascade = CascadeType.ALL)
	private List<Favoris> favoris;

	@OneToMany(mappedBy = Pret.USAGER, cascade = CascadeType.ALL)
	private List<Pret> prets;

}
