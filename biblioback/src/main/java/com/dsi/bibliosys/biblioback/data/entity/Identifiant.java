package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Identifiant implements Serializable {

	private static final long serialVersionUID = -4083166832802171328L;

	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String MOT_DE_PASSE = "motDePasse";
	public static final String IS_ACTIF = "isActif";
	public static final String[] FIELDS = { ID, EMAIL, MOT_DE_PASSE, IS_ACTIF };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String motDePasse;

	@Column(nullable = false)
	private Boolean isActif;

	@OneToOne(mappedBy = "identifiant")
	private Usager usager;
	
	@OneToOne(mappedBy = "identifiant")
	private Personnel personnel;

	@OneToMany(mappedBy = Profil.IDENTIFIANT, cascade = CascadeType.ALL)
	private List<Profil> profils;

}
