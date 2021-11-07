import { Button } from "primereact/button";
import { Menubar } from "primereact/menubar";
import React from "react";
import { useHistory } from "react-router-dom";
import {
  BIBLIOTHEQUES_PATH,
  CONTACT_PATH,
  EQUIPE_PATH,
  GALLERIE_PATH,
  HORAIRES_PATH,
  LOGIN_PATH,
  REGISTRATION_PATH,
  ROOT_PATH,
  SERVICES_PATH,
} from "../App";
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
    {
      label: "SERVICES",
      command: () => {
        goToPath(SERVICES_PATH);
      },
    },
    {
      label: "GALLERIE",
      command: () => {
        goToPath(GALLERIE_PATH);
      },
    },
    {
      label: "ÉQUIPE",
      command: () => {
        goToPath(EQUIPE_PATH);
      },
    },
    {
      label: "HORAIRES",
      command: () => {
        goToPath(HORAIRES_PATH);
      },
    },
    {
      label: "CONTACT",
      command: () => {
        goToPath(CONTACT_PATH);
      },
    },
  ];

  const start = () => (
    <img
      alt="logo"
      src={logo48}
      className="p-mr-4"
      style={{ cursor: "pointer" }}
      onClick={() => goToPath(ROOT_PATH)}
    ></img>
  );

  const end = () => (
    <div className="p-d-flex">
      <Button
        className="p-button-raised p-button-rounded p-mr-2"
        label="Se connecter"
        icon="pi pi-sign-in"
        onClick={() => goToPath(LOGIN_PATH)}
      />
      <Button
        className="p-button-outlined p-button-raised p-button-rounded p-button-secondary"
        label="Créer un compte"
        icon="pi pi-user-plus"
        onClick={() => goToPath(REGISTRATION_PATH)}
      />
    </div>
  );

  return <Menubar model={items} start={start} end={end} />;
};
