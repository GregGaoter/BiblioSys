import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { Menu } from "primereact/menu";
import { TieredMenu } from "primereact/tieredmenu";
import React, { useRef } from "react";
import { useHistory } from "react-router";
import { Route, Switch } from "react-router-dom";
import "./App.css";
import { Accueil } from "./components/Accueil";
import { Bibliotheques } from "./components/Bibliotheques";
import { Livres } from "./components/Livres";
import logo32 from "./ressources/images/logo-32-32.png";
import logo48 from "./ressources/images/logo-48-48.png";

function App(props: any) {
  const avatarMenuRef = useRef(null);
  const history = useHistory();

  const ROOT_PATH = "/";
  const BIBLIOTHEQUES_PATH = "/bibliotheques";
  const LIVRES_PATH = "/livres";

  const routers = [
    { path: ROOT_PATH, component: Accueil, exact: true },
    { path: BIBLIOTHEQUES_PATH, component: Bibliotheques },
    { path: LIVRES_PATH, component: Livres },
  ];

  const goToPath = (path: string): void => {
    history.push(path);
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
        goToPath(ROOT_PATH);
      },
    },
    {
      label: "Nos bibliothèques",
      icon: "pi pi-map",
      command: () => {
        goToPath(BIBLIOTHEQUES_PATH);
      },
    },
    { label: "Nos Livres", icon: "pi pi-book" },
    { separator: true },
    {
      label: "Littérature & Fiction",
      items: [
        { label: "Poésie" },
        { label: "Théatre" },
        { label: "Récit de voyage" },
        { label: "Roman historique" },
        { label: "Roman sentimental" },
      ],
    },
    { label: "Policier & Science-Fiction", items: [{ label: "Roman policier" }, { label: "Science fiction" }] },
    {
      label: "Bandes dessinées, comics & mangas, humour",
      items: [{ label: "Bande dessinée" }, { label: "Comic" }, { label: "Humour" }, { label: "Manga" }],
    },
    {
      label: "Développement durable & Écologie",
      items: [{ label: "Environnement" }, { label: "Société et politique" }, { label: "Ecologie" }],
    },
    {
      label: "Nature, animaux",
      items: [
        { label: "Nature" },
        { label: "Jardinage" },
        { label: "Mer" },
        { label: "Montagne" },
        { label: "Animaux" },
        { label: "Chasse et pêche" },
      ],
    },
    { label: "Loisirs, jeux", items: [{ label: "Bricolage" }, { label: "Jeux" }] },
    {
      label: "Santé, diététique, sport",
      items: [{ label: "Santé" }, { label: "Diététique et beauté" }, { label: "Sport" }],
    },
    { label: "Bien-être, dans sa tête et dans son corps", items: [{ label: "Développement personnel" }] },
    { label: "Cuisine, vins & boissons", items: [{ label: "Cuisine" }, { label: "Vin, alcool, boisson" }] },
    {
      label: "Tourisme, voyages, géographie",
      items: [
        { label: "Afrique" },
        { label: "Amériques" },
        { label: "Asie" },
        { label: "Europe" },
        { label: "Océanie et pacifique" },
        { label: "Proche et moyen orient" },
        { label: "Carte, plan, atlas" },
        { label: "Géographie" },
      ],
    },
    {
      label: "Arts & spectacles",
      items: [
        { label: "Beaux arts" },
        { label: "Cinéma et télévision" },
        { label: "Photographie et vidéo" },
        { label: "Spectacle et musique" },
        { label: "Art décoratif" },
        { label: "Mode" },
      ],
    },
    {
      label: "Histoire, actualité",
      items: [
        { label: "Histoire de france" },
        { label: "Histoire de Suisse" },
        { label: "Histoire internationale" },
        { label: "Sciences politiques et géopolitiques" },
        { label: "Actualité médiatique et politique" },
      ],
    },
    {
      label: "Religions, spiritualité, ésotérisme",
      items: [{ label: "Religion et spiritualité" }, { label: "Esotérisme et art divinatoire" }],
    },
    {
      label: "Sciences humaines et sociales",
      items: [{ label: "Philosophie" }, { label: "Sociologie" }, { label: "Ethnologie et anthropologie" }],
    },
    {
      label: "Psychologie, psychanalyse, pédagogie",
      items: [{ label: "Psychologie" }, { label: "Psychanalyse" }, { label: "Pédagogie" }],
    },
    { label: "Sciences, techniques, médecine", items: [{ label: "Sciences" }, { label: "Médecine" }] },
    {
      label: "Informatique & multimédia",
      items: [
        { label: "Informatique et entreprise" },
        { label: "Intelligence artificielle" },
        { label: "Internet" },
        { label: "Langage informatique" },
        { label: "Multimédia" },
        { label: "Progiciel" },
        { label: "Programmation" },
        { label: "Réseaux et télécommunication" },
        { label: "Système d'exploitation" },
      ],
    },
    {
      label: "Droit, économie, gestion, comptabilité",
      items: [{ label: "Droit" }, { label: "Economie" }, { label: "Gestion" }, { label: "Comptabilité" }],
    },
    {
      label: "Scolaire, parascolaire",
      items: [
        { label: "Maternelle, parascolaire" },
        { label: "Enseignement primaire" },
        { label: "Enseignement primaire, parascolaire" },
        { label: "Enseignement secondaire 1er cycle" },
        { label: "Collège, parascolaire" },
        { label: "Enseignement secondaire 2ème cycle" },
        { label: "Lycée, parascolaire" },
        { label: "Enseignement professionnel" },
        { label: "Technique, parascolaire" },
        { label: "BTS" },
        { label: "BTS, parascolaire" },
      ],
    },
  ];

  const avatarMenuItems = [
    { label: "Mon compte", icon: "pi pi-user" },
    { label: "Mes emprunts", icon: "pi pi-book" },
    { separator: true },
    { label: "Se déconnecter", icon: "pi pi-sign-out" },
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
            <Card className="p-shadow-6 p-mx-4 p-mt-3 p-mb-4">
              <Switch>
                {routers.map((route) => (
                  <Route exact={route.exact} path={route.path} component={route.component} key={route.path} />
                ))}
              </Switch>
            </Card>
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
              <div>© DSI - 2021</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
