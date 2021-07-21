package com.dsi.bibliosys.biblioback.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.EcritureLivre;

public class EcritureLivreSpecification {

	public static Specification<EcritureLivre> idEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(EcritureLivre.ID), id);
		};
	}

	public static Specification<EcritureLivre> livreIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(EcritureLivre.LIVRE), id);
		};
	}
	
	public static Specification<EcritureLivre> auteurIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(EcritureLivre.AUTEUR), id);
		};
	}

}
