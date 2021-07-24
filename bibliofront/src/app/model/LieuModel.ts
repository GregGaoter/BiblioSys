export interface ILieu {
  id?: number;
  region?: string;
  departement?: string;
  codePostal?: string;
  ville?: string;
}

export const defaultValue: Readonly<ILieu> = {};
