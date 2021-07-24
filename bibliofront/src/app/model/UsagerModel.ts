export interface IUsager {
  id?: number;
  prenom?: string;
  nom?: string;
  dateNaissance?: string;
  identifiantId?: number;
  adresseId?: number;
}

export const defaultValue: Readonly<IUsager> = {};
