import { InputText } from "primereact/inputtext";
import React from "react";
import divider from "../ressources/images/divider.png";
import { InputTextarea } from "primereact/inputtextarea";
import { Button } from "primereact/button";

export const Contact = () => (
  <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
    <h2 className="p-text-uppercase p-text-bold p-mt-4">Contactez-nous</h2>
    <img className="p-mb-4" alt="divider" src={divider} />
    <div className="p-grid">
      <div className="p-col">
        <div className="p-d-flex p-flex-column">
          <p className="p-mb-6">
            Veuillez remplir le formulaire ci-dessous pour nous envoyer un courriel et nous vous répondrons dans les
            plus brefs délais.
          </p>
          <div className="p-fluid p-formgrid p-grid">
            <div className="p-field p-col-12 p-md-6">
              <label htmlFor="nom">Nom</label>
              <InputText id="nom" type="text" />
            </div>
            <div className="p-field p-col-12 p-md-6">
              <label htmlFor="email">Email</label>
              <InputText id="email" type="text" />
            </div>
            <div className="p-field p-col-12">
              <label htmlFor="message">Message</label>
              <InputTextarea id="message" type="text" rows={4} />
            </div>
          </div>
          <Button label="Envoyer" className="p-button-rounded p-button-lg" icon="pi pi-send" style={{width: "150px"}} />
        </div>
      </div>
    </div>
  </div>
);
