package com.dsi.bibliosys.biblioback.data.authentification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe qui contient un nom d'utilisateur et un mot de passe pour obtenir les
 * données d'une requête dans une méthode d'authentification.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequete implements Serializable {

	private static final long serialVersionUID = 719052187681067085L;

	private String username;
	private String password;

}
