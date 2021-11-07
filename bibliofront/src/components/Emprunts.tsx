import axios from "axios";
import { Badge } from "primereact/badge";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { Column } from "primereact/column";
import { DataTable } from "primereact/datatable";
import React, { useEffect, useState } from "react";
import { IEmpruntResultat } from "../app/model/EmpruntResultatModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { entity, getEntity, updateEntity } from "../app/store/slice/PretSlice";
import "./Emprunts.css";

export const Emprunts = () => {
  const dispatch = useAppDispatch();

  const pretEntity = useAppSelector(entity);

  const [emprunts, setEmprunts] = useState<IEmpruntResultat[]>();
  const [pretId, setPretId] = useState<number | undefined>(undefined);

  useEffect(() => {
    getEmprunts();
  }, []);

  useEffect(() => {
    if (pretId) {
      dispatch(updateEntity(pretId as number, { ...pretEntity, nbProlongations: 1 }));
      getEmprunts();
    }
  }, [pretId, pretEntity, dispatch]);

  const getEmprunts = () => {
    axios.get<IEmpruntResultat[]>("/usager/emprunts").then((response) => setEmprunts(response.data));
  };

  const rowClass = (rowData: IEmpruntResultat) => {
    return {
      "row-relances": rowData.relances > 0,
    };
  };

  const handleProlongation = (pretId: number): void => {
    dispatch(getEntity(pretId as number));
    setPretId(pretId);
  };

  return (
    <div className="p-grid">
      <div className="p-col-12">
        <Card
          title={
            <div className="p-d-flex p-ai-center">
              <div className="p-mr-4">Mes emprunts</div>
              <Badge value={emprunts?.length}></Badge>
            </div>
          }
        >
          <DataTable value={emprunts} rowClassName={rowClass}>
            <Column field="bibliotheque" header="Bibliothèque" style={{ width: "190px" }}></Column>
            <Column field="titre" header="Titre"></Column>
            <Column
              field="auteursPrenomNom"
              header="Auteurs"
              body={(rowData: IEmpruntResultat) => (
                <div className="p-d-flex p-flex-column">
                  {rowData.auteursPrenomNom.map((auteur) => (
                    <div key={auteur}>{auteur}</div>
                  ))}
                </div>
              )}
              style={{ width: "300px" }}
            ></Column>
            <Column field="dateRetour" header="Date de retour" style={{ width: "140px" }}></Column>
            <Column field="prolongations" header="Prolongations" style={{ width: "140px" }}></Column>
            <Column field="relances" header="Relances" style={{ width: "120px" }}></Column>
            <Column
              header=""
              body={(rowData: IEmpruntResultat) => (
                <Button
                  label="Prolonger"
                  className="p-button-sm"
                  disabled={rowData.prolongations === 1}
                  onClick={() => handleProlongation(rowData.pretId)}
                />
              )}
              style={{ width: "110px" }}
            ></Column>
          </DataTable>
        </Card>
      </div>
    </div>
  );
};
