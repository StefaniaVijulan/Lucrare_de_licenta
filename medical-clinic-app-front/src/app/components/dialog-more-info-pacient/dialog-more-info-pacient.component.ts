import {
  Component,
  Inject,
  OnInit
} from '@angular/core';
import {
  FormBuilder
} from '@angular/forms';
import {
  MatDialogRef,
  MAT_DIALOG_DATA
} from '@angular/material';
import {
  Pacient
} from 'src/app/interfaces/pacient';
import {
  SecretarService
} from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-more-info-pacient',
  templateUrl: './dialog-more-info-pacient.component.html',
  styleUrls: ['./dialog-more-info-pacient.component.scss']
})
export class DialogMoreInfoPacientComponent implements OnInit {
  pacientI: any;
  hospitalizationI: any;
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
    this.pacientI = this._secretar.pacient;
    console.log(this.pacientI)
    this.hospitalizationI = this._secretar.hospitalization;
    console.log(this.hospitalizationI)
    if (!this.hospitalizationI.endDateHospitalization) {
      console.log("intra aici null data")
      this.dataEnd = "-"
      this.oraEnd = "-"
    } else {
      this.dataEnd = this.hospitalizationI.endDateHospitalization.split(" ")[0]
      this.oraEnd = this.hospitalizationI.endDateHospitalization.split(" ")[1]
    }
    if (!this.pacientI.grupaSange) {
      console.log("intra aici null grupa")
      this.grupaSange = "-"
      this.rhSange = "-"
    } else {
      this.grupaSange = this.pacientI.bloodTypePatient
      this.oraEnd = this.pacientI.rhPatient
    }
    if (this.pacientI.insurancePatient == "1") {
      console.log("intra aici null asigurare")
      this.asigurare = "da"
    } else {
      this.asigurare = "nu"
    }
    if (this.hospitalizationI.numberOfHospitalization == 0) {
      console.log("intra aici null asigurare")
      this.nrH = "0"
    } else {
      this.nrH = this.hospitalizationI.numberOfHospitalization
    }
  }


}