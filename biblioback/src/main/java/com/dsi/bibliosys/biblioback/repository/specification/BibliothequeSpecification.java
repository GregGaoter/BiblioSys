package com.dsi.bibliosys.biblioback.repository.specification;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Bibliotheque;

public class BibliothequeSpecification {

	public static Specification<Bibliotheque> idEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Bibliotheque.ID), id);
		};
	}

	public static Specification<Bibliotheque> nomEqual(String nom) {
		return (root, query, builder) -> {
			return Objects.isNull(nom) || nom.strip().length() == 0 ? null
					: builder.equal(root.get(Bibliotheque.NOM), nom);
		};
	}

}
