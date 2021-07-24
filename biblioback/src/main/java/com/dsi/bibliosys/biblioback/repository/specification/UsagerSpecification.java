package com.dsi.bibliosys.biblioback.repository.specification;

import java.util.Objects;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.dsi.bibliosys.biblioback.data.entity.Identifiant;
import com.dsi.bibliosys.biblioback.data.entity.Usager;

public class UsagerSpecification {

	public static Specification<Usager> emailEqual(String email) {
		return (root, query, builder) -> {
			if (Objects.isNull(email) || email.strip().length() == 0) {
				return null;
			}

			Subquery<Integer> subqueryIdentifiant = query.subquery(Integer.class);
			Root<Identifiant> rootIdentifiant = subqueryIdentifiant.from(Identifiant.class);

			subqueryIdentifiant.select(rootIdentifiant.get(Identifiant.ID))
					.where(IdentifiantSpecification.emailEqual(email).toPredicate(rootIdentifiant, query, builder));
			query.where(root.get(Usager.IDENTIFIANT).in(subqueryIdentifiant));

			return query.getRestriction();
		};
	}

}
