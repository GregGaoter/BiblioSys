package com.dsi.bibliosys.biblioback.repository.specification;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Genre;

public class GenreSpecification {

	public static Specification<Genre> rayonIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Genre.RAYON), id);
		};
	}

	public static Specification<Genre> nomEqual(String nom) {
		return (root, query, builder) -> {
			return Objects.isNull(nom) || nom.strip().length() == 0 ? null : builder.equal(root.get(Genre.NOM), nom);
		};
	}

}
