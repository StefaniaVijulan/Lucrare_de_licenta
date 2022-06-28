import {
  Component,
  OnInit
} from '@angular/core';
import {
  MatDialog
} from '@angular/material';
import {
  DialogAddAppointmentByPacientComponent
} from 'src/app/components/pacient/dialog-add-appointment-by-pacient/dialog-add-appointment-by-pacient.component';
import { ReprogramareAppComponent } from 'src/app/components/pacient/reprogramare-app/reprogramare-app.component';

import {
  AppointmentService
} from 'src/app/services/appointment/appointment.service';
import {
  AuthService
} from 'src/app/services/auth/auth.service';
import {
  PacientService
} from 'src/app/services/pacient/pacient.service';

@Component({
  selector: 'app-pacient',
  templateUrl: './pacient.component.html',
  styleUrls: ['./pacient.component.scss']
})
export class PacientComponent implements OnInit {

  //NextAppointemnt 
  existAppoinmtemt: boolean = true;
  nextAppointment: any;
  currentDate: Date;
  formatDate: any;
  targetDate: any;
  cDateMilisec: any;
  tDateMilisec: any;
  difference: any;
  seconds: any;
  minutes: any;
  days: any;
  cardiologName: any;
  hours: any;
  //NextAppointmentHematology
  nextAppointmentH: any;
  currentDateH: Date;
  formatDateH: any;
  targetDateH: any;
  cDateMilisecH: any;
  tDateMilisecH: any;
  differenceH: any;
  secondsH: any;
  minutesH: any;
  daysH: any;
  hoursH: any;
  //NextAppointemntRadiology
  nextAppointmentR: any;
  currentDateR: Date;
  formatDateR: any;
  targetDateR: any;
  cDateMilisecR: any;
  tDateMilisecR: any;
  differenceR: any;
  secondsR: any;
  minutesR: any;
  daysR: any;
  hoursR: any;
  constructor(private dialog: MatDialog, public _service: AuthService, public _patient: PacientService) {}

  ngOnInit() {
    this.allCardio()
    this.getNextAppoinement()
  }
  ngAfterViewInit() {

    this.getData()
    this.getDataHematology()
    this.getDataRadiology()
  }
  allCardio() {
    this._patient.allCardiolog().subscribe((res) => {
      this._patient.doctorListService = res;
      console.log(res)
    })

  }
  addAppointment() {
    console.log("Add appoinmtemt")
    this.dialog.open(DialogAddAppointmentByPacientComponent, {
      width: '30%',
      panelClass: 'my-panel'
    });
  }
  getNextAppoinement() {
    this._patient.getNextAppointment().subscribe((res) => {
      
      this.nextAppointment = res;
      this.formatDate = this.nextAppointment.dataA
      this._patient.cardiologService = this.nextAppointment.cardiolog.cnp
      console.log(" this._patient.cardiologService => ", this._patient.cardiologService)
      this.cardiologName = this.nextAppointment.cardiolog.lastName + " " + this.nextAppointment.cardiolog.firstName;
      console.log(this.formatDate)
      //this.getData()
    })
  }
  getData() {
   

    this._patient.getNextAppointment().toPromise().then((res) => {
      
      console.log("res", res)
      if(res){
        this.existAppoinmtemt =  false
        console.log(this.existAppoinmtemt)
        this.nextAppointment = res;
        this.formatDate = this.nextAppointment.dataA
        this.currentDate = new Date()
        console.log("current =>", this.currentDate)
  
        this.targetDate = new Date(this.formatDate.split("-")[1] + "/" + this.formatDate.split("-")[0] + "/" + this.formatDate.split("-")[2] + " " + this.nextAppointment.hour)
  
        console.log("target =>", this.targetDate)
        this.cDateMilisec = this.currentDate.getTime();
        this.tDateMilisec = this.targetDate.getTime();
  
        this.difference = this.tDateMilisec - this.cDateMilisec;
  
        this.seconds = Math.floor(this.difference / 1000)
        this.minutes = Math.floor(this.seconds / 60)
        this.hours = Math.floor(this.minutes / 60)
        this.days = Math.floor(this.hours / 24)
  
        this.hours %= 24;
        this.minutes %= 60;
        this.seconds %= 60;
        this.hours = this.hours < 10 ? '0' + this.hours : this.hours;
        this.minutes = this.minutes < 10 ? '0' + this.minutes : this.minutes;
        this.seconds = this.seconds < 10 ? '0' + this.seconds : this.seconds;
  
        document.getElementById('days').innerHTML = this.days;
        document.getElementById('hours').innerHTML = this.hours;
        document.getElementById('mins').innerHTML = this.minutes;
        document.getElementById('seconds').innerHTML = this.seconds;
        
 setInterval(this.getData, 1000);
        console.log("rhis sec", this.seconds)
     
      }else{
        console.log("nu ")
        console.log(this.existAppoinmtemt)
      }
      
      
    })

  }
  getDataHematology(){
    this._patient.getNextAppointmentHematology().toPromise().then((res) => {
      
        this.nextAppointmentH = res;
        this.formatDateH = this.nextAppointmentH.dataAppointmentHematology
        this.currentDateH = new Date()
        console.log("current =>", this.currentDateH)
  
        this.targetDateH = new Date(this.formatDateH.split("-")[1] + "/" + this.formatDateH.split("-")[0] + "/" + this.formatDateH.split("-")[2] + " " + this.nextAppointmentH.hourAppointmentHematology)
  
        console.log("target =>", this.targetDateH)
        this.cDateMilisecH = this.currentDateH.getTime();
        this.tDateMilisecH = this.targetDateH.getTime();
  
        this.differenceH = this.tDateMilisecH - this.cDateMilisecH;
  
        this.secondsH = Math.floor(this.differenceH / 1000)
        this.minutesH = Math.floor(this.secondsH / 60)
        this.hoursH = Math.floor(this.minutesH / 60)
        this.daysH = Math.floor(this.hoursH / 24)
  
        this.hoursH %= 24;
        this.minutesH %= 60;
        this.secondsH %= 60;
        this.hoursH = this.hoursH < 10 ? '0' + this.hoursH : this.hoursH;
        this.minutesH = this.minutesH < 10 ? '0' + this.minutesH : this.minutesH;
        this.secondsH = this.secondsH < 10 ? '0' + this.secondsH : this.secondsH;
  
        document.getElementById('daysH').innerHTML = this.daysH;
        document.getElementById('hoursH').innerHTML = this.hoursH;
        document.getElementById('minsH').innerHTML = this.minutesH;
        document.getElementById('secondsH').innerHTML = this.secondsH;
               setInterval(this.getDataHematology, 1000);
      
  
 
})
  }
  getDataRadiology(){
    this._patient.getNextAppointmentRadiology().toPromise().then((res) => {
      
        this.nextAppointmentR = res;
        this.formatDateR = this.nextAppointmentR.dataAppointmentRadiology
        this.currentDateR = new Date()
        console.log("current =>", this.currentDateR)
  
        this.targetDateR = new Date(this.formatDateR.split("-")[1] + "/" + this.formatDateR.split("-")[0] + "/" + this.formatDateR.split("-")[2] + " " + this.nextAppointmentR.hourAppointmentRadiology)
  
        console.log("target =>", this.targetDateR)
        this.cDateMilisecR = this.currentDateR.getTime();
        this.tDateMilisecR = this.targetDateR.getTime();
  
        this.differenceR = this.tDateMilisecR - this.cDateMilisecR;
  
        this.secondsR = Math.floor(this.differenceR / 1000)
        this.minutesR = Math.floor(this.secondsR / 60)
        this.hoursR = Math.floor(this.minutesR / 60)
        this.daysR = Math.floor(this.hoursR / 24)
  
        this.hoursR %= 24;
        this.minutesR %= 60;
        this.secondsR %= 60;
        this.hoursR = this.hoursR < 10 ? '0' + this.hoursR : this.hoursR;
        this.minutesR = this.minutesR < 10 ? '0' + this.minutesR : this.minutesR;
        this.secondsR = this.secondsR < 10 ? '0' + this.secondsR : this.secondsR;
  
        document.getElementById('daysR').innerHTML = this.daysR;
        document.getElementById('hoursR').innerHTML = this.hoursR;
        document.getElementById('minsR').innerHTML = this.minutesR;
        document.getElementById('secondsR').innerHTML = this.secondsR;
               setInterval(this.getDataRadiology, 1000);
      
  
})
  }
  reprogramareAppointment(element: any){
    this._patient.reprogrameazaType = element
    this.dialog.open(ReprogramareAppComponent,{
      panelClass: "dialog-responsive"
     })
    console.log("Appointment new", this._patient.reprogrameazaType)

  }
}