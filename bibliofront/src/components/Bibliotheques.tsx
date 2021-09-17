import { Card } from "primereact/card";
import { ProgressSpinner } from "primereact/progressspinner";
import React, { FC, useEffect, useRef } from "react";
import { IBibliotheque } from "../app/model/BibliothequeModel";
import { ILivre } from "../app/model/LivreModel";
import { useAppDispatch, useAppSelector } from "../app/store/hooks";
import {
  entities as bibliothequeEntities,
  getEntities as getBibliothequeEntities,
} from "../app/store/slice/BibliothequeSlice";
import {
  entities as livreEntities,
  getEntities as getLivreEntities,
  loading as livreLoading,
} from "../app/store/slice/LivreSlice";

export const Bibliotheques = () => {
  const dispatch = useAppDispatch();

  const hasLivres = useRef(false);
  const hasBibliotheques = useRef(false);

  useEffect(() => {
    if (!hasLivres.current) {
      dispatch(getLivreEntities());
      hasLivres.current = true;
    }
    if (!hasBibliotheques.current) {
      dispatch(getBibliothequeEntities());
      hasBibliotheques.current = true;
    }
  }, [dispatch]);

  const Header: FC<Pick<IBibliotheque, "imageFileName">> = ({ imageFileName }) => (
    <img alt={imageFileName} src={`/images/${imageFileName}`} />
  );

  const Footer: FC<Pick<ILivre, "bibliothequeId">> = ({ bibliothequeId }) => {
    const nbLivres = useAppSelector(livreEntities).filter((livre) => livre.bibliothequeId === bibliothequeId).length;
    return <div>{useAppSelector(livreLoading) ? <ProgressSpinner /> : <FooterWithLivres nbLivres={nbLivres} />}</div>;
  };

  interface FooterWithLivresProps {
    nbLivres: number;
  }

  const FooterWithLivres: FC<FooterWithLivresProps> = ({ nbLivres }) => <div>{`${nbLivres} livres`}</div>;

  return (
    <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
      <h2 className="p-text-uppercase p-text-bold p-mt-4 p-mb-2">Nos bibliothèques</h2>
      <hr style={{height: 55, color: '#000000', backgroundColor: '#000000'}}/>
      <div style={{ borderTop: "20px solid #fff ", marginLeft: 20, marginRight: 20 }}></div>
      <div className="p-grid">
        {useAppSelector(bibliothequeEntities).map((bibliotheque) => {
          const { id, nom, description, imageFileName } = bibliotheque;
          return (
            <div className="p-col" key={id}>
              <Card
                title={nom}
                header={<Header imageFileName={imageFileName} />}
                footer={<Footer bibliothequeId={id} />}
              >
                <p>{description}</p>
              </Card>
            </div>
          );
        })}
      </div>
    </div>
  );
};
