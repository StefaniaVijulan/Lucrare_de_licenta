import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddAppointmentComponent } from 'src/app/components/dialog-add-appointment/dialog-add-appointment.component';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private dialog: MatDialog, public _appointment: AppointmentService ) { }

  ngOnInit() {
  //  this.allCardiolog()
  }
  /*
  allCardiolog(){
    this._appointment.allCardiolog().subscribe((res)=>{
      this._appointment.doctorListService = res
      console.log(this._appointment.doctorListService)
    })
  }
  openAppointmentDialog(){
    
    this.dialog.open(DialogAddAppointmentComponent,{
     width: '40%'
    });
  }*/
}
