export interface IContact {
  address?: string;
  phone?: string;
  email?: string;
  facebook?: string;
  twitter?: string;
  youtube?: string;
}

export const defaultValue: Readonly<IContact> = {};
