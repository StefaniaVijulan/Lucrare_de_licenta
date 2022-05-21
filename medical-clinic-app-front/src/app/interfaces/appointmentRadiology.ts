import { Patient } from "./patient";

export class AppointmentsRadiology {
    
    dataAppointmentRadiology: string;
    hourAppointmentRadiology: string;

    //Analize de sange
    eco: boolean;
    ekg: boolean;
    ct: boolean;
    irm: boolean;


    patient: Patient;
    
    constructor(){}
}
