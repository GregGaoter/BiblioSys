package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;

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
public class EcritureLivre implements Serializable {

	private static final long serialVersionUID = -6141243436652657915L;

	public static final String ID = "id";
	public static final String LIVRE = "livre";
	public static final String AUTEUR = "auteur";
	public static final String[] FIELDS = { ID, LIVRE, AUTEUR };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "livre_id", nullable = false)
	private Livre livre;

	@ManyToOne
	@JoinColumn(name = "auteur_id", nullable = false)
	private Auteur auteur;

}
