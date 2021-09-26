import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import divider from "../ressources/images/divider.png";

export const Services = () => (
  <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
    <h2 className="p-text-uppercase p-text-bold p-mt-4">Nos services</h2>
    <img className="p-mb-4" alt="divider" src={divider} />
    <div className="p-grid">
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <FontAwesomeIcon icon="eye" size="6x" color="#2196F3" />
          <h4 className="p-mb-3">Consultation</h4>
          <div style={{ textAlign: "center" }}>
            <p>Consultez vos livres dans nos espaces de lecture</p>
          </div>
        </div>
      </div>
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <FontAwesomeIcon icon="search" size="6x" color="#2196F3" />
          <h4 className="p-mb-3">Recherche</h4>
          <div style={{ textAlign: "center" }}>
            <p>Trouvez vos livres parmis un vaste choix de rayons</p>
          </div>
        </div>
      </div>
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <FontAwesomeIcon icon="exchange-alt" size="6x" color="#2196F3" />
          <h4 className="p-mb-3">Prêt</h4>
          <div style={{ textAlign: "center" }}>
            <p>Empruntez nos livres pour une durée limitée</p>
          </div>
        </div>
      </div>
    </div>
  </div>
);
