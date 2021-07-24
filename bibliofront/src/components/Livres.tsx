import { Button } from "primereact/button";
import { Calendar } from "primereact/calendar";
import { Card } from "primereact/card";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import React, { useEffect, useRef, useState } from "react";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import { LIVRES_RESULTAT_PATH } from "../App";
import Constants from "../app/Constants";
import { ILivreCriteresRecherche } from "../app/model/LivreCriteresRechercheModel";
import { IRayon } from "../app/model/RayonModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { entities as auteurEntities, getEntities as getAuteurEntities } from "../app/store/slice/AuteurSlice";
import {
  entities as bibliothequeEntities,
  getEntities as getBibliothequeEntities,
} from "../app/store/slice/BibliothequeSlice";
import { entities as genreEntities, getEntities as getGenreEntities } from "../app/store/slice/GenreSlice";
import {
  getEntitiesByRayonId as getLivreResultatEntitiesByRayonId,
  getEntitiesBySearchCriterias as getLivreResultatEntitiesBySearchCriterias,
} from "../app/store/slice/LivreResultatSlice";
import { entities as rayonEntities, getEntities as getRayonEntities } from "../app/store/slice/RayonSlice";

interface Option {
  label: string;
  value: string;
}

export const Livres = () => {
  const dispatch = useAppDispatch();
  const [livreDateParution, setLivreDateParution] = useState<Date | Date[] | undefined>(undefined);
  const hasBibliotheques = useRef(false);
  const hasRayons = useRef(false);
  const hasGenres = useRef(false);
  const hasAuteurs = useRef(false);
  const history = useHistory();

  const { handleSubmit, control } = useForm<ILivreCriteresRecherche>();

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

  const onSubmit: SubmitHandler<ILivreCriteresRecherche> = (data) => {
    let critereDateParution: string[];
    if (!livreDateParution) {
      critereDateParution = [];
    } else if (livreDateParution && !Array.isArray(livreDateParution)) {
      critereDateParution = [livreDateParution.toISOString(), livreDateParution.toISOString()];
    } else {
      critereDateParution = livreDateParution.map((date) => date.toISOString());
    }

    dispatch(
      getLivreResultatEntitiesBySearchCriterias(
        { ...data, livreDateParution: critereDateParution },
        0,
        Constants.LIVRES_RESULTAT_PAGE_SIZE
      )
    );
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
                <Controller
                  name="rayonNom"
                  control={control}
                  defaultValue=""
                  render={({ field }) => (
                    <Dropdown {...field} options={rayonOptions} filter showClear filterBy="label" placeholder="Tous" />
                  )}
                />
              </div>
              <div className="p-field p-col-4">
                <label htmlFor="genre">Genre</label>
                <Controller
                  name="genreNom"
                  control={control}
                  defaultValue=""
                  render={({ field }) => (
                    <Dropdown {...field} options={genreOptions} filter showClear filterBy="label" placeholder="Tous" />
                  )}
                />
              </div>
              <div className="p-field p-col-4">
                <label htmlFor="titre">Titre</label>
                <Controller
                  name="livreTitre"
                  control={control}
                  defaultValue=""
                  render={({ field }) => <InputText {...field} />}
                />
              </div>
              <div className="p-field p-col-4">
                <label htmlFor="rayon">Auteur</label>
                <Controller
                  name="livreAuteur"
                  control={control}
                  defaultValue=""
                  render={({ field }) => (
                    <Dropdown {...field} options={auteurOptions} filter showClear filterBy="label" placeholder="Tous" />
                  )}
                />
              </div>
              <div className="p-field p-col-4">
                <label htmlFor="genre">Date de parution</label>
                <Calendar
                  id="livreDateParution"
                  value={livreDateParution}
                  onChange={(e) => setLivreDateParution(e.value)}
                  selectionMode="range"
                  dateFormat="dd/mm/yy"
                  monthNavigator
                  yearNavigator
                  yearRange={`1900:${new Date().getFullYear()}`}
                  showButtonBar
                  readOnlyInput
                />
                <small>Sélectionnez une plage de dates.</small>
              </div>
            </div>
            <Button className="p-mt-2" type="submit" icon="pi pi-search" label="Rechercher" />
          </form>
        </Card>
      </div>
    </div>
  );
};
