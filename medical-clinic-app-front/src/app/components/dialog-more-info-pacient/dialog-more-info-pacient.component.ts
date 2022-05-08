import {  Component,  Inject,  OnInit} from '@angular/core';
import {  FormBuilder} from '@angular/forms';
import {  MatDialogRef,  MAT_DIALOG_DATA} from '@angular/material';
import { timeStamp } from 'console';
import {  Pacient} from 'src/app/interfaces/pacient';
import {  SecretarService} from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-more-info-pacient',
  templateUrl: './dialog-more-info-pacient.component.html',
  styleUrls: ['./dialog-more-info-pacient.component.scss']
})
export class DialogMoreInfoPacientComponent implements OnInit {

  hospitalizationInfo: any;

  dataEnd: string;
  oraEnd: string;
  grupaSange: string;
  rhSange: string;
  asigurare: string;
  nrH: string;
  constructor(private _formBuilder: FormBuilder,
    private _secretar: SecretarService,
    private dialogref: MatDialogRef < DialogMoreInfoPacientComponent >
  ) {}

  ngOnInit() {
    console.log("onInit in dialog-more-info-pacient")
  /* this._secretar.getSpecificHospitalization().subscribe((res)=>{
      this.hospitalizationInfo = res;
      console.log("raspuns =>")
      console.log(this.hospitalizationInfo)
      if (!this.hospitalizationInfo.endDateHospitalization) {
        console.log("intra aici null data")
        this.dataEnd = "-"
        this.oraEnd = "-"
      } else {
        this.dataEnd = this.hospitalizationInfo.endDateHospitalization.split(" ")[0]
        this.oraEnd = this.hospitalizationInfo.endDateHospitalization.split(" ")[1] + " "+ this.hospitalizationInfo.endDateHospitalization.split(" ")[2]
      }
      console.log("blood")
      console.log(this.hospitalizationInfo.patient.bloodTypePatient)
      if (!this.hospitalizationInfo.patient.bloodTypePatient) {
        console.log("intra aici null grupa")
        this.grupaSange = "-"
        this.rhSange = "-"
      } else {
        this.grupaSange = this.hospitalizationInfo.patient.bloodTypePatient
        this.rhSange = this.hospitalizationInfo.patient.rhPatient
      }
      if (this.hospitalizationInfo.patient.insurancePatient == "1") {
        console.log("intra aici null asigurare")
        this.asigurare = "da"
      } else {
        this.asigurare = "nu"
      }
      console.log(this.hospitalizationInfo.numberOfHospitalization)
      if (!this.hospitalizationInfo.numberOfHospitalization) {
        console.log("intra aici null number ")
        this.nrH = "0"
      } else {
        this.nrH = this.hospitalizationInfo.numberOfHospitalization
      }
    })
    console.log("intra in if")
    */
  }



}