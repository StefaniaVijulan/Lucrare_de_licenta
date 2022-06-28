import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentsHematology } from 'src/app/interfaces/appointmentHematology';
import { AppointmentsRadiology } from 'src/app/interfaces/appointmentRadiology';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { PacientService } from 'src/app/services/pacient/pacient.service';

@Component({
  selector: 'app-reprogramare-app',
  templateUrl: './reprogramare-app.component.html',
  styleUrls: ['./reprogramare-app.component.scss']
})
export class ReprogramareAppComponent implements OnInit {

  blockedData: any
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  acordGDPR: boolean = false;
  hourInterval: any;
  luna: string;
  formG: FormGroup;
  selectedCardio:any = localStorage.getItem("cnp")
  editApp: Appointment = new Appointment();
  editAppH: AppointmentsHematology = new AppointmentsHematology();
  editAppR: AppointmentsRadiology = new AppointmentsRadiology();
  listCardio: Object;
  
  loading: boolean = false;
  constructor(public _patient:PacientService,
   private _formBuilder: FormBuilder,
   private _appointment: AppointmentService,
    private dialogref: MatDialogRef < ReprogramareAppComponent >,
    private dialog: MatDialog) { }

  ngOnInit() {
    console.log(" this._patient.cardiologService => ", this._patient.cardiologService)
    this.validationDate()
    this.validationDateHema()
    this.validationDateRadio()
  }

  validationDate(){
    this._appointment.getDataBlock(this._patient.cardiologService).subscribe((res)=>{
      for(let dat of res){
        this._appointment.blockedDataAppointment.push(new Date(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] ).getTime());
      }
    })
  }
  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    if(this._patient.reprogrameazaType == "appointment")
      {
        console.log("intra in myfilter")
        return !this._appointment.blockedDataAppointment.find(x=>x==time) && day !==0 && day !==6;

      }
    else if(this._patient.reprogrameazaType == "hematology"){
      console.log("intra in myfilter 2")
      return !this.blockedData.find(x=>x==time) && day !==0 && day !==6;
      
    }else{
      console.log("intra in myfilter 3")
      return !this.blockedData.find(x=>x==time) && day !==0 && day !==6;
    }
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
   //  console.log(this.dataEdit)
     this._appointment.getValidationData(this.selectedCardio, this.dataEdit).subscribe((res)=>{
       this.hourInterval = res
      // console.log(res)
     })
  }
  validationDateHema(){
    this._patient.getDataBlockHema().subscribe((res)=>{
      //console.log(res)
      this.blockedData = new Array();
      for(let dat in res){
        this.blockedData.add(new Date(dat).getTime())
      }
      console.log("this.blockedData",this.blockedData)
    })
  }
  hourCheckHema(){
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
    this._patient.getValidationDataHema( this.dataEdit).subscribe((res)=>{
      this.hourInterval = res

      console.log(res)
    })
 }
 validationDateRadio(){
  this._patient.getDataBlockRadio().subscribe((res)=>{
    //console.log(res)
    this.blockedData = new Array();
    for(let dat in res){
      this.blockedData.add(new Date(dat).getTime())
    }
    console.log("this.blockedData",this.blockedData)
  })
}
hourCheckRadio(){
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
  this._patient.getValidationDataRadio( this.dataEdit).subscribe((res)=>{
    this.hourInterval = res

    console.log(res)
  })
}
  reprogramreApp(){
    if(this._patient.reprogrameazaType == "appointment"){
      console.log("appointment")
       this.editApp.dataA = this.dataEdit
      this.editApp.hour = this.hourP
      this._patient.reprogramareAppointment(this.editApp).subscribe((res)=>{
        console.log(res)
      })
    }else if(this._patient.reprogrameazaType == "hematology"){
      console.log("hematology")
      this.editAppH.dataAppointmentHematology = this.dataEdit
      this.editAppH.hourAppointmentHematology = this.hourP
      this._patient.reprogramareAppointmentHematology(this.editAppH).subscribe((res)=>{
        console.log(res)
      })

    }
    else if(this._patient.reprogrameazaType == "radiology"){
      console.log("acum radiology")
      this.editAppR.dataAppointmentRadiology = this.dataEdit
      this.editAppR.hourAppointmentRadiology = this.hourP
      console.log("this.editAppR", this.editAppR)
      this._patient.reprogramareAppointmentRadiology(this.editAppR).subscribe((res)=>{
        console.log(res)
      })

    }
  }


}
