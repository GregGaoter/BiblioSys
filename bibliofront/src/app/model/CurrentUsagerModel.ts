export interface ICurrentUsager {
  usagerId?: number;
  prenom?: string;
  nom?: string;
  dateNaissance?: Date;
  identifiantId?: number;
  email?: string;
  isActif?: boolean;
  adresseId?: number;
  numeroRue?: number;
  rue?: string;
  lieuId?: number;
  region?: string;
  departement?: string;
  codePostal?: string;
  ville?: string;
}

export const defaultValue: Readonly<ICurrentUsager> = {};
