import { Cardiolog } from "./cardiolog";

export class Patient {
    cnp: string;
    firstName: string;
    lastName: string;
    emailUser: string;
    numberUser: string;
    dadLetterPatient: string;
    seriesPatient: string;
    numberPatient: string;
    sexPatient: string;
    citizenshipPatient: string;


    cityPatient: string;
    townPatient: string;
    streetPatient: string;
    noPatient: string;
    placePatient: string;


    insurancePatient: string;
    jobTypePatient: string;
    role: String;
    
    cardiolog: Cardiolog;
    constructor(){}
}