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
public class Profil implements Serializable {

	private static final long serialVersionUID = 7667328222741253341L;

	public static final String ID = "id";
	public static final String IDENTIFIANT = "identifiant";
	public static final String ROLE = "role";
	public static final String[] FIELDS = { ID, IDENTIFIANT, ROLE };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "identifiant_id", nullable = false)
	private Identifiant identifiant;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
