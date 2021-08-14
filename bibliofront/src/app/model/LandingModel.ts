import { IContact } from "./ContactModel";
import { IFeature } from "./FeatureModel";
import { IHeader } from "./HeaderModel";
import { IService } from "./ServiceModel";
import { ITeam } from "./TeamModel";

export interface ILanding {
  Header?: IHeader;
  Features?: IFeature[];
  Services?: IService[];
  Team?: ITeam[];
  Contact?: IContact;
}

export const defaultValue: Readonly<ILanding> = {};
