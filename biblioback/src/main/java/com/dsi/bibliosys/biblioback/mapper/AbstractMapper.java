package com.dsi.bibliosys.biblioback.mapper;

import com.dsi.bibliosys.biblioback.service.AdresseService;
import com.dsi.bibliosys.biblioback.service.AuteurService;
import com.dsi.bibliosys.biblioback.service.BibliothequeService;
import com.dsi.bibliosys.biblioback.service.CollectionService;
import com.dsi.bibliosys.biblioback.service.EcritureLivreService;
import com.dsi.bibliosys.biblioback.service.EditeurService;
import com.dsi.bibliosys.biblioback.service.FavorisService;
import com.dsi.bibliosys.biblioback.service.GenreService;
import com.dsi.bibliosys.biblioback.service.IdentifiantService;
import com.dsi.bibliosys.biblioback.service.LieuService;
import com.dsi.bibliosys.biblioback.service.LivreService;
import com.dsi.bibliosys.biblioback.service.PersonnelService;
import com.dsi.bibliosys.biblioback.service.PretService;
import com.dsi.bibliosys.biblioback.service.ProfilService;
import com.dsi.bibliosys.biblioback.service.RayonService;
import com.dsi.bibliosys.biblioback.service.RoleService;
import com.dsi.bibliosys.biblioback.service.UsagerService;

/**
 * Clsee abstraite fournissant les attributs des services.
 */
public abstract class AbstractMapper {

	protected AdresseService adresseService;
	protected AuteurService auteurService;
	protected BibliothequeService bibliothequeService;
	protected CollectionService collectionService;
	protected EcritureLivreService ecritureLivreService;
	protected EditeurService editeurService;
	protected FavorisService favorisService;
	protected GenreService genreService;
	protected IdentifiantService identifiantService;
	protected LieuService lieuService;
	protected LivreService livreService;
	protected PersonnelService personnelService;
	protected PretService pretService;
	protected ProfilService profilService;
	protected RayonService rayonService;
	protected RoleService roleService;
	protected UsagerService usagerService;

}
