import { AppointmentsHematology } from "./appointmentHematology";
import { Hematolog } from "./hematolog";

export class HematologyResult{
    colesterol_seric_total: number;
    hdl_colesterol: number;
    ldl_colesterol: number;
    trigliceride_serice: number;
    glicemie: number;
    tgo: number;
    tgp: number;
    uree_serica: number;
    creatina_serica: number;
    potasiu_seric: number;
    magneziu_seric: number;
    acid_uric: number;
    calciu_ionic_seric: number;
    calciu_seric_total: number;

    
    globule_rosii: number;
    hemoglobina: number;
    hematocrit: number;
    globule_albe: number;
    trombocite: number;


    t3: number;
    t4: number;
    tsh: number;

    id: number;
    done: boolean;
    interpretare: string;

    appointmentHematology: AppointmentsHematology;

    hematolog: Hematolog;
    constructor(){}
}