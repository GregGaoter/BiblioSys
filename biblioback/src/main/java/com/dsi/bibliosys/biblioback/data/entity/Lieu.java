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
public class Lieu implements Serializable {

	private static final long serialVersionUID = -8216083815536312424L;

	public static final String ID = "id";
	public static final String REGION = "region";
	public static final String DEPARTEMENT = "departement";
	public static final String CODE_POSTAL = "codePostal";
	public static final String VILLE = "ville";
	public static final String[] FIELDS = { ID, REGION, DEPARTEMENT, CODE_POSTAL, VILLE };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private String region;

	@Column(nullable = false)
	private String departement;

	@Column(nullable = false)
	private String codePostal;

	@Column(nullable = false)
	private String ville;

	@OneToMany(mappedBy = Adresse.LIEU, cascade = CascadeType.ALL)
	private List<Adresse> adresses;

}
