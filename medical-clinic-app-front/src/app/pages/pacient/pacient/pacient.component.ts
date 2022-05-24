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
  constructor(private dialog: MatDialog, public _service: AuthService, public _patient: PacientService) {}

  ngOnInit() {
    this.allCardio()
this.getNextAppoinement()
  }
  ngAfterViewInit() {
 
    this.getData()
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
      this.cardiologName =  this.nextAppointment.cardiolog.lastName + " " + this.nextAppointment.cardiolog.firstName;
      console.log(this.formatDate)
      //this.getData()
    })
  }
  getData() {
    this._patient.getNextAppointment().subscribe((res) => {
      this.nextAppointment = res;
      this.formatDate = this.nextAppointment.dataA
      this.currentDate = new Date()
      console.log("current =>", this.currentDate)
      const options = {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric'
      };
      this.targetDate = new Date(this.formatDate.split("-")[1] + "/" + this.formatDate.split("-")[0] + "/" + this.formatDate.split("-")[2] + " " + this.nextAppointment.hour)

      console.log("target =>", this.targetDate)
        this.cDateMilisec = this.currentDate.getTime();
        this.tDateMilisec = this.targetDate.getTime();
        
        this.difference = this.tDateMilisec - this.cDateMilisec;

        this.seconds = Math.floor(this.difference/1000)
        this.minutes = Math.floor(this.seconds/60)
        this.hours = Math.floor(this.minutes/60)
        this.days = Math.floor(this.hours/24)

        this.hours %=24;
        this.minutes %=60;
        this.seconds %=60;
        this.hours = this.hours < 10 ?'0' + this.hours : this.hours;
        this.minutes = this.minutes < 10 ? '0' + this.minutes : this.minutes;
        this.seconds = this.seconds < 10 ? '0' + this.seconds : this.seconds;

        document.getElementById('days').innerHTML = this.days;
        document.getElementById('hours').innerHTML = this.hours;
        document.getElementById('mins').innerHTML = this.minutes;
        document.getElementById('seconds').innerHTML = this.seconds;
        
        setInterval(this.getData, 1000)  })
  }
}