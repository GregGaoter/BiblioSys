import { Card } from "primereact/card";
import { Menu } from "primereact/menu";
import { Menubar } from "primereact/menubar";
import { TieredMenu } from "primereact/tieredmenu";
import { Button } from "primereact/button";
import React, { useRef } from "react";
import "./App.css";
import { Accueil } from "./components/Accueil";
import { Bibliotheques } from "./components/Bibliotheques";
import { Livres } from "./components/Livres";
import logo from "./ressources/images/logo-48-48.png";
import { Switch, Route } from "react-router-dom";

function App() {
  const avatarMenuRef = useRef(null);

  const routers = [
    { path: "/", component: Accueil, exact: true },
    { path: "/bibliotheques", component: Bibliotheques },
    { path: "/livres", component: Livres },
  ];

  const tieredMenuItems = [
    {
      template: (item: any, options: any) => {
        return (
          <div className="p-d-flex p-jc-center p-ai-center">
            <img
              className="p-mr-2"
              alt="logo"
              src={logo}
              style={{
                width: "40px",
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
    { label: "Accueil", icon: "pi pi-home" },
    { label: "Nos bibliothèques", icon: "pi pi-map" },
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

  const menuBarStart = (
    <div className="p-d-inline-flex p-ai-baseline">
      <i className="pi pi-home p-ml-4 p-mr-3"></i>
      <i className="pi pi-chevron-right p-mr-3"></i>
      <h5>Catégorie</h5>
    </div>
  ) as any;

  const menuBarEnd = (
    <Button label="GG" className="p-button-rounded" onClick={(event) => (avatarMenuRef as any).current.toggle(event)} />
  ) as any;

  const avatarMenuItems = [
    { label: "Mon compte", icon: "pi pi-user" },
    { label: "Mes emprunts", icon: "pi pi-book" },
    { separator: true },
    { label: "Se déconnecter", icon: "pi pi-sign-out" },
  ];

  return (
    <div className="p-d-flex">
      <TieredMenu model={tieredMenuItems} className="p-shadow-6" />
      <div className="p-d-flex p-flex-column">
        <Menu model={avatarMenuItems} popup ref={avatarMenuRef} id="avatar-menu-popup" />
        <Menubar start={menuBarStart} end={menuBarEnd} className="p-as-start p-shadow-6" style={{ width: "90vw" }} />
        <Card className="p-shadow-6 p-ml-3 p-mr-3 p-mt-3">
          <Switch>
            {routers.map((route) => (
              <Route exact={route.exact} path={route.path} component={route.component} key={route.path} />
            ))}
          </Switch>
        </Card>
      </div>
    </div>
  );
}

export default App;
