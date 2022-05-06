import { Cardiolog } from "./cardiolog";
import { Pacient } from "./pacient";
import { Secretar } from "./secretar";

export class Hospitalization {
    
    registrationNoHospitalization: string;
    
    patient: Pacient;
    secretary: Secretar;
    cardiolog: Cardiolog;
    constructor(){}
}