import { Button } from "primereact/button";
import { DataView, DataViewLayoutOptions } from "primereact/dataview";
import { Dropdown } from "primereact/dropdown";
import React, { useEffect, useRef, useState } from "react";
import { ILivre } from "../app/model/LivreModel";
import { ILivreResultat } from "../app/model/LivreResultatModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { entities as livreResultatEntities } from "../app/store/slice/LivreResultatSlice";
import { CreateMutable } from "../app/type";
// import "../ressources/css/livres-resultat.css";

export const LivresResultat = () => {
  const dispatch = useAppDispatch();

  const [layout, setLayout] = useState("grid");
  const [sortKey, setSortKey] = useState(null);
  const [sortOrder, setSortOrder] = useState(null);
  const [sortField, setSortField] = useState(null);
  const sortOptions = [
    { label: "Croissant", value: "livre" },
    { label: "Décroissant", value: "!livre" },
  ];

  const renderListItem = (data: ILivreResultat) => {
    return (
      <div className="p-col-12">
        <div className="p-d-flex">
          <img
            className="p-mr-2"
            src={`http://localhost:80/img/books/${data.livre?.nomImage}`}
            onError={(e) => (e.currentTarget.src = "http://localhost:80/img/books/couverture-non-disponible.jpg")}
            alt={data.livre?.nomImage}
          />
          <div className="p-d-flex p-flex-column">
            <h4 className="p-mb-2">{data.livre?.titre}</h4>
            <h5 className="p-mb-2">{data.auteur?.prenomNom}</h5>
          </div>
        </div>
      </div>
    );
  };

  // const renderListItem = (data: ILivre) => {
  //   return (
  //     <div className="p-col-12">
  //       <div className="product-list-item">
  //         <img
  //           src={`http://localhost:80/img/books/${data.nomImage}`}
  //           onError={(e) => (e.currentTarget.src = "http://localhost:80/img/books/couverture-non-disponible.jpg")}
  //           alt={data.nomImage}
  //         />
  //         <div className="product-list-detail">
  //           <div className="product-name">{data.titre}</div>
  //           <div className="product-description">{data.ean13}</div>
  //           <i className="pi pi-tag product-category-icon"></i>
  //           <span className="product-category">{data.dimension}</span>
  //         </div>
  //         <div className="product-list-action">
  //           <span className="product-price">${data.nbPages}</span>
  //           <Button icon="pi pi-shopping-cart" label="Emprunter" disabled={data.nbExemplaires === 0}></Button>
  //           <span className={`product-badge status-${data.nbExemplaires}`}>{data.nbExemplaires}</span>
  //         </div>
  //       </div>
  //     </div>
  //   );
  // };

  const renderGridItem = (data: ILivreResultat) => {
    return (
      <div className="p-col-12 p-md-4">
        <div className="product-grid-item card">
          <div className="product-grid-item-top">
            <div>
              <i className="pi pi-tag product-category-icon"></i>
              <span className="product-category">{data.livre?.dimension}</span>
            </div>
            <span className={`product-badge status-${data.livre?.nbExemplaires}`}>{data.livre?.nbExemplaires}</span>
          </div>
          <div className="product-grid-item-content">
            <img
              src={`http://localhost:80/img/books/${data.livre?.nomImage}`}
              onError={(e) => (e.currentTarget.src = "http://localhost:80/img/books/couverture-non-disponible.jpg")}
              alt={data.livre?.nomImage}
            />
            <div className="product-name">{data.livre?.titre}</div>
            <div className="product-description">{data.livre?.ean13}</div>
          </div>
          <div className="product-grid-item-bottom">
            <span className="product-price">${data.livre?.nbPages}</span>
            <Button icon="pi pi-shopping-cart" label="Add to Cart" disabled={data.livre?.nbExemplaires === 0}></Button>
          </div>
        </div>
      </div>
    );
  };

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
          <Dropdown
            options={sortOptions}
            value={sortKey}
            optionLabel="label"
            placeholder="Trier par titre"
            onChange={onSortChange}
          />
        </div>
        <div className="p-col-6" style={{ textAlign: "right" }}>
          <DataViewLayoutOptions layout={layout} onChange={(e) => setLayout(e.value)} />
        </div>
      </div>
    );
  };

  const onSortChange = () => {};

  const header = renderHeader();

  return (
    <div className="dataview-demo">
      <div className="card">
        <DataView
          value={useAppSelector(livreResultatEntities).slice(0, 9) as CreateMutable<ILivre[]>}
          layout={layout}
          header={header}
          itemTemplate={itemTemplate}
          paginator
          rows={5}
          // sortOrder={sortOrder}
          // sortField={sortField}
        />
      </div>
    </div>
  );
};
