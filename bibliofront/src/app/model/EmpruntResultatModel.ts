export interface IEmpruntResultat {
  pretId: number;
  bibliotheque: string;
  titre: string;
  auteursPrenomNom: string[];
  dateRetour: Date;
  prolongations: number;
  relances: number;
}
