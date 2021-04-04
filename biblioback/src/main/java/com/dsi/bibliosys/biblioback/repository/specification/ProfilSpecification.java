package com.dsi.bibliosys.biblioback.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Lieu;
import com.dsi.bibliosys.biblioback.data.entity.Profil;

public class ProfilSpecification {

	public static Specification<Profil> identifiantIdEqual(Integer id) {
		return (root, query, builder) -> {
			return id == null ? null : builder.equal(root.get(Lieu.REGION), id);
		};
	}

}
