package com.dsi.bibliosys.biblioback.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.app.Utils;
import com.dsi.bibliosys.biblioback.data.entity.Lieu;

public class LieuSpecification {

	public static Specification<Lieu> regionContaining(String template) {
		return (root, query, builder) -> {
			return template == null ? null
					: builder.like(builder.function("unaccent", String.class, builder.lower(root.get(Lieu.REGION))),
							"%" + Utils.stringNormalize(template) + "%");
		};
	}

	public static Specification<Lieu> regionEqual(String region) {
		return (root, query, builder) -> {
			return region == null ? null : builder.equal(root.get(Lieu.REGION), region);
		};
	}

	public static Specification<Lieu> departementContaining(String template) {
		return (root, query, builder) -> {
			return template == null ? null
					: builder.like(
							builder.function("unaccent", String.class, builder.lower(root.get(Lieu.DEPARTEMENT))),
							"%" + Utils.stringNormalize(template) + "%");
		};
	}

	public static Specification<Lieu> departementEqual(String departement) {
		return (root, query, builder) -> {
			return departement == null ? null : builder.equal(root.get(Lieu.DEPARTEMENT), departement);
		};
	}

	public static Specification<Lieu> codePostalContaining(String template) {
		return (root, query, builder) -> {
			return template == null ? null
					: builder.like(
							builder.function("unaccent", String.class, builder.lower(root.get(Lieu.CODE_POSTAL))),
							"%" + Utils.stringNormalize(template) + "%");
		};
	}

	public static Specification<Lieu> codePostalEqual(String codePostal) {
		return (root, query, builder) -> {
			return codePostal == null ? null : builder.equal(root.get(Lieu.CODE_POSTAL), codePostal);
		};
	}

	public static Specification<Lieu> villeContaining(String template) {
		return (root, query, builder) -> {
			return template == null ? null
					: builder.like(builder.function("unaccent", String.class, builder.lower(root.get(Lieu.VILLE))),
							"%" + Utils.stringNormalize(template) + "%");
		};
	}

	public static Specification<Lieu> villeEqual(String ville) {
		return (root, query, builder) -> {
			return ville == null ? null : builder.equal(root.get(Lieu.VILLE), ville);
		};
	}

}
