import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { PacientService } from 'src/app/services/pacient/pacient.service';
import { DialogAppointmentSuccessComponent } from '../../dialog-appointment-success/dialog-appointment-success.component';

@Component({
  selector: 'app-dialog-add-appointment-by-pacient',
  templateUrl: './dialog-add-appointment-by-pacient.component.html',
  styleUrls: ['./dialog-add-appointment-by-pacient.component.scss']
})
export class DialogAddAppointmentByPacientComponent implements OnInit {
  tomorrowD:any = new Date().getDate()+1;
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  acordGDPR: boolean = false;
  hourInterval: any;
  luna: string;
  appointment: Appointment = new Appointment();
 
  selectedCardio: any;
  cardiologList: any;
  bloackValid: any;

  msg=''

  loading = false;
  constructor( private dialog: MatDialog, private _patient: PacientService, private dialogref: MatDialogRef < DialogAddAppointmentByPacientComponent >) { }

  ngOnInit() {
    console.log("Intra in on init")
    this.cardiologList = this._patient.doctorListService
   console.log("this.cardiologList",this.cardiologList)
   console.log("this.cardiologList from service",this._patient.doctorListService)
  }

  onCardioChange(event) {
    this.dateP = ""
    this.hourP = ""
    this.validationDate()
  }

  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    console.log(time)

    console.log(this._patient.blockedDataAppointment)
    return !this._patient.blockedDataAppointment.find(x=>x==time);//&& day !==0 && day !==6;
  };

  validationDate(){

    this._patient.getDataBlock(this.selectedCardio).subscribe((res)=>{
      console.log(res)
      for(let dat of res){
        console.log(dat)
        console.log(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] )
        console.log(new Date(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] ).getTime())
        this._patient.blockedDataAppointment.push(new Date(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] ).getTime());
        console.log(this._patient.blockedDataAppointment)
      }
      
      console.log(this._patient.blockedDataAppointment)
    })
  }
 
  
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
     this._patient.getValidationData(this.selectedCardio, this.dataEdit).subscribe((res)=>{
       this.hourInterval = res
       console.log(res)
     })
  }
  setAppointment(){
  
    this.appointment.dataA = this.dataEdit
    this.appointment.hour = this.hourP
  }
  addAppointment(){
    this.loading = true;
    this.setAppointment()
    console.log("selectedCardio")
    console.log(this.selectedCardio)
    console.log(this.appointment)
    return this._patient.addAppointment(this.selectedCardio,this.appointment).subscribe((res)=>{
      this.loading = false;
      console.log(res)
      if(res == 0){
      
        this.dialogref.close("save");
        this.dialog.open(DialogAppointmentSuccessComponent,{
        width: '30%'
      })}
      else if(res ==1 ){
        this.msg ='Există deja o programare făcută pe acest CNP'
      } else
      if(res == 2){
        this.msg = 'Exista deja un cont creat pentru acest cnp. Pentru a face o progrmare vă recomandăm să vă conectați la acesta'
      }
   })
  
  }
}