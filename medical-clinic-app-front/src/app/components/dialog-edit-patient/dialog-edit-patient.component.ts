import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Patient } from 'src/app/interfaces/patient';
import { SecretarService } from 'src/app/services/secretar/secretar.service';
import { DialogAddPacientComponent } from '../dialog-add-pacient/dialog-add-pacient.component';

@Component({
  selector: 'app-dialog-edit-patient',
  templateUrl: './dialog-edit-patient.component.html',
  styleUrls: ['./dialog-edit-patient.component.scss']
})
export class DialogEditPatientComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;


  hospitalizationGroup: FormGroup;
 

  newPacient: Patient = new Patient();
  constructor(private _formBuilder: FormBuilder,
    public _secretar: SecretarService, private dialog: MatDialog,
    private dialogref: MatDialogRef < DialogAddPacientComponent >) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      cnp: [this._secretar.pacientService.cnp, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: [this._secretar.pacientService.firstName, Validators.required],
      lastName: [this._secretar.pacientService.lastName, Validators.required],
      emailUser: [this._secretar.pacientService.emailUser, Validators.required], 
      numberUser: [this._secretar.pacientService.numberUser, Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      dadLetterPatient: [this._secretar.pacientService.dadLetterPatient, Validators.required], 
      seriesPatient:[this._secretar.pacientService.seriesPatient, Validators.compose([Validators.required, Validators.pattern('([A-Z][A-Z])')])],
      numberPatient: [this._secretar.pacientService.numberPatient, Validators.compose([Validators.required, Validators.pattern('([0-9]{6})')])],
      sexPatient: [this._secretar.pacientService.sexPatient, Validators.required],
      citizenshipPatient:[this._secretar.pacientService.citizenshipPatient, Validators.required],
    });
    this.firstFormGroup.controls['cnp'].disable()

    this.secondFormGroup = this._formBuilder.group({
      cityPatient: [this._secretar.pacientService.cityPatient, Validators.required],
      townPatient: [this._secretar.pacientService.townPatient, Validators.required],
      streetPatient: [this._secretar.pacientService.streetPatient, Validators.required],
      noPatient: [this._secretar.pacientService.noPatient, Validators.required],
      placePatient: [this._secretar.pacientService.placePatient, Validators.required]
    });

    this.thirdFormGroup = this._formBuilder.group({
      jobTypePatient:[this._secretar.pacientService.jobTypePatient, Validators.required],
      insurancePatient:[this._secretar.pacientService.insurancePatient, Validators.required],
     
    });
   
  }
  setPacient(){
   this.newPacient.cnp = this._secretar.pacientService.cnp
   this.newPacient.firstName = this.firstFormGroup.value.firstName
   this.newPacient.lastName = this.firstFormGroup.value.lastName
   this.newPacient.numberUser = this.firstFormGroup.value.numberUser
   this.newPacient.emailUser = this.firstFormGroup.value.emailUser
   this.newPacient.dadLetterPatient = this.firstFormGroup.value.dadLetterPatient
   this.newPacient.seriesPatient = this.firstFormGroup.value.seriesPatient
   this.newPacient.numberPatient = this.firstFormGroup.value.numberPatient
   this.newPacient.sexPatient = this.firstFormGroup.value.sexPatient
   this.newPacient.citizenshipPatient = this.firstFormGroup.value.citizenshipPatient


   this.newPacient.cityPatient = this.secondFormGroup.value.cityPatient
   this.newPacient.townPatient = this.secondFormGroup.value.townPatient
   this.newPacient.streetPatient = this.secondFormGroup.value.streetPatient
   this.newPacient.noPatient = this.secondFormGroup.value.noPatient
   this.newPacient.placePatient = this.secondFormGroup.value.placePatient

   this.newPacient.insurancePatient = this.thirdFormGroup.value.insurancePatient
   this.newPacient.jobTypePatient = this.thirdFormGroup.value.jobTypePatient

   
  }
  editPatient(){
    console.log("editeaza")
  //DE FACUT FUNCTIA DE EDIT 
    this.dialogref.close();
  }
}
