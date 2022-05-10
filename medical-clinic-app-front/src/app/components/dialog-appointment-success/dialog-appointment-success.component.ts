import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';

@Component({
  selector: 'app-dialog-appointment-success',
  templateUrl: './dialog-appointment-success.component.html',
  styleUrls: ['./dialog-appointment-success.component.scss']
})
export class DialogAppointmentSuccessComponent implements OnInit {
  blockedData: any;
  dateP: string;
  msg: string ="";
  constructor(  private _appointment: AppointmentService,) { }

  ngOnInit() {
   // this.validationDate()
  }
  

}
