import React from "react";
import { Card } from "primereact/card";
import memory from "../ressources/images/memory.jpg";
import reverie from "../ressources/images/reverie.jpg";
import idea from "../ressources/images/idea.png";

export const Bibliotheques = () => {
  return (
    <div className="p-grid">
      <div className="p-col">
        <Card title="Bibliothèque Mémoire" header={<img alt="Memory" src={memory} />} footer="500 livres">
          <p>
            Cette bibliothèque située <strong>81 Rue des Mandarines</strong> fera travailler votre mémoire avec sa large
            collection de revues scientifiques.
          </p>
        </Card>
      </div>
      <div className="p-col">
        <Card title="Bibliothèque Rêverie" header={<img alt="Reverie" src={reverie} />} footer="500 livres">
          <p>
            Cette bibliothèque située <strong>10 Rue des Bijoux</strong> vous fera rêver avec sa large collection de
            romans sentimentaux.
          </p>
        </Card>
      </div>
      <div className="p-col">
        <Card title="Bibliothèque Idée" header={<img alt="Idea" src={idea} />} footer="500 livres">
          <p>
            Cette bibliothèque située <strong>49 Rue de Lavande</strong> ne vous laissera pas à court d'idée avec sa
            large collection de revues techniques et de bricolage.
          </p>
        </Card>
      </div>
    </div>
  );
};
