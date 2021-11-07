import React from "react";
import divider from "../ressources/images/divider.png";
import team01 from "../ressources/images/team01.jpg";
import team02 from "../ressources/images/team02.jpg";
import team03 from "../ressources/images/team03.jpg";
import team04 from "../ressources/images/team04.jpg";

export const Team = () => (
  <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
    <h2 className="p-text-uppercase p-text-bold p-mt-4">Notre équipe</h2>
    <img className="p-mb-4" alt="divider" src={divider} />
    <div className="p-grid">
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <img alt="team01" src={team01} className="p-shadow-6 p-mb-3" />
          <h4 className="p-mb-3">Eugène Du Trieux</h4>
          <div style={{ textAlign: "center" }}>
            <p>Responsable réseau Biblillonie</p>
          </div>
        </div>
      </div>
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <img alt="team02" src={team02} className="p-shadow-6 p-mb-3" />
          <h4 className="p-mb-3">Noël Séguin</h4>
          <div style={{ textAlign: "center" }}>
            <p>Responsable bibliothèque Mémoire</p>
          </div>
        </div>
      </div>
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <img alt="team03" src={team03} className="p-shadow-6 p-mb-3" />
          <h4 className="p-mb-3">Anouk Ducharme</h4>
          <div style={{ textAlign: "center" }}>
            <p>Responsable bibliothèque Rêverie</p>
          </div>
        </div>
      </div>
      <div className="p-col">
        <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
          <img alt="team04" src={team04} className="p-shadow-6 p-mb-3" />
          <h4 className="p-mb-3">Nadine Arcouet</h4>
          <div style={{ textAlign: "center" }}>
            <p>Responsable bibliothèque Idées</p>
          </div>
        </div>
      </div>
    </div>
  </div>
);
