import { Button } from "primereact/button";
import React from "react";
import "./Header.css";

export const Header = () => (
  <div className="p-d-flex p-flex-column p-jc-center p-ai-center background-image" style={{ height: "850px" }}>
    <div
      className="p-d-flex p-flex-column p-jc-center p-ai-center p-mb-6"
      style={{ backgroundColor: "rgba(255, 255, 255, 0.5)", width: "100%" }}
    >
      <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
        <h1 className="p-text-uppercase p-text-bold p-mb-2" style={{ fontSize: "75px" }}>
          Réseau Biblillonie
        </h1>
        <h2 className="p-mb-6">Bibliothèques publiques et scolaires de la ville de Panoît</h2>
        <Button className="p-button-raised p-button-rounded p-button-help p-button-lg p-mb-3" label="En savoir plus" />
      </div>
    </div>
  </div>
);
