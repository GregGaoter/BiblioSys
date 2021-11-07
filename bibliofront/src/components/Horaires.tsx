import { Column } from "primereact/column";
import { ColumnGroup } from "primereact/columngroup";
import { DataTable } from "primereact/datatable";
import React from "react";
import divider from "../ressources/images/divider.png";

export const Horaires = () => {
  const horairesMemoire = [
    { days: "Lundi", hours: "fermée" },
    { days: "Mardi", hours: "15h à 19h" },
    { days: "Mercredi", hours: "10h à 12h - 14h à 19h" },
    { days: "Jeudi", hours: "15h à 19h" },
    { days: "Vendredi", hours: "15h à 19h" },
    { days: "Samedi", hours: "10h à 12h - 14h à 18h" },
    { days: "Dimanche", hours: "fermée" },
  ];
  const horairesReverie = [
    { days: "Lundi", hours: "fermée" },
    { days: "Mardi", hours: "10h à 19h" },
    { days: "Mercredi", hours: "10h à 19h" },
    { days: "Jeudi", hours: "10h à 19h" },
    { days: "Vendredi", hours: "10h à 19h" },
    { days: "Samedi", hours: "10h à 17h" },
    { days: "Dimanche", hours: "fermée" },
  ];
  const horairesIdees = [
    { days: "Lundi", hours: "13h30 à 18h30" },
    { days: "Mardi", hours: "10h à 18h30" },
    { days: "Mercredi", hours: "13h30 à 18h30" },
    { days: "Jeudi", hours: "13h30 à 18h30" },
    { days: "Vendredi", hours: "10h à 12h - 13h30 à 19h" },
    { days: "Samedi", hours: "09h30 à 12h" },
    { days: "Dimanche", hours: "fermée" },
  ];

  const headerGroup = (
    <ColumnGroup>
      {/* <Row>
        <Column header="Bibliothèque Mémoire" colSpan={2} />
      </Row> */}
    </ColumnGroup>
  );

  return (
    <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
      <h2 className="p-text-uppercase p-text-bold p-mt-4">Nos horaires</h2>
      <img className="p-mb-4" alt="divider" src={divider} />
      <div className="p-grid">
        <div className="p-col">
          <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
            <h4 className="p-mb-3">Bibliothèque Mémoire</h4>
            <div style={{ textAlign: "center" }}>
              <DataTable value={horairesMemoire} headerColumnGroup={headerGroup} className="p-datatable-sm">
                <Column field="days" style={{width:'30%'}} />
                <Column field="hours" style={{width:'70%'}} />
              </DataTable>
            </div>
          </div>
        </div>
        <div className="p-col">
          <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
            <h4 className="p-mb-3">Bibliothèque Rêverie</h4>
            <div style={{ textAlign: "center" }}>
              <DataTable value={horairesReverie} headerColumnGroup={headerGroup} className="p-datatable-sm">
              <Column field="days" style={{width:'30%'}} />
                <Column field="hours" style={{width:'70%'}} />
              </DataTable>
            </div>
          </div>
        </div>
        <div className="p-col">
          <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
            <h4 className="p-mb-3">Bibliothèque Idées</h4>
            <div style={{ textAlign: "center" }}>
              <DataTable value={horairesIdees} headerColumnGroup={headerGroup} className="p-datatable-sm">
              <Column field="days" style={{width:'30%'}} />
                <Column field="hours" style={{width:'70%'}} />
              </DataTable>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
