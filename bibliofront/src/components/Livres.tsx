import { IconProp } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { Dropdown } from "primereact/dropdown";
import React, { FC, useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import { LIVRES_RESULTAT_PATH } from "../App";
import { Icon } from "../app/Icon";
import { IRayon } from "../app/model/RayonModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { getEntitiesByRayonId as getLivreResultatEntitiesByRayonId } from "../app/store/slice/LivreResultatSlice";
import {
  entities as bibliothequeEntities,
  getEntities as getBibliothequeEntities,
} from "../app/store/slice/BibliothequeSlice";
import { entities as rayonEntities, getEntities as getRayonEntities } from "../app/store/slice/RayonSlice";
import { entities as genreEntities, getEntities as getGenreEntities } from "../app/store/slice/GenreSlice";
import { entities as auteurEntities, getEntities as getAuteurEntities } from "../app/store/slice/AuteurSlice";
import { InputText } from "primereact/inputtext";
import { Calendar } from 'primereact/calendar';

interface Option {
  label: string;
  value: string;
}

export const Livres = () => {
  const dispatch = useAppDispatch();
  const hasBibliotheques = useRef(false);
  const hasRayons = useRef(false);
  const hasGenres = useRef(false);
  const hasAuteurs = useRef(false);
  const history = useHistory();

  useEffect(() => {
    if (!hasBibliotheques.current) {
      dispatch(getBibliothequeEntities());
      hasBibliotheques.current = true;
    }
    if (!hasRayons.current) {
      dispatch(getRayonEntities());
      hasRayons.current = true;
    }
    if (!hasGenres.current) {
      dispatch(getGenreEntities());
      hasGenres.current = true;
    }
    if (!hasAuteurs.current) {
      dispatch(getAuteurEntities());
      hasAuteurs.current = true;
    }
  }, [dispatch]);

  const bibliothequeOptions = useAppSelector(bibliothequeEntities).map(
    (bibliotheque): Option => ({
      label: bibliotheque.nom as string,
      value: bibliotheque.nom as string,
    })
  );
  const rayonOptions = useAppSelector(rayonEntities).map(
    (rayon): Option => ({
      label: rayon.nom as string,
      value: rayon.nom as string,
    })
  );
  const genreOptions = useAppSelector(genreEntities).map(
    (genre): Option => ({
      label: genre.nom as string,
      value: genre.nom as string,
    })
  );
  const auteurOptions = useAppSelector(auteurEntities).map(
    (auteur): Option => ({
      label: auteur.prenomNom as string,
      value: auteur.prenomNom as string,
    })
  );
  const handleSelectedRayon = (rayon: IRayon): void => {
    dispatch(getLivreResultatEntitiesByRayonId(rayon.id as number));
    history.push(LIVRES_RESULTAT_PATH);
  };

  return (
    <div className="p-grid">
      <div className="p-col-12">
        <Card title="Rayons">
          <div className="p-grid p-nogutter">
            {useAppSelector(rayonEntities).map((rayon) => (
              <div className="p-col-4" key={rayon.id}>
                <Button
                  className="p-button-text p-button-plain"
                  label={rayon.nom}
                  style={{ display: "block", height: "100%", width: "100%" }}
                  onClick={() => handleSelectedRayon(rayon)}
                />
              </div>
            ))}
          </div>
        </Card>
      </div>
      <div className="p-col-12">
        <Card title="Recherche avancée">
          <div className="p-fluid p-formgrid p-grid">
            <div className="p-field p-col-4">
              <label htmlFor="bibliotheque">Bibliothèque</label>
              <Dropdown options={bibliothequeOptions} optionLabel="label" placeholder="Tous" onChange={(e) => ""} />
            </div>
            <div className="p-field p-col-4">
              <label htmlFor="rayon">Rayon</label>
              <Dropdown
                options={rayonOptions}
                filter
                showClear
                filterBy="label"
                placeholder="Tous"
                onChange={(e) => ""}
              />
            </div>
            <div className="p-field p-col-4">
              <label htmlFor="genre">Genre</label>
              <Dropdown
                options={genreOptions}
                filter
                showClear
                filterBy="label"
                placeholder="Tous"
                onChange={(e) => ""}
              />
            </div>
            <div className="p-field p-col-4">
              <label htmlFor="titre">Titre</label>
              <InputText />
            </div>
            <div className="p-field p-col-4">
              <label htmlFor="rayon">Auteur</label>
              <Dropdown
                options={auteurOptions}
                filter
                showClear
                filterBy="label"
                placeholder="Tous"
                onChange={(e) => ""}
              />
            </div>
            <div className="p-field p-col-4">
              <label htmlFor="genre">Date de parution</label>
              <Calendar id="range" onChange={(e) => ""} selectionMode="range" readOnlyInput />
            </div>
          </div>
        </Card>
      </div>
    </div>
  );
};
