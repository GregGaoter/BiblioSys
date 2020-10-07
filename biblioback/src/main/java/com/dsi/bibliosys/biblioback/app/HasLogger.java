package com.dsi.bibliosys.biblioback.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HasLogger est une interface qui fournit le loggin à la classe qui
 * l'implémente.
 */
public interface HasLogger {

	/**
	 * Logger.
	 * 
	 * @return Le Logger pour la classe qui implémente l'interface.
	 */
	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}

}
