package com.dsi.bibliosys.biblioback.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Collection implements Serializable {

	private static final long serialVersionUID = 207464660480005858L;

	public static final String ID = "id";
	public static final String NOM = "nom";
	public static final String[] FIELDS = { ID, NOM };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	private String nom;

}
