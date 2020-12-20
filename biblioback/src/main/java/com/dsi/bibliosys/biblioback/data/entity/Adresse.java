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
public class Adresse implements Serializable {

	private static final long serialVersionUID = 3348389607292879440L;

	public static final String ID = "id";
	public static final String NUMERO_RUE = "numeroRue";
	public static final String RUE = "rue";
	public static final String LIEU = "lieu";
	public static final String[] FIELDS = { ID, NUMERO_RUE, RUE, LIEU };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private Integer numeroRue;

	@Column(nullable = false)
	private String rue;

	@ManyToOne
	@JoinColumn(name = "lieu_id", nullable = false)
	private Lieu lieu;

	@OneToMany(mappedBy = Usager.ADRESSE, cascade = CascadeType.ALL)
	private List<Usager> usagers;

	@OneToMany(mappedBy = Personnel.ADRESSE, cascade = CascadeType.ALL)
	private List<Personnel> personnels;

	@OneToMany(mappedBy = Bibliotheque.ADRESSE, cascade = CascadeType.ALL)
	private List<Bibliotheque> bibliotheques;

}
