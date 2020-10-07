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
public class Favoris implements Serializable {

	private static final long serialVersionUID = 4297537070516149153L;

	public static final String ID = "id";
	public static final String USAGER = "usager";
	public static final String LIVRE = "livre";
	public static final String[] FIELDS = { ID, USAGER, LIVRE };

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

}
