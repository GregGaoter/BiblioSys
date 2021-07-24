export interface IEmpruntResultat {
  bibliotheque: string;
  titre: string;
  auteursPrenomNom: string[];
  dateRetour: Date;
  prolongations: number;
  relances: number;
}
