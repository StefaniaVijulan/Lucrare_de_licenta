import { Component, OnInit,HostListener } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddAppointmentComponent } from 'src/app/components/dialog-add-appointment/dialog-add-appointment.component';
import { DialogContComponent } from 'src/app/components/dialog-cont/dialog-cont.component';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  listUsers: any;
  constructor(private dialog: MatDialog, public _service: AuthService, public _appointment: AppointmentService) { }

  ngOnInit() {
    this.allUsers()
    this.allCardio()
  }
 allCardio(){
  this._appointment.allCardiolog().subscribe((res)=>{
    this._appointment.doctorListService = res
    console.log(this._appointment.doctorListService)
  })
 }
  allUsers(){
    this._service.getAllUsers().subscribe((res)=>{
      this._service.userListService = res
      this.listUsers = res;
      console.log(this._service.userListService)
    })
  }
  openApp(){
    this.dialog.open(DialogContComponent,{
      width: '20%',
      panelClass: 'my-panel'
     });
  }
 /* openAppointmentDialog(){
    
    this.dialog.open(DialogContComponent,{
     width: '30%',
     panelClass: 'my-panel'
    });
  }*/

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
