import { Component, OnInit,HostListener } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddAppointmentComponent } from 'src/app/components/dialog-add-appointment/dialog-add-appointment.component';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  listDoctors: any;
  constructor(private dialog: MatDialog, public _appointment: AppointmentService ) { }

  ngOnInit() {
    this.allCardiolog()
  }
 
  allCardiolog(){
    this._appointment.allCardiolog().subscribe((res)=>{
      this._appointment.doctorListService = res
      this.listDoctors = res;
      console.log(this._appointment.doctorListService)
    })
  }
  openAppointmentDialog(){
    
    this.dialog.open(DialogAddAppointmentComponent,{
     width: '40%'
    });
  }

  bgVariable:boolean = false;
  @HostListener('document:scroll', ['$event'])
  onScroll(event){
    console.log("intra in onscroll")
    if(document.body.scrollTop > 60 || document.documentElement.scrollTop > 60){
      console.log("intra in if")
      this.bgVariable = true;
    }else{
      console.log("intra in else")
      this.bgVariable = false;
    }
  }
  
  
}
