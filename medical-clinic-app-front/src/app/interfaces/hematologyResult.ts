import { AppointmentsHematology } from "./appointmentHematology";

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
    inr_cu_interpretare: number;
    hemoleucograma_completa: number;
    t3: number;
    t4: number;
    tsh: number;

    id: number;
    done: boolean;

    appointmentHematology: AppointmentsHematology;

    
    constructor(){}
}