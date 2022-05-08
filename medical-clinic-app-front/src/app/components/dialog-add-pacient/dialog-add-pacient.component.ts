import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import {  Component,  OnInit} from '@angular/core';
import {  FormBuilder,  FormGroup,  Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { timeStamp } from 'console';
import { Hospitalization } from 'src/app/interfaces/hospitalization';
import { Pacient } from 'src/app/interfaces/pacient';
import { User } from 'src/app/interfaces/user';
import { SecretarComponent } from 'src/app/pages/secretar/secretar/secretar.component';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-add-pacient',
  templateUrl: './dialog-add-pacient.component.html',
  styleUrls: ['./dialog-add-pacient.component.scss']
})
export class DialogAddPacientComponent implements OnInit {

  isLinear = false;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  selectedCardio: any= [];

  hospitalizationGroup: FormGroup;
  user: User;
  cardiologList: any;
  hospitalization: Hospitalization= new Hospitalization()
  newPacient: Pacient = new Pacient();

  constructor(private _formBuilder: FormBuilder,
    private _secretar: SecretarService,
    private dialogref: MatDialogRef < DialogAddPacientComponent >) {}

  ngOnInit() {
    this._secretar.allCardiolog().subscribe((res)=>{this.cardiologList = res
    console.log(res)})
    this.firstFormGroup = this._formBuilder.group({
      cnp: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      numberUser: ['', Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      emailUser: [''],
      dadLetterPatient: ['', Validators.required],
      
      seriesPatient:['', Validators.compose([Validators.required, Validators.pattern('([A-Z][A-Z])')])],
      numberPatient: ['', Validators.compose([Validators.required, Validators.pattern('([0-9]{6})')])],
      sexPatient: ['', Validators.required],
    });
    this.secondFormGroup = this._formBuilder.group({
      cityPatient: ['', Validators.required],
      townPatient: ['', Validators.required],
      streetPatient: ['', Validators.required],
      noPatient: ['', Validators.required],
    });
    this.thirdFormGroup = this._formBuilder.group({
      placePatient: ['', Validators.required],
      citizenshipPatient:['', Validators.required],
      jobPatient:[''],
      bloodTypePatient:[''],
      rhPatient:[''],
      allergyPatient:[''],
      insurancePatient:['', Validators.required]
    });
    this.hospitalizationGroup = this._formBuilder.group({
      registrationNoHospitalization: ['', Validators.required],
    })
   
  }
  setHospitalization(){
    
    this.hospitalization.registrationNoHospitalization = this.hospitalizationGroup.value.registrationNoHospitalization
  }
  setPacient(){
   this.newPacient.cnp = this.firstFormGroup.value.cnp
   this.newPacient.firstName = this.firstFormGroup.value.firstName
   this.newPacient.lastName = this.firstFormGroup.value.lastName
   this.newPacient.numberUser = this.firstFormGroup.value.numberUser
   this.newPacient.emailUser = this.firstFormGroup.value.emailUser
   this.newPacient.dadLetterPatient = this.firstFormGroup.value.dadLetterPatient
   this.newPacient.seriesPatient = this.firstFormGroup.value.seriesPatient
   this.newPacient.numberPatient = this.firstFormGroup.value.numberPatient
   this.newPacient.sexPatient = this.firstFormGroup.value.sexPatient
   
   this.newPacient.cityPatient = this.secondFormGroup.value.cityPatient
   this.newPacient.townPatient = this.secondFormGroup.value.townPatient
   this.newPacient.streetPatient = this.secondFormGroup.value.streetPatient
   this.newPacient.noPatient = this.secondFormGroup.value.noPatient

   this.newPacient.placePatient = this.thirdFormGroup.value.placePatient
   this.newPacient.citizenshipPatient = this.thirdFormGroup.value.citizenshipPatient
   this.newPacient.jobPatient = this.thirdFormGroup.value.jobPatient
   this.newPacient.bloodTypePatient = this.thirdFormGroup.value.bloodTypePatient
   this.newPacient.rhPatient = this.thirdFormGroup.value.rhPatient
   this.newPacient.allergyPatient = this.thirdFormGroup.value.allergyPatient
   this.newPacient.insurancePatient = this.thirdFormGroup.value.insurancePatient

   
  }
  addPacient(){
      this.setPacient() 
      console.log("intra aici")
      this._secretar.addPacient(this.newPacient).subscribe({
          next: (data) => {
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

