package com.dsi.bibliosys.biblioback.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Pret;
import com.dsi.bibliosys.biblioback.data.entity.Usager;

public class PretSpecification {

	public static Specification<Pret> usagerEqual(Usager usager) {
		return (root, query, builder) -> {
			return usager == null ? null : builder.equal(root.get(Pret.USAGER), usager.getId());
		};
	}

}
