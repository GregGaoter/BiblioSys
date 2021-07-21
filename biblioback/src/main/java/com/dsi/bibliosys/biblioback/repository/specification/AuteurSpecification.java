package com.dsi.bibliosys.biblioback.repository.specification;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Auteur;

public class AuteurSpecification {

	public static Specification<Auteur> prenomNomEqual(String nom) {
		return (root, query, builder) -> {
			return Objects.isNull(nom) || nom.strip().length() == 0 ? null
					: builder.equal(root.get(Auteur.PRENOM_NOM), nom);
		};
	}

}
