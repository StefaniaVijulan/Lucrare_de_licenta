import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { DialogAddUserComponent } from '../moderator/dialog-add-user/dialog-add-user.component';
import { map, filter, switchMap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Data } from '@angular/router';
import { Appointment } from 'src/app/interfaces/appointment';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DialogAppointmentSuccessComponent } from '../dialog-appointment-success/dialog-appointment-success.component';
@Component({
  selector: 'app-dialog-add-appointment',
  templateUrl: './dialog-add-appointment.component.html',
  styleUrls: ['./dialog-add-appointment.component.scss']
})
export class DialogAddAppointmentComponent implements OnInit {
  private baseUrl = environment.baseUrl;
  tomorrowD:any = new Date().getDate()+1;
  blockedData: any
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  hourInterval: any;
  private luna: string
  appointment: Appointment = new Appointment();
  firstFormGroup!: FormGroup;
  constructor(private _formBuilder: FormBuilder, private _http: HttpClient, private dialog: MatDialog, private _appointment: AppointmentService, private dialogref: MatDialogRef < DialogAddAppointmentComponent >) { }

  ngOnInit() {
    this.validationDate()
    this.firstFormGroup = this._formBuilder.group({
      cnp: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      numberUser: ['', Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      emailUser: ['']})
      
  }
  
  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    return !this.blockedData.find(x=>x==time) && day !==0 && day !==6;
  };

  validationDate(){
    this._appointment.getDataBlock().subscribe((res)=>{
      console.log(res)
      this.blockedData = new Array();
      for(let dat in res){
        this.blockedData.add(new Date(dat).getTime())
      }
      console.log(res)
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
     this._appointment.getValidationData(this.dataEdit).subscribe((res)=>{
       this.hourInterval = res
       console.log(res)
     })
  }
  setAppointment(){
    this.appointment.cnp = this.firstFormGroup.value.cnp
    this.appointment.firstName = this.firstFormGroup.value.firstName
    this.appointment.lastName = this.firstFormGroup.value.lastName
    this.appointment.numberUser = this.firstFormGroup.value.numberUser
    this.appointment.emailUser = this.firstFormGroup.value.emailUser
    this.appointment.dataA = this.dataEdit
    this.appointment.hour = this.hourP
  }
  addAppointment(){
    this.setAppointment()
    console.log(this.appointment)
    return this._appointment.addAppointment(this.appointment).subscribe((res)=>{
      console.log(res)
      this.dialogref.close("save");
      this.dialog.open(DialogAppointmentSuccessComponent,{
      width: '30%'
    })
   })
  
  }

  

}
