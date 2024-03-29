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
public class Livre implements Serializable {

	private static final long serialVersionUID = -9083385557107533471L;

	public static final String ID = "id";
	public static final String BIBLIOTHEQUE = "bibliotheque";
	public static final String GENRE = "genre";
	public static final String NOM_IMAGE = "nomImage";
	public static final String TITRE = "titre";
	public static final String EDITEUR = "editeur";
	public static final String COLLECTION = "collection";
	public static final String DATE_PARUTION = "dateParution";
	public static final String DIMENSION = "dimension";
	public static final String NB_PAGES = "nbPages";
	public static final String EAN13 = "ean13";
	public static final String NB_EXEMPLAIRES = "nbExemplaires";
	public static final String RESUME = "resume";
	public static final String[] FIELDS = { ID, BIBLIOTHEQUE, GENRE, NOM_IMAGE, TITRE, EDITEUR, COLLECTION,
			DATE_PARUTION, DIMENSION, NB_PAGES, EAN13, NB_EXEMPLAIRES, RESUME };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "bibliotheque_id", nullable = false)
	private Bibliotheque bibliotheque;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	private String nomImage;

	private String titre;

	@ManyToOne
	@JoinColumn(name = "editeur_id", nullable = false)
	private Editeur editeur;

	@ManyToOne
	@JoinColumn(name = "collection_id", nullable = false)
	private Collection collection;

	private LocalDateTime dateParution;

	private String dimension;

	@Column(nullable = true)
	private Integer nbPages;

	private String ean13;

	@Column(nullable = false)
	private Integer nbExemplaires;

	@Column(length = 10000)
	private String resume;

	@OneToMany(mappedBy = EcritureLivre.LIVRE, cascade = CascadeType.ALL)
	private List<EcritureLivre> ecritureLivres;

	@OneToMany(mappedBy = Pret.LIVRE, cascade = CascadeType.ALL)
	private List<Pret> prets;

	@OneToMany(mappedBy = Favoris.LIVRE, cascade = CascadeType.ALL)
	private List<Favoris> favoris;

}
