package com.dsi.bibliosys.biblioback.data.authentification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe qui renvoie le token JWT. Si nous avons besoin d'autres informations à
 * envoyer en réponse, il faut les déclarer ici.
 */
@AllArgsConstructor
@Getter
public class JwtReponse implements Serializable {

	private static final long serialVersionUID = 3206496032489168163L;

	private final String token;

}
