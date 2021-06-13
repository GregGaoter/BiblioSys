import { IAuteur } from "./AuteurModel";
import { IEditeur } from "./EditeurModel";
import { ILivre } from "./LivreModel";

export interface ILivreResultat {
  livre?: ILivre;
  auteur?: IAuteur;
  editeur?: IEditeur;
}

export const defaultValue: Readonly<ILivreResultat> = {};
