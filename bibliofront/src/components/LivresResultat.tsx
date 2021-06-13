import { Badge } from "primereact/badge";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { DataView, DataViewLayoutOptions } from "primereact/dataview";
import { Dialog } from "primereact/dialog";
import { Dropdown } from "primereact/dropdown";
import React, { FC, useState } from "react";
import { ILivre } from "../app/model/LivreModel";
import { ILivreResultat } from "../app/model/LivreResultatModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { entities as livreResultatEntities } from "../app/store/slice/LivreResultatSlice";
import { CreateMutable } from "../app/type";
import { dateToMonthNameYear } from "../app/Utils";

export const LivresResultat = () => {
  const dispatch = useAppDispatch();

  const [layout, setLayout] = useState("grid");
  const [sortKey, setSortKey] = useState(undefined);
  const [sortOrder, setSortOrder] = useState(undefined);
  const [sortField, setSortField] = useState(undefined);
  const [showResume, setShowResume] = useState<boolean>(false);
  const [showUnavailableFeature, setShowUnavailableFeature] = useState<boolean>(false);
  const [resume, setResume] = useState<string | undefined>(undefined);

  const sortOptions = [
    { label: "Croissant", value: "titre" },
    { label: "Décroissant", value: "!titre" },
  ];

  const renderListItem = (data: ILivreResultat) => (
    <div className="p-col-12">
      <div className="p-d-flex p-m-3">
        <img
          className="p-mr-4 p-shadow-6"
          src={`http://localhost:80/img/livres/${data.livre?.nomImage}`}
          onError={(e) => (e.currentTarget.src = "http://localhost:80/img/livres/couverture-non-disponible.jpg")}
          alt={data.livre?.nomImage}
          style={{
            width: "135px",
            height: "195px",
          }}
        />
        <div className="p-d-flex p-flex-column">
          <h4 className="p-mb-2">{data.livre?.titre}</h4>
          <h5 className="p-mb-2">{data.auteur?.prenomNom}</h5>
          <h6>{data.editeur?.nom}</h6>
          <div className="p-mt-auto">{`EAN13 : ${data.livre?.ean13}`}</div>
          <div>{`Parution : ${dateToMonthNameYear(data.livre?.dateParution)}`}</div>
          <div>{`${data.livre?.nbPages} pages`}</div>
        </div>
        <div className="p-d-flex p-flex-column p-ai-center p-jc-between p-ml-auto">
          <CBadge nbExemplaires={data.livre?.nbExemplaires}></CBadge>
          <Button className="p-button-rounded p-button-help" onClick={() => handleShowResume(data.livre?.resume)}>
            <i className="pi pi-eye" style={{ fontSize: "1.5em" }} />
          </Button>
          <Button icon="pi pi-book" label="Emprunter" disabled={data.livre?.nbExemplaires === 0} />
        </div>
      </div>
    </div>
  );

  const renderGridItem = (data: ILivreResultat) => (
    <div className="p-col-4">
      <Card className="p-m-2">
        <div className="p-d-flex p-flex-column">
          <div className="p-d-flex p-jc-between p-mb-4">
            <div className="p-d-flex p-flex-column">
              <div className="p-mt-auto">{`EAN13 : ${data.livre?.ean13}`}</div>
              <div>{`Parution : ${dateToMonthNameYear(data.livre?.dateParution)}`}</div>
            </div>
            <div className="p-d-flex p-flex-column p-ai-end">
              <CBadge nbExemplaires={data.livre?.nbExemplaires}></CBadge>
              <div>{`${data.livre?.nbPages} pages`}</div>
            </div>
          </div>
          <img
            className="p-shadow-6 p-as-center p-mb-3"
            src={`http://localhost:80/img/livres/${data.livre?.nomImage}`}
            onError={(e) => (e.currentTarget.src = "http://localhost:80/img/livres/couverture-non-disponible.jpg")}
            alt={data.livre?.nomImage}
            style={{
              width: "135px",
              height: "195px",
            }}
          />
          <h4 className="p-mb-2 p-as-center">{data.livre?.titre}</h4>
          <h5 className="p-mb-2 p-as-center">{data.auteur?.prenomNom}</h5>
          <h6 className="p-mb-4 p-as-center">{data.editeur?.nom}</h6>
          <div className="p-d-flex p-jc-between">
            <Button className="p-button-rounded p-button-help" onClick={() => handleShowResume(data.livre?.resume)}>
              <i className="pi pi-eye" style={{ fontSize: "1.5em" }} />
            </Button>
            <Button icon="pi pi-book" label="Emprunter" disabled={data.livre?.nbExemplaires === 0} />
          </div>
        </div>
      </Card>
    </div>
  );

  const itemTemplate = (livreResultat: ILivreResultat, layout: "list" | "grid") => {
    if (!livreResultat) {
      return;
    }
    if (layout === "list") return renderListItem(livreResultat);
    else if (layout === "grid") return renderGridItem(livreResultat);
  };

  const renderHeader = () => {
    return (
      <div className="p-grid p-nogutter">
        <div className="p-col-6" style={{ textAlign: "left" }}>
          <Dropdown options={sortOptions} value={sortKey} placeholder="Trier par titre" onChange={onSortChange} />
        </div>
        <div className="p-col-6" style={{ textAlign: "right" }}>
          <DataViewLayoutOptions layout={layout} onChange={(e) => setLayout(e.value)} />
        </div>
      </div>
    );
  };

  const onSortChange = (event: any) => {
    setShowUnavailableFeature(true);
  };

  const handleShowResume = (resume: string | undefined): void => {
    setResume(resume);
    setShowResume(true);
  };

  const closeResume = (): void => {
    setShowResume(false);
  };

  const closeUnavailableFeature = (): void => {
    setShowUnavailableFeature(false);
  };

  const header = renderHeader();

  return (
    <>
      <div className="card">
        <DataView
          value={useAppSelector(livreResultatEntities) as CreateMutable<ILivre[]>}
          layout={layout}
          header={header}
          itemTemplate={itemTemplate}
          paginator
          rows={6}
          sortOrder={sortOrder}
          sortField={sortField}
        />
      </div>
      <Dialog
        header="Résumé"
        visible={showResume}
        footer={<Button label="Fermer" icon="pi pi-times" onClick={closeResume} />}
        onHide={closeResume}
        style={{ width: "50vw" }}
      >
        <p>{resume}</p>
      </Dialog>
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

interface ICBadge {
  nbExemplaires: number | undefined;
}

const CBadge: FC<ICBadge> = ({ nbExemplaires }) => {
  switch (nbExemplaires) {
    case 0:
      return <Badge value="Indisponible" severity="danger"></Badge>;
    case 1:
      return <Badge value="1 exemplaire" severity="warning"></Badge>;
    default:
      return <Badge value="Disponible" severity="success"></Badge>;
  }
};
