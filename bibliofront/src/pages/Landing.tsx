import React, { useEffect, useState } from "react";
import { IContact } from "../app/model/ContactModel";
import { IFeature } from "../app/model/FeatureModel";
import { IHeader } from "../app/model/HeaderModel";
import { defaultValue, ILanding } from "../app/model/LandingModel";
import { IService } from "../app/model/ServiceModel";
import { ITeam } from "../app/model/TeamModel";
import { Bibliotheques } from "../components/Bibliotheques";
import { Contact } from "../components/Contact";
import { Features } from "../components/Features";
import { Gallery } from "../components/Gallery";
import { Header } from "../components/Header";
import { Navigation } from "../components/Navigation";
import { Services } from "../components/Services";
import { Team } from "../components/Team";
import { data } from "../data/data";

const Landing = () => {
  const [landingPageData, setLandingPageData] = useState<ILanding>(defaultValue);

  useEffect(() => {
    setLandingPageData(data);
  }, []);

  return (
    <>
      {landingPageData && (
        <div>
          <Navigation />
          <Header />
          {/* <Bibliotheques /> */}
          {/* <Header data={landingPageData.Header as IHeader} />
          <Features data={landingPageData.Features as IFeature[]} />
          <Services data={landingPageData.Services as IService[]} />
          <Gallery />
          <Team data={landingPageData.Team as ITeam[]} />
          <Contact data={landingPageData.Contact as IContact} /> */}
        </div>
      )}
    </>
  );
};

export default Landing;
