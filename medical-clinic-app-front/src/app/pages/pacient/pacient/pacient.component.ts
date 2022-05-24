import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddAppointmentByPacientComponent } from 'src/app/components/pacient/dialog-add-appointment-by-pacient/dialog-add-appointment-by-pacient.component';

import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { PacientService } from 'src/app/services/pacient/pacient.service';

@Component({
  selector: 'app-pacient',
  templateUrl: './pacient.component.html',
  styleUrls: ['./pacient.component.scss']
})
export class PacientComponent implements OnInit {

  constructor(private dialog: MatDialog, public _service: AuthService, public _patient: PacientService) { }

  ngOnInit() {
    this.allCardio()
  }
 allCardio(){
  this._patient.allCardiolog().subscribe((res)=>{
   this._patient.doctorListService = res;
    console.log(res)
  })
 }
 addAppointment(){
   console.log("Add appoinmtemt")
   this.dialog.open(DialogAddAppointmentByPacientComponent,{
    width: '30%',
    panelClass: 'my-panel'
   });
 }
}
