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
