import axios from "axios";
import { Card } from "primereact/card";
import React, { useEffect, useRef, useState } from "react";
import { ILivre } from "../app/model/LivreModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import {
  getEntities as getBibliothequeEntities,
  selectEntities as selectBibliothequeEntities,
} from "../app/store/slice/BibliothequeSlice";

export const Bibliotheques = () => {
  const [livres, setLivres] = useState<ILivre[]>([]);
  const bibliotheques = useAppSelector(selectBibliothequeEntities);
  const hasBibliotheques = useRef(false);
  const dispatch = useAppDispatch();

  useEffect(() => {
    axios.get<ILivre[]>("/livre/all").then((res) => setLivres(res.data));
  }, []);

  useEffect(() => {
    if (!hasBibliotheques.current) {
      dispatch(getBibliothequeEntities());
    }
  }, [dispatch]);

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
