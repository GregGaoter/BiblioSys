import axios from "axios";
import { Button } from "primereact/button";
import { Calendar } from "primereact/calendar";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { Message } from "primereact/message";
import { InputNumber } from 'primereact/inputnumber';

const Registration = () => {
  const history = useHistory();

  const [prenom, setPrenom] = useState("");
  const [nom, setNom] = useState("");
  const [dateNaissance, setDateNaissance] = useState<Date | Date[] | undefined>(undefined);
  const [numeroRue, setNumeroRue] = useState<number | undefined>(undefined);
  const [rue, setRue] = useState("");
  const [codePostal, setCodePostal] = useState("");
  const [ville, setVille] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPasswordAlert, setShowPasswordAlert] = useState(false);

  const handleSignUp = () => {
    if (password !== confirmPassword) {
      setShowPasswordAlert(true);
    } else {
      const endpoint = "http://localhost:8080/registration";
      const data = { prenom, nom, dateNaissance, numeroRue, rue, codePostal, ville, username, password };

      axios.post(endpoint, data).then((reponse) => {
        history.push("/");
      });
    }
  };

  const title = <h3 className="p-d-flex p-jc-center">Bienvenue sur Biblillonie</h3>;

  const footer = (
    <Button
      label="S'inscrire"
      icon="pi pi-sign-in"
      style={{ display: "block", width: "100%" }}
      onClick={handleSignUp}
    />
  );

  return (
    <div className="p-d-flex p-jc-center p-mt-6">
      <div className="p-d-flex p-flex-column p-ai-center">
        <Card className="p-shadow-6 p-mb-4" title={title} footer={footer}>
          <div className="p-d-flex p-flex-column">
            <h5>Informations personnelles</h5>
            <div className="p-fluid p-formgrid p-grid">
              <div className="p-field p-col">
                <label>Prénom</label>
                <InputText type="text" onChange={(e) => setPrenom(e.currentTarget.value)} />
              </div>
              <div className="p-field p-col">
                <label>Nom</label>
                <InputText type="text" onChange={(e) => setNom(e.currentTarget.value)} />
              </div>
              <div className="p-field p-col">
                <label>Date de naissance</label>
                <Calendar
                  id="icon"
                  showIcon
                  monthNavigator
                  yearNavigator
                  yearRange={`${new Date().getFullYear() - 100}:${new Date().getFullYear()}`}
                  onChange={(e) => setDateNaissance(e.value)}
                />
              </div>
            </div>
            <h5>Adresse</h5>
            <div className="p-fluid p-formgrid p-grid">
              <div className="p-field p-col-6">
                <label>Numéro</label>
                <InputNumber showButtons min={1} onChange={(e) => setNumeroRue(e.value)} />
              </div>
              <div className="p-field p-col-6">
                <label>Rue</label>
                <InputText type="text" onChange={(e) => setRue(e.currentTarget.value)} />
              </div>
              <div className="p-field p-col-6">
                <label>Code Postal</label>
                <InputText type="text" onChange={(e) => setCodePostal(e.currentTarget.value)} />
              </div>
              <div className="p-field p-col-6">
                <label>Ville</label>
                <InputText type="text" onChange={(e) => setVille(e.currentTarget.value)} />
              </div>
            </div>
            <h5>Informations de connexion</h5>
            {showPasswordAlert && <Message severity="error" text="Votre mot de passe n'est pas confirmé" />}
            <div className="p-fluid p-formgrid p-grid">
              <div className="p-field p-col-12">
                <label>E-mail</label>
                <InputText
                  id="email"
                  type="email"
                  placeholder="Entrer votre e-mail"
                  onChange={(e) => setUsername(e.currentTarget.value)}
                />
              </div>
              <div className="p-field p-col-6">
                <label htmlFor="password">Mot de passe</label>
                <InputText
                  id="password"
                  type="password"
                  placeholder="Entrer votre mot de passe"
                  onChange={(e) => setPassword(e.currentTarget.value)}
                />
              </div>
              <div className="p-field p-col-6">
                <label htmlFor="password">Confirmer mot de passe</label>
                <InputText
                  id="confirmPassword"
                  type="password"
                  placeholder="Confirmer votre mot de passe"
                  onChange={(e) => setConfirmPassword(e.currentTarget.value)}
                />
              </div>
            </div>
          </div>
        </Card>
        <Button
          label="Revenir à la page d'accueil"
          icon="pi pi-arrow-left"
          className="p-button-secondary"
          style={{ display: "inline", width: "260px" }}
          onClick={() => history.push("/")}
        />
      </div>
    </div>
  );
};

export default Registration;
