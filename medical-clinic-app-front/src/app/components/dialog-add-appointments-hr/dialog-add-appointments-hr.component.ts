import { AnimateTimings } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { DialogAddAppointmentsHematologyComponent } from '../dialog-add-appointments-hematology/dialog-add-appointments-hematology.component';
import { DialogAddAppointmentsRadiologyComponent } from '../dialog-add-appointments-radiology/dialog-add-appointments-radiology.component';

@Component({
  selector: 'app-dialog-add-appointments-hr',
  templateUrl: './dialog-add-appointments-hr.component.html',
  styleUrls: ['./dialog-add-appointments-hr.component.scss']
})
export class DialogAddAppointmentsHrComponent implements OnInit {

  appointmens: FormGroup;
  panelOpenState = false;

  listSpecificHema: any;
  listSpecificRadio: any;
  constructor(private _doctor:DoctorService,
    private _formBuilder: FormBuilder,
    private dialogref: MatDialogRef < DialogAddAppointmentsHrComponent >,
    private dialog: MatDialog ) { 
    this.appointmens = this._formBuilder.group({
      appointmensRadiology: false,
      appointmensHematology: false
  });

  }

  ngOnInit() {
this.getSpeciifcHema();
this.getSpeciifcRadio();
}

addAppointmentHematology(){
  this._doctor.appointmensRadio = this.appointmens.value.appointmensRadiology
  this.dialogref.close("addHR");
  this.dialog.open(DialogAddAppointmentsHematologyComponent,{
   width: '30%'
  });
}
openAddRadiology(){
  console.log("Appointment radio")
  this.dialogref.close("addHemaDone");
  this.dialog.open(DialogAddAppointmentsRadiologyComponent,{
   width: '30%'
  });
}
getSpeciifcHema(){
  this._doctor.getSpecificAppointmentHematology().subscribe((res)=>{
    this.listSpecificHema = res;
    console.log(this.listSpecificHema)
  })
}
getSpeciifcRadio(){
  this._doctor.getSpecificAppointmentRadiology().subscribe((res)=>{
    this.listSpecificRadio = res;
    console.log(this.listSpecificRadio)
  })
}
}