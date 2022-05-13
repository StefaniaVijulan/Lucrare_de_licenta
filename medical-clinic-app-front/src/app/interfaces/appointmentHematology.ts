import { Patient } from "./patient";

export class AppointmentsHematology {
    
    dataAppointmentHematology: string;
    hourAppointmentHematology: string;

    //Analize de sange
    colesterol_seric_total: boolean;
    hdl_colesterol: boolean;
    ldl_colesterol: boolean;
    trigliceride_serice: boolean;
    glicemie: boolean;
    tgo: boolean;
    tgp: boolean;
    uree_serica: boolean;
    creatina_serica: boolean;
    potasiu_seric: boolean;
    magneziu_seric: boolean;
    acid_uric: boolean;
    calciu_ionic_seric: boolean;
    calciu_seric_total: boolean;
    inr_cu_interpretare: boolean;
    hemoleucograma_completa: boolean;
    t3: boolean;
    t4: boolean;
    tsh: boolean;

    patient: Patient;
    
    constructor(){}
}
