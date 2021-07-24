import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faBrain,
  faChartLine,
  faCogs,
  faDragon,
  faEye,
  faGamepad,
  faGlobeEurope,
  faGraduationCap,
  faLaptopCode,
  faLeaf,
  faMonument,
  faPalette,
  faPaw,
  faPrayingHands,
  faRobot,
  faRunning,
  faTheaterMasks,
  faUserFriends,
  faUtensils,
  faYinYang,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Button } from "primereact/button";
import { Menu } from "primereact/menu";
import { TieredMenu } from "primereact/tieredmenu";
import React, { useEffect, useRef } from "react";
import { useHistory } from "react-router";
import { Switch } from "react-router-dom";
import "./App.css";
import Constants from "./app/Constants";
import { IGenre } from "./app/model/GenreModel";
import { useAppDispatch, useAppSelector } from "./app/store/hooks";
import { entities as genreEntities, getEntities as getGenreEntities } from "./app/store/slice/GenreSlice";
import { getEntitiesByGenreId as getLivreResultatEntitiesByGenreId } from "./app/store/slice/LivreResultatSlice";
import { entities as rayonEntities, getEntities as getRayonEntities } from "./app/store/slice/RayonSlice";
import { Accueil } from "./components/Accueil";
import { Bibliotheques } from "./components/Bibliotheques";
import { Compte } from "./components/Compte";
import { Emprunts } from "./components/Emprunts";
import { Livres } from "./components/Livres";
import { LivresResultat } from "./components/LivresResultat";
import { PrivateRoute } from "./PrivateRoute";
import logo32 from "./ressources/images/logo-32-32.png";
import logo48 from "./ressources/images/logo-48-48.png";

library.add(
  faTheaterMasks,
  faRobot,
  faDragon,
  faLeaf,
  faPaw,
  faGamepad,
  faRunning,
  faYinYang,
  faUtensils,
  faGlobeEurope,
  faPalette,
  faMonument,
  faPrayingHands,
  faUserFriends,
  faBrain,
  faCogs,
  faLaptopCode,
  faChartLine,
  faGraduationCap,
  faEye
);

export const ROOT_PATH = "/";
export const ACCUEIL_PATH = "/accueil";
export const BIBLIOTHEQUES_PATH = "/bibliotheques";
export const LIVRES_PATH = "/livres";
export const LIVRES_RESULTAT_PATH = "/livres/resultat";
export const COMPTE_PATH = "/compte";
export const EMPRUNTS_PATH = "/emprunts";

function App() {
  const dispatch = useAppDispatch();
  const avatarMenuRef = useRef(null);
  const history = useHistory();
  const hasMenuItems = useRef(false);

  useEffect(() => {
    if (!hasMenuItems.current) {
      dispatch(getRayonEntities());
      dispatch(getGenreEntities());
      hasMenuItems.current = true;
    }
  }, [dispatch]);

  const routers = [
    { path: ACCUEIL_PATH, component: Accueil, exact: true },
    { path: BIBLIOTHEQUES_PATH, component: Bibliotheques, exact: true },
    { path: LIVRES_PATH, component: Livres, exact: true },
    { path: LIVRES_RESULTAT_PATH, component: LivresResultat, exact: true },
    { path: COMPTE_PATH, component: Compte, exact: true },
    { path: EMPRUNTS_PATH, component: Emprunts, exact: true },
  ];

  const goToPath = (path: string): void => {
    history.push(path);
  };

  const logout = (): void => {
    localStorage.clear();
    history.push(ROOT_PATH);
  };

  const genres = useAppSelector(genreEntities);

  const rayonGenreMenuItems = useAppSelector(rayonEntities).map((rayon) => ({
    label: rayon.nom,
    items: genres
      .filter((genre) => genre.rayonId === rayon.id)
      .map((genre) => ({
        label: genre.nom,
        command: () => {
          handleSelectedGenre(genre);
        },
      })),
  }));

  const handleSelectedGenre = (genre: IGenre): void => {
    dispatch(getLivreResultatEntitiesByGenreId(genre.id as number, 0, Constants.LIVRES_RESULTAT_PAGE_SIZE));
    history.push(LIVRES_RESULTAT_PATH);
  };

  const tieredMenuItems = [
    {
      template: (item: any, options: any) => {
        return (
          <div className="p-d-flex p-jc-center p-ai-center">
            <img
              className="p-mr-2"
              alt="logo"
              src={logo48}
              style={{
                width: "48px",
                paddingTop: "1px",
                paddingBottom: "1px",
              }}
            />
            <h5>Biblillonie</h5>
          </div>
        );
      },
    },
    { separator: true },
    {
      label: "Accueil",
      icon: "pi pi-home",
      command: () => {
        goToPath(ACCUEIL_PATH);
      },
    },
    {
      label: "Nos bibliothèques",
      icon: "pi pi-map",
      command: () => {
        goToPath(BIBLIOTHEQUES_PATH);
      },
    },
    {
      label: "Nos Livres",
      icon: "pi pi-book",
      command: () => {
        goToPath(LIVRES_PATH);
      },
    },
    { separator: true },
    ...rayonGenreMenuItems,
  ];

  const avatarMenuItems = [
    {
      label: "Mon compte",
      icon: "pi pi-user",
      command: () => {
        goToPath(COMPTE_PATH);
      },
    },
    {
      label: "Mes emprunts",
      icon: "pi pi-book",
      command: () => {
        goToPath(EMPRUNTS_PATH);
      },
    },
    { separator: true },
    {
      label: "Se déconnecter",
      icon: "pi pi-sign-out",
      command: () => {
        logout();
      },
    },
  ];

  return (
    <div className="p-grid nested-grid p-nogutter">
      <div className="p-col-fixed" style={{ width: "200px" }}>
        <TieredMenu model={tieredMenuItems} className="p-shadow-6" />
      </div>
      <div className="p-col">
        <div className="p-grid p-nogutter">
          <div className="p-col-12">
            <div className="p-d-flex">
              <Button
                icon="pi pi-user"
                className="p-button-rounded p-shadow-6 p-ml-auto p-mr-4 p-mt-1"
                onClick={(event) => (avatarMenuRef as any).current.toggle(event)}
              />
              <Menu model={avatarMenuItems} popup ref={avatarMenuRef} id="avatar-menu-popup" />
            </div>
          </div>
          <div className="p-col-12">
            <div className="p-mx-4 p-mt-3 p-mb-4">
              <Switch>
                {routers.map((route) => (
                  <PrivateRoute exact={route.exact} path={route.path} component={route.component} key={route.path} />
                ))}
              </Switch>
            </div>
          </div>
          <div className="p-col-12">
            <div className="p-d-flex p-mx-4 p-ai-center p-jc-between">
              <div className="p-d-flex p-ai-center">
                <img
                  className="p-mr-2"
                  alt="logo"
                  src={logo32}
                  style={{
                    width: "32px",
                  }}
                />
                <div>Biblillonie</div>
              </div>
              <div>{`© DSI - ${new Date().getFullYear()}`}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
