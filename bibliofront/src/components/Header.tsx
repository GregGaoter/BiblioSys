import React, { FC } from "react";
import { IHeader } from "../app/model/HeaderModel";

interface HeaderProps {
  data: IHeader;
}

export const Header: FC<HeaderProps> = ({ data }) => {
  return (
    <header id="header">
      <div className="intro">
        <div className="overlay">
          <div className="container">
            <div className="row">
              <div className="col-md-8 col-md-offset-2 intro-text">
                <h1>
                  {data.title}
                  <span></span>
                </h1>
                <p>{data.paragraph}</p>
                <a href="#features" className="btn btn-custom btn-lg page-scroll">
                  En savoir plus
                </a>{" "}
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};
