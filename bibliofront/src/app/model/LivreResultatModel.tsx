import { IAuteur } from "./AuteurModel";
import { ILivre } from "./LivreModel";

export interface ILivreResultat {
  livre?: ILivre;
  auteur?: IAuteur;
}

export const defaultValue: Readonly<ILivreResultat> = {};
