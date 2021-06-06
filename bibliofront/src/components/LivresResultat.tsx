import React, { useState } from "react";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import { DataView, DataViewLayoutOptions } from "primereact/dataview";
import { ILivre } from "../app/model/LivreModel";
import { entities as livreEntities } from "../app/store/slice/LivreSlice";
import { CreateMutable } from "../app/type";

export const LivresResultat = () => {
  const dispatch = useAppDispatch();

  const [layout, setLayout] = useState('grid');

  const itemTemplate = (livre: ILivre, layout: "list" | "grid") => {
    if (!livre) {
      return;
    }
    if (layout === "list") {
      return renderListItem(livre);
    }
    if (layout === "grid") {
      return renderGridItem(livre);
    }
  };

  const renderListItem = (livre: ILivre) => {
    return (
      <div className="p-col-12">
        <div>{livre.titre}</div>
      </div>
    );
  };

  const renderGridItem = (livre: ILivre) => {
    return (
      <div className="p-col-12">
      <div>{livre.ean13}</div>
    </div>
    );
}

  return (
    <DataView
      value={useAppSelector(livreEntities).slice(0, 9) as CreateMutable<ILivre[]>}
      layout={layout}
      itemTemplate={itemTemplate}
    ></DataView>
  );
};
