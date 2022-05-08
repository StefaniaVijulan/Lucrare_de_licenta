import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddAppointmentComponent } from 'src/app/components/dialog-add-appointment/dialog-add-appointment.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private dialog: MatDialog, ) { }

  ngOnInit() {
  }

  openAppointmentDialog(){
    
    this.dialog.open(DialogAddAppointmentComponent,{
     width: '40%'
    });
  }
}
