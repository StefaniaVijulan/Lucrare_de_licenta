import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-dialog-edit-appointment',
  templateUrl: './dialog-edit-appointment.component.html',
  styleUrls: ['./dialog-edit-appointment.component.scss']
})
export class DialogEditAppointmentComponent implements OnInit {
  blockedData: any
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  acordGDPR: boolean = false;
  hourInterval: any;
  luna: string;
  formG: FormGroup;
  selectedCardio: any;
  editAppointment: Appointment = new Appointment()
  listCardio: Object;
  loading: boolean = false;
  constructor(private _formBuilder: FormBuilder,
    private _secretar: SecretarService,
    private _appointment: AppointmentService,
    private dialogref: MatDialogRef < DialogEditAppointmentComponent >,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.allCardio();

    this.formG = this._formBuilder.group({
      cnp: [this._secretar.appointmentListService.cnp],
      firstName: [this._secretar.appointmentListService.firstName],
      lastName: [this._secretar.appointmentListService.lastName],
      emailUser: [this._secretar.appointmentListService.emailUser],
      numberUser: [this._secretar.appointmentListService.numberUser],
  });
      
    this.formG.controls['cnp'].disable()
    this.formG.controls['firstName'].disable()
    this.formG.controls['lastName'].disable()

    console.log(this._secretar.appointmentListService)

  }
  onCardioChange(event) {
    this.dateP = ""
    this.hourP = ""
    this.validationDate()
  }
  allCardio(){
    this._secretar.allCardiolog().subscribe((res)=>{
      console.log(res)
      this.listCardio = res
    })
  }
  validationDate(){
    
    this._appointment.getDataBlock(this.selectedCardio).subscribe((res)=>{
      console.log(res)
      for(let dat of res){
        console.log(dat)
        console.log(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] )
        console.log(new Date(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] ).getTime())
        this._appointment.blockedDataAppointment.push(new Date(dat.split("-")[1] + "/" +dat.split("-")[0] + "/" + dat.split("-")[2] ).getTime());
        console.log(this._appointment.blockedDataAppointment)
      }
      
      console.log(this._appointment.blockedDataAppointment)
    })
  }

  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    

    console.log("this._appointment.blockedDataAppointment =>",this._appointment.blockedDataAppointment)
    return !this._appointment.blockedDataAppointment.find(x=>x==time) && day !==0 && day !==6;
  };

  
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
    this.editAppointment.cnp = this._secretar.appointmentListService.cnp
    this.editAppointment.lastName = this._secretar.appointmentListService.lastName
    this.editAppointment.firstName = this._secretar.appointmentListService.firstName
    this.editAppointment.emailUser = this._secretar.appointmentListService.emailUser
    this.editAppointment.numberUser = this._secretar.appointmentListService.numberUser
    this.editAppointment.dataA = this.dataEdit
    this.editAppointment.hour =this.hourP

    console.log("edit fisa")
    console.log(this.editAppointment)
    console.log(this.selectedCardio)
    console.log(this.editAppointment.patient)
    console.log(this.dataEdit)
    console.log(this.hourP)

    this._secretar.editAppointment(this._secretar.appointmentListService.id, this.selectedCardio, this.editAppointment).subscribe((res)=>{
      console.log(res)
      this.dialogref.close("edit")
      window.location.reload()
    })
    /*
    this.setFisa()
    this._doctor.editFisaPatient(this.newfisaPatient).subscribe((res)=>{
      this.formG.reset();
      this.formG2.reset();
      this.formG3.reset();
      this.formG4.reset();
      //se intoarce cu textul save
      this.dialogref.close("fisa");
      console.log("Add fisas")
    })*/
  }
}
