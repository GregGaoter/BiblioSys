package com.dsi.bibliosys.biblioback.app;

import static com.dsi.bibliosys.biblioback.app.Constant.DATE_FORMAT_PATTERN;
import static com.dsi.bibliosys.biblioback.app.Constant.PERIODE_PRET_SEMAINES;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import com.dsi.bibliosys.biblioback.app.exception.IllegalStringArgumentException;
import com.dsi.bibliosys.biblioback.data.dto.LivreSearchCriteriasDto;
import com.dsi.bibliosys.biblioback.data.entity.Pret;

import lombok.NonNull;

/**
 * Classe abstraite fournissant des méthodes statiques utilitaires pour
 * l'application.
 */
public abstract class Utils {

	/**
	 * Retourne le nom simple de la classe en argument comme donné dans le code
	 * source. Retourne un String vide si la classe en argument est anonyme.
	 * 
	 * @param type Classe.
	 * @return Le nom simple de la classe en argument.
	 */
	public static final String getClassName(@NonNull Class<?> type) {
		return type.getSimpleName();
	}

	/**
	 * Vérifie si le string est blank ou empty.
	 * 
	 * @param str String à vérifier
	 * @throws IllegalStringArgumentException si le string est blank ou empty.
	 */
	public static final void stringCheck(@NonNull String str) throws IllegalStringArgumentException {
		if (str.strip().length() > 0) {
			throw new IllegalStringArgumentException();
		}
	}

	/**
	 * Convertit un string en lettres minuscules et supprime les signes
	 * diacritiques.
	 * 
	 * @param str Le string à normaliser.
	 * @return Le string en lettres minuscules sans signes diacritiques.
	 */
	public static final String stringNormalize(@NonNull String str) {
		return StringUtils.stripAccents(str.toLowerCase());
	}

	/**
	 * Fournit le header "x-total-count"
	 * 
	 * @param totalCount
	 * @return Le header "x-total-count"
	 */
	public static final HttpHeaders getTotalCountFilterIdHeaders(long totalCount, int filterId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers", "x-total-count, x-filter-id");
		headers.add("x-total-count", String.valueOf(totalCount));
		headers.add("x-filter-id", String.valueOf(filterId));
		return headers;
	}

	public static final HttpHeaders getSearchCriteriasHeaders(long totalCount,
			LivreSearchCriteriasDto livreSearchCriteriasDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers",
				"x-total-count,x-bibliotheque-nom,x-rayon-nom,x-genre-nom,x-livre-titre,x-livre-auteur,x-livre-date-parution-start,x-livre-date-parution-end");
		headers.add("x-total-count", String.valueOf(totalCount));
		headers.add("x-bibliotheque-nom", String.valueOf(livreSearchCriteriasDto.getBibliothequeNom()));
		headers.add("x-rayon-nom", String.valueOf(livreSearchCriteriasDto.getRayonNom()));
		headers.add("x-genre-nom", String.valueOf(livreSearchCriteriasDto.getGenreNom()));
		headers.add("x-livre-titre", String.valueOf(livreSearchCriteriasDto.getLivreTitre()));
		headers.add("x-livre-auteur", String.valueOf(livreSearchCriteriasDto.getLivreAuteur()));
		headers.add("x-livre-date-parution-start", livreSearchCriteriasDto.getLivreDateParution().length == 0 ? null
				: String.valueOf(livreSearchCriteriasDto.getLivreDateParution()[0]));
		headers.add("x-livre-date-parution-end", livreSearchCriteriasDto.getLivreDateParution().length == 0 ? null
				: String.valueOf(livreSearchCriteriasDto.getLivreDateParution()[1]));
		return headers;
	}

	public static final LocalDateTime getDateRetourPret(Pret pret) {
		return pret.getDatePret().plusWeeks(PERIODE_PRET_SEMAINES * (1 + pret.getNbProlongations()));
	}

	public static final String getDateRetourPretFormat(Pret pret) {
		return getDateRetourPret(pret).format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
	}

}
