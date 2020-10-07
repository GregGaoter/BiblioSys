package com.dsi.bibliosys.biblioback.app;

import org.apache.commons.lang3.StringUtils;

import com.dsi.bibliosys.biblioback.app.exception.IllegalStringArgumentException;

import lombok.NonNull;

/**
 * Classe abstraite fournissant des méthodes statiques utilitaires pour
 * l'application.
 */
public abstract class Utils {

	/**
	 * Vérifie si le string blank ou empty.
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

}
