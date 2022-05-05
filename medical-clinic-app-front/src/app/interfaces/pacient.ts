import { Hospitalization } from "./hospitalization";

export class Pacient {
    
    cnp: String;
    firstName: String;
    lastName: String;
    emailUser: String;
    numberUser: String;

    dadLetterPatient: string;
    seriesPatient: string;
    numberPatient: string;
    sexPatient: string;

    cityPatient: string;
    townPatient: string;
    streetPatient: string;
    noPatient: string;

    placePatient: string;
    citizenshipPatient: string;
    jobPatient: string;
    bloodTypePatient: string;
    rhPatient: string;
    allergyPatient:string;
    insurancePatient: boolean;

    role: String;
    
    hospitalizationList: Hospitalization[];
    
    constructor(){}
}
