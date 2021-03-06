import { StringifyOptions } from "querystring";
import { Cardiolog } from "./cardiolog";
import { Patient } from "./patient";


export class Appointment {
    
    cnp: string;
    firstName: string;
    lastName: string;
    emailUser: string;
    numberUser: string;
    dataA: string;
    hour: string;
    
    patient: Patient;

    cardiolog: Cardiolog;
    constructor(){}
}
