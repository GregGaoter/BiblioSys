export interface IGenre {
  id?: number;
  nom?: string;
  rayonId?: number;
}

export const defaultValue: Readonly<IGenre> = {};
