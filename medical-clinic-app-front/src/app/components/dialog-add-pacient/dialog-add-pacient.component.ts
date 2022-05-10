import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import {  Component,  OnInit} from '@angular/core';
import {  FormBuilder,  FormGroup,  Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { timeStamp } from 'console';
import { Patient } from 'src/app/interfaces/patient';

import { User } from 'src/app/interfaces/user';
import { SecretarComponent } from 'src/app/pages/secretar/secretar/secretar.component';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-add-pacient',
  templateUrl: './dialog-add-pacient.component.html',
  styleUrls: ['./dialog-add-pacient.component.scss']
})
export class DialogAddPacientComponent implements OnInit {


  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  selectedCardio: any= [];

  hospitalizationGroup: FormGroup;
  user: User;
 

  newPacient: Patient = new Patient();

  constructor(private _formBuilder: FormBuilder,
    public _secretar: SecretarService,
    private dialogref: MatDialogRef < DialogAddPacientComponent >) {}

  ngOnInit() {
   

    this.firstFormGroup = this._formBuilder.group({
      cnp: [this._secretar.pacientService.cnp, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: [this._secretar.pacientService.firstName, Validators.required],
      lastName: [this._secretar.pacientService.lastName, Validators.required],
      emailUser: [this._secretar.pacientService.emailUser, Validators.required], 
      numberUser: [this._secretar.pacientService.numberUser, Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      dadLetterPatient: ['', Validators.required], 
      seriesPatient:['', Validators.compose([Validators.required, Validators.pattern('([A-Z][A-Z])')])],
      numberPatient: ['', Validators.compose([Validators.required, Validators.pattern('([0-9]{6})')])],
      sexPatient: ['', Validators.required],
      citizenshipPatient:['', Validators.required],
    });
    this.firstFormGroup.controls['cnp'].disable()

    this.secondFormGroup = this._formBuilder.group({
      cityPatient: ['', Validators.required],
      townPatient: ['', Validators.required],
      streetPatient: ['', Validators.required],
      noPatient: ['', Validators.required],
      placePatient: ['', Validators.required]
    });

    this.thirdFormGroup = this._formBuilder.group({
      jobTypePatient:['', Validators.required],
      insurancePatient:['', Validators.required],
     
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
  addPacient(){
      this.setPacient() 
      console.log("intra aici")
      this._secretar.addPatient(this.newPacient).subscribe({
          next: (data) => {
            this._secretar.addFisa(this.newPacient.cnp).subscribe((res)=>{
              this.firstFormGroup.reset();
              this.secondFormGroup.reset();
              this.thirdFormGroup.reset();
              //se intoarce cu textul save
              this.dialogref.close("add");
              console.log("add fisa")
            })

          console.log("Add pacient")
          },
          error: () => {
            console.log("eroare")
            
          }
        }
      

  )
  }
  deleteUser(data: any){

    console.log("Delete user")
    this.setPacient() 
    this._secretar.deletePatient(this.newPacient.cnp).subscribe((res)=>{
      
    })
  }

}

