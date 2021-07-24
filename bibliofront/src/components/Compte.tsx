import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { Dialog } from "primereact/dialog";
import React, { useState } from "react";
import { useAppSelector } from "../app/store/hooks";
import { entity as currentUsagerEntity } from "../app/store/slice/CurrentUsagerSlice";

export const Compte = () => {
  const [showUnavailableFeature, setShowUnavailableFeature] = useState<boolean>(false);
  
  const currentUsager = useAppSelector(currentUsagerEntity);

  const handleEditeInformationsPersonnelles = (event: any) => {
    setShowUnavailableFeature(true);
  };

  const handleEditeInformationsConnexion = (event: any) => {
    setShowUnavailableFeature(true);
  };

  const closeUnavailableFeature = (): void => {
    setShowUnavailableFeature(false);
  };

  return (
    <>
      <div className="p-grid">
        <div className="p-col-12">
          <Card
            title={
              <div className="p-d-flex p-ai-baseline">
                <div className="p-mr-4">Informations personnelles</div>
                <Button icon="pi pi-pencil" className="p-button-rounded" onClick={handleEditeInformationsPersonnelles} />
              </div>
            }
          >
            <div className="p-d-flex p-flex-column">
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "130px" }}>
                  Prénom
                </div>
                <div>{currentUsager.prenom}</div>
              </div>
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "130px" }}>
                  Nom
                </div>
                <div>{currentUsager.nom}</div>
              </div>
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "130px" }}>
                  Numéro/Rue
                </div>
                <div>{`${currentUsager.numeroRue} ${currentUsager.rue}`}</div>
              </div>
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "130px" }}>
                  Code postal/Ville
                </div>
                <div>{`${currentUsager.codePostal} ${currentUsager.ville}`}</div>
              </div>
            </div>
          </Card>
        </div>
        <div className="p-col-12">
          <Card
            title={
              <div className="p-d-flex p-ai-baseline">
                <div className="p-mr-4">Informations de connexion</div>
                <Button icon="pi pi-pencil" className="p-button-rounded" onClick={handleEditeInformationsConnexion} />
              </div>
            }
          >
            <div className="p-d-flex p-flex-column">
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "100px" }}>
                  E-mail
                </div>
                <div>{currentUsager.email}</div>
              </div>
              <div className="p-d-flex">
                <div className="p-mr-3 p-text-bold" style={{ width: "100px" }}>
                  Mot de passe
                </div>
                <div className="p-filled">••••••••</div>
              </div>
            </div>
          </Card>
        </div>
      </div>
      <Dialog
        header="Non disponible"
        visible={showUnavailableFeature}
        footer={<Button label="Fermer" icon="pi pi-times" onClick={closeUnavailableFeature} />}
        onHide={closeUnavailableFeature}
        style={{ width: "50vw" }}
      >
        <p>Cette fonctionnalité sera bientôt disponible.</p>
      </Dialog>
    </>
  );
};
