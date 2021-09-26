import React from "react";
import divider from "../ressources/images/divider.png";
import galery01 from "../ressources/images/galery01.jpg";
import galery02 from "../ressources/images/galery02.jpg";
import galery03 from "../ressources/images/galery03.jpg";
import galery04 from "../ressources/images/galery04.jpg";
import galery05 from "../ressources/images/galery05.jpg";
import galery06 from "../ressources/images/galery06.jpg";
import galery07 from "../ressources/images/galery07.jpg";
import galery08 from "../ressources/images/galery08.jpg";
import galery09 from "../ressources/images/galery09.jpg";

export const Gallery = () => {
  const gallery = [galery01, galery02, galery03, galery04, galery05, galery06, galery07, galery08, galery09];

  return (
    <div className="p-d-flex p-flex-column p-jc-center p-ai-center">
      <h2 className="p-text-uppercase p-text-bold p-mt-4">Notre gallerie</h2>
      <img className="p-mb-4" alt="divider" src={divider} />
      <div className="p-grid p-justify-center">
        {gallery.map((image, index) => (
          <div className="p-col-4" key={index}>
            <img alt={`gallery${index}`} src={image} className="p-mx-2" style={{ cursor: 'pointer' }} />
          </div>
        ))}
      </div>
    </div>
  );
};
