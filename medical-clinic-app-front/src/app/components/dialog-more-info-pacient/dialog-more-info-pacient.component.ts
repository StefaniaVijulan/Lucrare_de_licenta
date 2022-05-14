import {  Component,  Inject,  OnInit} from '@angular/core';
import {  FormBuilder} from '@angular/forms';
import {  MatDialog, MatDialogRef,  MAT_DIALOG_DATA} from '@angular/material';
import { timeStamp } from 'console';
import { DoctorService } from 'src/app/services/doctor/doctor.service';

import {  SecretarService} from 'src/app/services/secretar/secretar.service';
import { DialogAddAppointmentsHrComponent } from '../dialog-add-appointments-hr/dialog-add-appointments-hr.component';
import { DialogEditFisaComponent } from '../dialog-edit-fisa/dialog-edit-fisa.component';

@Component({
  selector: 'app-dialog-more-info-pacient',
  templateUrl: './dialog-more-info-pacient.component.html',
  styleUrls: ['./dialog-more-info-pacient.component.scss']
})
export class DialogMoreInfoPacientComponent implements OnInit {

  fisaInfo: any;

  dataEnd: string;
  oraEnd: string;
  grupaSange: string;
  rhSange: string;
  asigurare: string;
  nrH: string;
  constructor(private _formBuilder: FormBuilder,
    private _doctor: DoctorService,
    private dialogref: MatDialogRef < DialogMoreInfoPacientComponent >,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    
    console.log("fisa pacient")
    this._doctor.infoSpecific(this._doctor.cnpCurrantpatientService).subscribe((res)=>{
      this.fisaInfo = res
      this._doctor.infoFisaService = res;
      console.log(res)
    })
    
  }
  openDialog(){
    this.dialogref.close("save");
    this.dialog.open(DialogEditFisaComponent,{
     width: '40%'
    })}
  openAppointmentsDialog(){
      this.dialogref.close("addAppointments");
      this.dialog.open(DialogAddAppointmentsHrComponent,{
       width: '40%'
      })}
  


}