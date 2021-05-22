export interface IBibliotheque {
  id?: number;
  nom?: string;
  adresseId?: number;
  description?: string;
  imageFileName?: string;
}

export const defaultValue: Readonly<IBibliotheque> = {};
