import { IconProp } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card } from "primereact/card";
import React, { FC } from "react";
import { Icon } from "../app/Icon";
import { Dropdown } from "primereact/dropdown";

interface RayonItemProps {
  icon: IconProp;
  title: string;
}

const RayonItem: FC<RayonItemProps> = (props) => (
  <div className="p-col-2" style={{ cursor: "pointer" }} onClick={findRayon}>
    <div className="p-d-flex p-flex-column p-ai-center">
      <FontAwesomeIcon icon={props.icon} className="p-mb-2" size="6x" />
      <div className="p-text-center">{props.title}</div>
    </div>
  </div>
);

const rayonItems: RayonItemProps[] = [
  { icon: Icon.THEATER_MASKS, title: "Littérature & Fiction" },
  { icon: Icon.ROBOT, title: "Policier & Science-Fiction" },
  { icon: Icon.DRAGON, title: "Bandes dessinées, comics & mangas, humour" },
  { icon: Icon.LEAF, title: "Développement durable & Écologie" },
  { icon: Icon.PAW, title: "Nature, animaux" },
  { icon: Icon.GAMEPAD, title: "Loisirs, jeux" },
  { icon: Icon.RUNNING, title: "Santé, diététique, sport" },
  { icon: Icon.YIN_YANG, title: "Bien-être, dans sa tête et dans son corps" },
  { icon: Icon.UTENSILS, title: "Cuisine, vins & boissons" },
  { icon: Icon.GLOBE_EUROPE, title: "Tourisme, voyages, géographie" },
  { icon: Icon.PALETTE, title: "Arts & spectacles" },
  { icon: Icon.MONUMENT, title: "Histoire, actualité" },
  { icon: Icon.PRAYING_HANDS, title: "Religions, spiritualité, ésotérisme" },
  { icon: Icon.USER_FRIENDS, title: "Sciences humaines et sociales" },
  { icon: Icon.BRAIN, title: "Psychologie, psychanalyse, pédagogie" },
  { icon: Icon.COGS, title: "Sciences, techniques, médecine" },
  { icon: Icon.LAPTOP_CODE, title: "Informatique & multimédia" },
  { icon: Icon.CHART_LINE, title: "Droit, économie, gestion, comptabilité" },
  { icon: Icon.GRADUATION_CAP, title: "Scolaire, parascolaire" },
];

const findRayon = () => {
  alert("Click");
};

const bibliothequeOptions = [
  { label: "Mémoire", value: "memoire" },
  { label: "Rêverie", value: "reverie" },
  { label: "Idée", value: "idee" },
];

const rayonOptions = [
  { label: "Littérature & Fiction", value: "Littérature & Fiction" },
  { label: "Policier & Science-Fiction", value: "Policier & Science-Fiction" },
  { label: "Bandes dessinées, comics & mangas, humour", value: "Bandes dessinées, comics & mangas, humour" },
];

const genreOptions = [
  { label: "Poésie", value: "Poésie" },
  { label: "Théatre", value: "Théatre" },
  { label: "Récit de voyage", value: "Récit de voyage" },
];

export const Livres = () => (
  <div className="p-grid">
    <div className="p-col-12">
      <Card title="Rayons">
        <div className="p-grid">
          {rayonItems.map((item) => (
            <RayonItem icon={item.icon} title={item.title} key={item.title} />
          ))}
        </div>
      </Card>
    </div>
    <div className="p-col-12">
      <Card title="Recherche avancée">
        <div className="p-fluid p-formgrid p-grid">
          <div className="p-field p-col-4">
            <label htmlFor="bibliotheque">Bibliothèque</label>
            <Dropdown value="memoire" options={bibliothequeOptions} onChange={(e) => ""} />
          </div>
          <div className="p-field p-col-4">
            <label htmlFor="rayon">Rayon</label>
            <Dropdown value="Littérature & Fiction" options={rayonOptions} onChange={(e) => ""} />
          </div>
          <div className="p-field p-col-4">
            <label htmlFor="genre">Genre</label>
            <Dropdown value="Poésie" options={genreOptions} onChange={(e) => ""} />
          </div>
        </div>
      </Card>
    </div>
  </div>
);
