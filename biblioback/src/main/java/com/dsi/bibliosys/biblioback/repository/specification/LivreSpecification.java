package com.dsi.bibliosys.biblioback.repository.specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.app.Utils;
import com.dsi.bibliosys.biblioback.data.dto.LivreSearchCriteriasDto;
import com.dsi.bibliosys.biblioback.data.entity.Auteur;
import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;
import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;
import com.dsi.bibliosys.biblioback.data.entity.Genre;
import com.dsi.bibliosys.biblioback.data.entity.Livre;
import com.dsi.bibliosys.biblioback.data.entity.Rayon;

public class LivreSpecification {

	public static Specification<Livre> bibliothequeIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Livre.BIBLIOTHEQUE), id);
		};
	}

	public static Specification<Livre> bibliothequeNomEqual(String bibliothequeNom) {
		return (root, query, builder) -> {
			if (Objects.isNull(bibliothequeNom) || bibliothequeNom.strip().length() == 0) {
				return null;
			}

			Subquery<Integer> subqueryBibliotheque = query.subquery(Integer.class);
			Root<Bibliotheque> rootBibliotheque = subqueryBibliotheque.from(Bibliotheque.class);

			subqueryBibliotheque.select(rootBibliotheque.get(Bibliotheque.ID)).where(
					BibliothequeSpecification.nomEqual(bibliothequeNom).toPredicate(rootBibliotheque, query, builder));
			query.where(root.get(Livre.BIBLIOTHEQUE).in(subqueryBibliotheque));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> rayonIdEqual(Integer id) {
		return (root, query, builder) -> {
			if (Objects.isNull(id)) {
				return null;
			}

			Subquery<Integer> subqueryRayon = query.subquery(Integer.class);
			Root<Rayon> rootRayon = subqueryRayon.from(Rayon.class);
			Subquery<Integer> subqueryGenre = query.subquery(Integer.class);
			Root<Genre> rootGenre = subqueryRayon.from(Genre.class);

			subqueryRayon.select(rootRayon.get(Rayon.ID))
					.where(RayonSpecification.idEqual(id).toPredicate(rootRayon, query, builder));
			subqueryGenre.select(rootGenre.get(Genre.RAYON)).where(rootGenre.get(Genre.RAYON).in(subqueryRayon));
			query.where(root.get(Livre.GENRE).in(subqueryGenre));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> rayonNomEqual(String rayonNom) {
		return (root, query, builder) -> {
			if (Objects.isNull(rayonNom) || rayonNom.strip().length() == 0) {
				return null;
			}

			Subquery<Integer> subqueryRayon = query.subquery(Integer.class);
			Root<Rayon> rootRayon = subqueryRayon.from(Rayon.class);
			Subquery<Integer> subqueryGenre = query.subquery(Integer.class);
			Root<Genre> rootGenre = subqueryGenre.from(Genre.class);

			subqueryRayon.select(rootRayon.get(Rayon.ID))
					.where(RayonSpecification.nomEqual(rayonNom).toPredicate(rootRayon, query, builder));
			subqueryGenre.select(rootGenre.get(Genre.RAYON)).where(rootGenre.get(Genre.RAYON).in(subqueryRayon));
			query.where(root.get(Livre.GENRE).in(subqueryGenre));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> genreIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Livre.GENRE), id);
		};
	}

	public static Specification<Livre> genreNomEqual(String genreNom) {
		return (root, query, builder) -> {
			if (Objects.isNull(genreNom) || genreNom.strip().length() == 0) {
				return null;
			}

			Subquery<Integer> subqueryGenre = query.subquery(Integer.class);
			Root<Genre> rootGenre = subqueryGenre.from(Genre.class);

			subqueryGenre.select(rootGenre.get(Genre.ID))
					.where(GenreSpecification.nomEqual(genreNom).toPredicate(rootGenre, query, builder));
			query.where(root.get(Livre.GENRE).in(subqueryGenre));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> titreContaining(String titre) {
		return (root, query, builder) -> {
			return Objects.isNull(titre) || titre.strip().length() == 0 ? null
					: builder.like(builder.function("unaccent", String.class, builder.lower(root.get(Livre.TITRE))),
							"%" + Utils.stringNormalize(titre) + "%");
		};
	}

	public static Specification<Livre> auteurIdEqual(Integer id) {
		return (root, query, builder) -> {
			if (Objects.isNull(id)) {
				return null;
			}

			Subquery<Integer> subqueryEcritureLivre = query.subquery(Integer.class);
			Root<EcritureLivre> rootEcritureLivre = subqueryEcritureLivre.from(EcritureLivre.class);

			subqueryEcritureLivre.select(rootEcritureLivre.get(EcritureLivre.LIVRE))
					.where(EcritureLivreSpecification.auteurIdEqual(id).toPredicate(rootEcritureLivre, query, builder));
			query.where(root.get(Livre.ID).in(subqueryEcritureLivre));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> auteurPrenomNomEqual(String auteurPrenomNom) {
		return (root, query, builder) -> {
			if (Objects.isNull(auteurPrenomNom) || auteurPrenomNom.strip().length() == 0) {
				return null;
			}

			Subquery<Integer> subqueryAuteur = query.subquery(Integer.class);
			Root<Auteur> rootAuteur = subqueryAuteur.from(Auteur.class);
			Subquery<Integer> subqueryEcritureLivre = query.subquery(Integer.class);
			Root<EcritureLivre> rootEcritureLivre = subqueryEcritureLivre.from(EcritureLivre.class);

			subqueryAuteur.select(rootAuteur.get(Auteur.ID))
					.where(AuteurSpecification.prenomNomEqual(auteurPrenomNom).toPredicate(rootAuteur, query, builder));
			subqueryEcritureLivre.select(rootEcritureLivre.get(EcritureLivre.LIVRE))
					.where(rootEcritureLivre.get(EcritureLivre.AUTEUR).in(subqueryAuteur));
			query.where(root.get(Livre.ID).in(subqueryEcritureLivre));

			return query.getRestriction();
		};
	}

	public static Specification<Livre> dateParutionBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
		return (root, query, builder) -> {
			return Objects.isNull(dateStart) || Objects.isNull(dateEnd) || !dateStart.isBefore(dateEnd) ? null
					: builder.between(root.get(Livre.DATE_PARUTION), dateStart, dateEnd);
		};
	}

	public static Specification<Livre> searchCriteriasEqual(LivreSearchCriteriasDto criterias) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<LocalDateTime> datesParution = criterias.getLivreDateParution().length == 0 ? Arrays.asList(null, null)
				: Arrays.stream(criterias.getLivreDateParution())
						.map(date -> LocalDateTime.parse(date.replace("Z", ""))).sorted().collect(Collectors.toList());

		return Specification.where(bibliothequeNomEqual(criterias.getBibliothequeNom()))
				.and(rayonNomEqual(criterias.getRayonNom())).and(genreNomEqual(criterias.getGenreNom()))
				.and(titreContaining(criterias.getLivreTitre())).and(auteurPrenomNomEqual(criterias.getLivreAuteur()))
				.and(dateParutionBetween(datesParution.get(0), datesParution.get(1)));
	}

}
