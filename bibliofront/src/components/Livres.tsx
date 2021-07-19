import { Button } from "primereact/button";
import { Calendar } from "primereact/calendar";
import { Card } from "primereact/card";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import React, { useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import { LIVRES_RESULTAT_PATH } from "../App";
import { IRayon } from "../app/model/RayonModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { entities as auteurEntities, getEntities as getAuteurEntities } from "../app/store/slice/AuteurSlice";
import {
  entities as bibliothequeEntities,
  getEntities as getBibliothequeEntities,
} from "../app/store/slice/BibliothequeSlice";
import { entities as genreEntities, getEntities as getGenreEntities } from "../app/store/slice/GenreSlice";
import { getEntitiesByRayonId as getLivreResultatEntitiesByRayonId } from "../app/store/slice/LivreResultatSlice";
import { entities as rayonEntities, getEntities as getRayonEntities } from "../app/store/slice/RayonSlice";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import Constants from "../app/Constants";

interface Option {
  label: string;
  value: string;
}

interface Form {
  bibliothequeNom: string;
  rayonNom: string;
  genreNom: string;
  livreTitre: string;
  livreAuteur: string;
  livreDateParution: string;
}

export const Livres = () => {
  const dispatch = useAppDispatch();
  const hasBibliotheques = useRef(false);
  const hasRayons = useRef(false);
  const hasGenres = useRef(false);
  const hasAuteurs = useRef(false);
  const history = useHistory();

  const { register, handleSubmit, control } = useForm<Form>();

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

  const bibliothequeOptions = [
    "",
    ...useAppSelector(bibliothequeEntities).map(
      (bibliotheque): Option => ({
        label: bibliotheque.nom as string,
        value: bibliotheque.nom as string,
      })
    ),
  ];
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
    dispatch(getLivreResultatEntitiesByRayonId(rayon.id as number, 0, Constants.LIVRES_RESULTAT_PAGE_SIZE));
    history.push(LIVRES_RESULTAT_PATH);
  };

  const onSubmit: SubmitHandler<Form> = (data) => console.log(data);

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
          <form onSubmit={handleSubmit(onSubmit)}>
              <div className="p-fluid p-formgrid p-grid">
                <div className="p-field p-col-4">
                  <label htmlFor="bibliothequeNom">Bibliothèque</label>
                  <Controller
                    name="bibliothequeNom"
                    control={control}
                    defaultValue=""
                    render={({ field }) => <Dropdown {...field} options={bibliothequeOptions} optionLabel="label" />}
                  />
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
              <Button className="p-mt-2" type="submit" icon="pi pi-search" label="Rechercher" />
          </form>
        </Card>
      </div>
    </div>
  );
};
