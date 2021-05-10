import axios from "axios";
import { Card } from "primereact/card";
import React, { useEffect, useState } from "react";
import { IBibliotheque } from "../app/shared/model/Bibliotheque";
import { ILivre } from "../app/shared/model/Livre";

export const Bibliotheques = () => {
  const [livres, setLivres] = useState<ILivre[]>([]);
  const [bibliotheques, setBibliotheques] = useState<IBibliotheque[]>([]);

  useEffect(() => {
    axios.get<ILivre[]>("http://localhost:8080/livre/all").then((res) => setLivres(res.data));
    axios.get<IBibliotheque[]>("http://localhost:8080/bibliotheque/all").then((res) => setBibliotheques(res.data));
  }, []);

  return (
    <div className="p-grid">
      {bibliotheques.map((bibliotheque) => {
        const { id, nom, description, imageFileName } = bibliotheque;
        return (
          <div className="p-col" key={id}>
            <Card
              title={nom}
              header={<img alt={nom} src={`/images/${imageFileName}`} />}
              footer={`${livres.filter((livre) => livre.bibliothequeId === id).length} livres`}
            >
              <p>{description}</p>
            </Card>
          </div>
        );
      })}
    </div>
  );
};
