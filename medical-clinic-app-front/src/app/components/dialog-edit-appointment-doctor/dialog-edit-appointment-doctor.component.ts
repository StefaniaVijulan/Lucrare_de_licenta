import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-edit-appointment-doctor',
  templateUrl: './dialog-edit-appointment-doctor.component.html',
  styleUrls: ['./dialog-edit-appointment-doctor.component.scss']
})
export class DialogEditAppointmentDoctorComponent implements OnInit {

  blockedData: any
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  acordGDPR: boolean = false;
  hourInterval: any;
  luna: string;
  formG: FormGroup;
  selectedCardio:any = localStorage.getItem("cnp")
  editAppointment: Appointment = new Appointment()
  listCardio: Object;
  loading: boolean = false;
  constructor(private _formBuilder: FormBuilder,
    private _doctor: DoctorService,
    private _appointment: AppointmentService,
    private dialogref: MatDialogRef < DialogEditAppointmentDoctorComponent >,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    

    this.formG = this._formBuilder.group({
      cnp: [this._doctor.appointmentListService.cnp],
      firstName: [this._doctor.appointmentListService.firstName],
      lastName: [this._doctor.appointmentListService.lastName],
      emailUser: [this._doctor.appointmentListService.emailUser],
      numberUser: [this._doctor.appointmentListService.numberUser],
  });
      
    this.formG.controls['cnp'].disable()
    this.formG.controls['firstName'].disable()
    this.formG.controls['lastName'].disable()


  }

  validationDate(){
    console.log("validation date => ", localStorage.getItem("cnp"))

    this._appointment.getDataBlock(this.selectedCardio).subscribe((res)=>{
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
     this._appointment.getValidationData(this.selectedCardio, this.dataEdit).subscribe((res)=>{
       this.hourInterval = res
       console.log(res)
     })
  }
  editAppointmentF(){ 
  //  this.loading = true;
    this.editAppointment.cnp = this._doctor.appointmentListService.cnp
    this.editAppointment.lastName = this._doctor.appointmentListService.lastName
    this.editAppointment.firstName = this._doctor.appointmentListService.firstName
    this.editAppointment.emailUser = this._doctor.appointmentListService.emailUser
    this.editAppointment.numberUser = this._doctor.appointmentListService.numberUser
    this.editAppointment.dataA = this.dataEdit
    this.editAppointment.hour =this.hourP

    console.log("edit fisa")
    console.log(this.editAppointment)
    console.log(this.selectedCardio)
    console.log(this.editAppointment.patient)
    console.log(this.dataEdit)
    console.log(this.hourP)

    this._doctor.editAppointment(this._doctor.appointmentListService.id, this.editAppointment).subscribe((res)=>{
      console.log(res)
    })
  
  }
}
