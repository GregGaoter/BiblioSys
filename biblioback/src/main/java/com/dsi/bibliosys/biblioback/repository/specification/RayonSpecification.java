package com.dsi.bibliosys.biblioback.repository.specification;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Rayon;

public class RayonSpecification {

	public static Specification<Rayon> idEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Rayon.ID), id);
		};
	}

	public static Specification<Rayon> nomEqual(String nom) {
		return (root, query, builder) -> {
			return Objects.isNull(nom) || nom.strip().length() == 0 ? null : builder.equal(root.get(Rayon.NOM), nom);
		};
	}

}
