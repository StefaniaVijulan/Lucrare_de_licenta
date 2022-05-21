import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { stringify } from 'querystring';
import { AppointmentsHematology } from 'src/app/interfaces/appointmentHematology';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { DialogAddAppointmentsRadiologyComponent } from '../dialog-add-appointments-radiology/dialog-add-appointments-radiology.component';

@Component({
  selector: 'app-dialog-add-appointments-hematology',
  templateUrl: './dialog-add-appointments-hematology.component.html',
  styleUrls: ['./dialog-add-appointments-hematology.component.scss']
})
export class DialogAddAppointmentsHematologyComponent implements OnInit {
  tomorrowD:any = new Date().getDate();
  appointmentHematology: FormGroup;

  blockedData: any; // zilele in care nu se fac programari
  hourInterval: any;//orele in care nu se fac programari

  dateP: string;
  hourP:string;

  ziua: string;
  luna: string;
  dataEdit: string;

  appointment: AppointmentsHematology = new AppointmentsHematology();

  constructor(
    private dialogref: MatDialogRef < DialogAddAppointmentsRadiologyComponent >,
    private dialog: MatDialog,
    private _formBuilder: FormBuilder, public _doctor: DoctorService) { }

  ngOnInit() {
    console.log("validate data=>")
    this.validationDate()
    this.appointmentHematology = this._formBuilder.group({

      colesterol_seric_total: false,
      hdl_colesterol: false,
      ldl_colesterol: false,
      trigliceride_serice: false,
      glicemie: false,
      tgo: false,
      tgp: false,
      uree_serica: false,
      creatina_serica: false,
      potasiu_seric: false,
      magneziu_seric: false,
      acid_uric: false,
      calciu_ionic_seric: false,
      calciu_seric_total: false,
      inr_cu_interpretare: false,
      hemoleucograma_completa: false,
      t3: false,
      t4: false,
      tsh: false      
    })
      
  }

  setAppointmentHema(){
    this.appointment.colesterol_seric_total= this.appointmentHematology.value.colesterol_seric_total,
    this.appointment.hdl_colesterol= this.appointmentHematology.value.hdl_colesterol,
    this.appointment.ldl_colesterol= this.appointmentHematology.value.ldl_colesterol,
    this.appointment.trigliceride_serice= this.appointmentHematology.value.trigliceride_serice,
    this.appointment.glicemie= this.appointmentHematology.value.glicemie,
    this.appointment.tgo= this.appointmentHematology.value.tgo,
    this.appointment.tgp= this.appointmentHematology.value.tgp,
    this.appointment.uree_serica= this.appointmentHematology.value.uree_serica,
    this.appointment.creatina_serica= this.appointmentHematology.value.creatina_serica,
    this.appointment.potasiu_seric= this.appointmentHematology.value.potasiu_seric,
    this.appointment.magneziu_seric= this.appointmentHematology.value.magneziu_seric,
    this.appointment.acid_uric= this.appointmentHematology.value.acid_uric,
    this.appointment.calciu_ionic_seric= this.appointmentHematology.value.calciu_ionic_seric,
    this.appointment.calciu_seric_total= this.appointmentHematology.value.calciu_seric_total,
    this.appointment.inr_cu_interpretare= this.appointmentHematology.value.inr_cu_interpretare,
    this.appointment.hemoleucograma_completa= this.appointmentHematology.value.hemoleucograma_completa,
    this.appointment.t3= this.appointmentHematology.value.t3,
    this.appointment.t4= this.appointmentHematology.value.t4,
    this.appointment.tsh= this.appointmentHematology.value.tsh,
    
    this.appointment.dataAppointmentHematology = this.dataEdit,
    this.appointment.hourAppointmentHematology = this.hourP
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
    return !this.blockedData.find(x=>x==time) && day !==0 && day !==6
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
 openAddRadiology(){
  this.addHematologyAppointment()
  console.log("Appointment radio")
  this.dialogref.close("addHemaDone");
  this.dialog.open(DialogAddAppointmentsRadiologyComponent,{
   width: '30%'
  });
  
 }
 addHematologyAppointment(){
   this.setAppointmentHema()
  console.log("Add appointment hema")
   console.log(this.appointment)
  this._doctor.addAppointmenthematology(this.appointment).subscribe((res)=>{
     console.log(res)
    
   })

 }
}
