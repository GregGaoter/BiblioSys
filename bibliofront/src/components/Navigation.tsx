import { Button } from "primereact/button";
import { Menubar } from "primereact/menubar";
import React from "react";
import { useHistory } from "react-router-dom";
import { BIBLIOTHEQUES_PATH } from "../App";
import logo48 from "../ressources/images/logo-48-48.png";

export const Navigation = () => {
  const history = useHistory();

  const goToPath = (path: string): void => {
    history.push(path);
  };

  const items = [
    {
      label: "BIBLIOTHÈQUES",
      command: () => {
        goToPath(BIBLIOTHEQUES_PATH);
      },
    },
    { label: "SERVICES" },
    { label: "GALLERIE" },
    { label: "ÉQUIPE" },
    { label: "CONTACT" },
  ];

  return <Menubar model={items} start={start} end={end} />;
};

const start = () => <img alt="logo" src={logo48} className="p-mr-4"></img>;

const end = () => (
  <div className="p-d-flex">
    <Button className="p-button-raised p-button-rounded p-mr-2" label="Se connecter" icon="pi pi-sign-in" />
    <Button
      className="p-button-outlined p-button-raised p-button-rounded p-button-secondary"
      label="Créer un compte"
      icon="pi pi-user-plus"
    />
  </div>
);
