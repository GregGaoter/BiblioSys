export interface IPret {
  id?: number;
  usagerId?: number;
  livreId?: number;
  datePret?: string;
  nbProlongations?: number;
  nbRelances?: number;
}

export const defaultValue: Readonly<IPret> = {};
