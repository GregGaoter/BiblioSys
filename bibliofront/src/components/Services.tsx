import React, { FC } from "react";
import { IService } from "../app/model/ServiceModel";

interface ServicesProps {
  data: IService[];
}

export const Services: FC<ServicesProps> = ({ data }) => {
  return (
    <div id="services" className="text-center">
      <div className="container">
        <div className="section-title">
          <h2>Our Services</h2>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit duis sed dapibus leonec.</p>
        </div>
        <div className="row">
          {data.map((d, i) => (
            <div key={`${d.name}-${i}`} className="col-md-4">
              {" "}
              <i className={d.icon}></i>
              <div className="service-desc">
                <h3>{d.name}</h3>
                <p>{d.text}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
