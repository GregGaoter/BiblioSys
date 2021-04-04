package com.dsi.bibliosys.biblioback.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Identifiant;

public class IdentifiantSpecification {

	public static Specification<Identifiant> emailEqual(String email) {
		return (root, query, builder) -> {
			return email == null ? null : builder.equal(root.get(Identifiant.EMAIL), email);
		};
	}

}
