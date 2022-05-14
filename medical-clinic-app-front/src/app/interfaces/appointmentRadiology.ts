import { Patient } from "./patient";

export class AppointmentsRadiology {
    
    dataAppointmentHematology: string;
    hourAppointmentHematology: string;

    //Analize de sange
    eco: boolean;
    ekg: boolean;
    ct: boolean;
    irm: boolean;


    patient: Patient;
    
    constructor(){}
}
