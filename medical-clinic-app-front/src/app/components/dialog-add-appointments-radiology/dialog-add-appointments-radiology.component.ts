import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { AppointmentsRadiology } from 'src/app/interfaces/appointmentRadiology';
import { DoctorService } from 'src/app/services/doctor/doctor.service';

@Component({
  selector: 'app-dialog-add-appointments-radiology',
  templateUrl: './dialog-add-appointments-radiology.component.html',
  styleUrls: ['./dialog-add-appointments-radiology.component.scss']
})
export class DialogAddAppointmentsRadiologyComponent implements OnInit {
  appointmentRadiology: FormGroup;

  blockedData: any; // zilele in care nu se fac programari
  hourInterval: any;//orele in care nu se fac programari


  dateP: string;
  hourP:string;

  ziua: string;
  luna: string;
  dataEdit: string;

  appointment: AppointmentsRadiology = new AppointmentsRadiology();

  constructor(
    private dialogref: MatDialogRef < DialogAddAppointmentsRadiologyComponent >,
    private dialog: MatDialog,
    private _formBuilder: FormBuilder, public _doctor: DoctorService) { }

  ngOnInit() {
    console.log("validate data=>")
    this.validationDate()
    this.appointmentRadiology = this._formBuilder.group({
      eco: true,
      ekg: true,
      ct: true,
      irm: true     
    })
      
  }
  validationDate(){
    this._doctor.getDataBlockHema().subscribe((res)=>{
      //console.log(res)
      this.blockedData = new Array();
      for(let dat in res){
        this.blockedData.add(new Date(dat).getTime())
      }
      console.log(this.blockedData)
    })
  }
  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    return !this.blockedData.find(x=>x==time) ;
  };

  hourCheck(){
    var formatedDate = new Date(this.dateP).toLocaleString();
    if(formatedDate.split(",")[0].split("/")[0].length ==1)
    {
      this.luna = "0"+ formatedDate.split(",")[0].split("/")[0];
    }
    else{
      this.luna = formatedDate.split(",")[0].split("/")[0]
    }
    if(formatedDate.split(",")[0].split("/")[1].length ==1)
    {
      this.ziua = "0"+ formatedDate.split(",")[0].split("/")[1];
    }
    else{
      this.ziua = formatedDate.split(",")[0].split("/")[1]
    }
    this.dataEdit = this.ziua +"-"+ this.luna + "-"+formatedDate.split(",")[0].split("/")[2]
    console.log(this.dataEdit)
    this._doctor.getValidationDataHema( this.dataEdit).subscribe((res)=>{
      this.hourInterval = res

      console.log(res)
    })
 }
 setAppointementRadio(){
   this.appointment.ct = this.appointmentRadiology.value.ct,
   this.appointment.eco = this.appointmentRadiology.value.eco,
   this.appointment.ekg = this.appointmentRadiology.value.ekg,
   this.appointment.irm = this.appointmentRadiology.value.irm
 }
 addAppointmentRadio(){
   this.setAppointementRadio()
   console.log("add radio")
   console.log(this.appointment)
  this._doctor.addAppointmentRadiologie(this.appointment).subscribe((res)=>{
    console.log("add radio")
    console.log(res)

  })
 }
}
