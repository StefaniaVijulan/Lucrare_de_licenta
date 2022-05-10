import { Patient } from "./patient";

export class FisaPatient {
    
    bloodTypePatient: string;
    rhPatient: string;
    allergyPatient: string;

    //ANAMNEZA

    familyHistory: string;
    personalHistory: string; // ce boli a mai avut, nasteri etc
    lifeAndWorkConditional: string; //daca lucreaza rural sau traieste in zona toxica
    behavior: string;
    pillsHistory: string;

    //EXAMEN OBIECTIV
    generalCondition: string;
    waist: string;
    weight: string;
    nutritionalStatus: string;
    ganglionSystem: string;
    connectiveTissue: string;

    cardiovascularSystem: string;

    patient: Patient;
}