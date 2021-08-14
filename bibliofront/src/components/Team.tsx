import React, { FC } from "react";
import { ITeam } from "../app/model/TeamModel";

interface TeamProps {
  data: ITeam[];
}

export const Team: FC<TeamProps> = ({ data }) => {
  return (
    <div id="team" className="text-center">
      <div className="container">
        <div className="col-md-8 col-md-offset-2 section-title">
          <h2>Meet the Team</h2>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit duis sed dapibus leonec.</p>
        </div>
        <div id="row">
          {data.map((d, i) => (
            <div key={`${d.name}-${i}`} className="col-md-3 col-sm-6 team">
              <div className="thumbnail">
                {" "}
                <img src={d.img} alt="..." className="team-img" />
                <div className="caption">
                  <h4>{d.name}</h4>
                  <p>{d.job}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
