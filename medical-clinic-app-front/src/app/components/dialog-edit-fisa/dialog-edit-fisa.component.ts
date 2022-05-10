import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { FisaPatient } from 'src/app/interfaces/fisaPatient';
import { DoctorService } from 'src/app/services/doctor/doctor.service';

@Component({
  selector: 'app-dialog-edit-fisa',
  templateUrl: './dialog-edit-fisa.component.html',
  styleUrls: ['./dialog-edit-fisa.component.scss']
})
export class DialogEditFisaComponent implements OnInit {
  formG: FormGroup;
  formG2: FormGroup;
  formG3: FormGroup;
  formG4: FormGroup;
  newfisaPatient: FisaPatient = new FisaPatient()
  constructor(private _formBuilder: FormBuilder,
    private _doctor: DoctorService,
    private dialogref: MatDialogRef < DialogEditFisaComponent >,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.formG = this._formBuilder.group({
      cnp: [this._doctor.infoFisaService.patient.cnp],
      firstName: [this._doctor.infoFisaService.patient.firstName],
      lastName: [this._doctor.infoFisaService.patient.lastName],
      allergyPatient: [this._doctor.infoFisaService.allergyPatient],
      bloodTypePatient: [this._doctor.infoFisaService.bloodTypePatient],
      rhPatient: [this._doctor.infoFisaService.rhPatient]
      
    });
    this.formG2 = this._formBuilder.group({
      familyHistory: [this._doctor.infoFisaService.familyHistory],
      personalHistory: [this._doctor.infoFisaService.personalHistory],
      lifeAndWorkConditional: [this._doctor.infoFisaService.lifeAndWorkConditional],
      behavior: [this._doctor.infoFisaService.behavior],
      pillsHistory: [this._doctor.infoFisaService.pillsHistory],

    });
    this.formG3 = this._formBuilder.group({
      generalCondition: [this._doctor.infoFisaService.generalCondition],
      waist: [this._doctor.infoFisaService.waist],
      weight: [this._doctor.infoFisaService.weight],
      nutritionalStatus: [this._doctor.infoFisaService.nutritionalStatus],
      ganglionSystem: [this._doctor.infoFisaService.ganglionSystem],
      connectiveTissue: [this._doctor.infoFisaService.connectiveTissue],

    });
    this.formG4 = this._formBuilder.group({
    
      cardiovascularSystem: [this._doctor.infoFisaService.cardiovascularSystem]
    });

    this.formG.controls['cnp'].disable()
    this.formG.controls['firstName'].disable()
    this.formG.controls['lastName'].disable()

    console.log("edit fisa")
    console.log(this._doctor.infoFisaService)
    console.log("this._doctor.infoFisaService.patient.cnp")
    console.log(this._doctor.infoFisaService.patient.cnp)
  }
  setFisa(){
    this.newfisaPatient.bloodTypePatient = this.formG.value.bloodTypePatient,
    this.newfisaPatient.rhPatient = this.formG.value.rhPatient,
    this.newfisaPatient.allergyPatient = this.formG.value.allergyPatient,


    this.newfisaPatient.familyHistory = this.formG2.value.familyHistory,
    this.newfisaPatient.personalHistory = this.formG2.value.personalHistory,
    this.newfisaPatient.lifeAndWorkConditional = this.formG2.value.lifeAndWorkConditional,
    this.newfisaPatient.behavior = this.formG2.value.behavior,
    this.newfisaPatient.pillsHistory = this.formG2.value.pillsHistory,

    this.newfisaPatient.generalCondition = this.formG3.value.generalCondition,
    this.newfisaPatient.waist = this.formG3.value.waist,
    this.newfisaPatient.weight = this.formG3.value.weight,
    this.newfisaPatient.nutritionalStatus = this.formG3.value.nutritionalStatus,
    this.newfisaPatient.ganglionSystem = this.formG3.value.ganglionSystem,
    this.newfisaPatient.connectiveTissue = this.formG3.value.connectiveTissue,

    this.newfisaPatient.cardiovascularSystem = this.formG4.value.cardiovascularSystem
  }

  editFisaPatient(){ 
    this.setFisa()
    this._doctor.editFisaPatient(this.newfisaPatient).subscribe((res)=>{
      this.formG.reset();
      this.formG2.reset();
      this.formG3.reset();
      this.formG4.reset();
      //se intoarce cu textul save
      this.dialogref.close("fisa");
      console.log("Add fisas")
    })
  }
}
