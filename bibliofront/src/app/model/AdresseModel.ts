export interface IAdresse {
  id?: number;
  numeroRue?: number;
  rue?: string;
  lieuId?: number;
}

export const defaultValue: Readonly<IAdresse> = {};
