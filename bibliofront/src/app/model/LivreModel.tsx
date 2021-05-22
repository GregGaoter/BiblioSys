export interface ILivre {
  id?: number;
  bibliothequeId?: number;
  genreId?: number;
  nomImage?: string;
  titre?: string;
  editeurId?: number;
  collectionId?: number;
  dateParution?: string;
  dimension?: string;
  nbPages?: number;
  ean13?: string;
  nbExemplaires?: number;
  resume?: string;
}

export const defaultValue: Readonly<ILivre> = {};
