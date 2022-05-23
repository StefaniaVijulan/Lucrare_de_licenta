import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { environment } from 'src/environments/environment';
import { DialogAppointmentSuccessComponent } from '../dialog-appointment-success/dialog-appointment-success.component';

@Component({
  selector: 'app-dialog-add-appointment-by-doctor',
  templateUrl: './dialog-add-appointment-by-doctor.component.html',
  styleUrls: ['./dialog-add-appointment-by-doctor.component.scss']
})
export class DialogAddAppointmentByDoctorComponent implements OnInit {
  private baseUrl = environment.baseUrl;
  tooltipVariable = "Prin completarea formularului declar ca sunt de acord cu folosirea datelor mele personale de catre clinica. Puteti consulta mai multe detalii despre prelucrarea datelor dvs. personale in Politica de prelucrare disponibila clinica noastră. Prezentul consimtamant este acordat de bunavoie si poate fi oricand revocat in scris la adresa cabinetului sau prin email la adresa gdpr@medicalclinicapp.com."
  tomorrowD:any = new Date().getDate()+1;
  dateP: string;
  ziua: string;
  hourP:string;
  dataEdit: string;
  acordGDPR: boolean = false;
  hourInterval: any;
  luna: string;
  appointment: Appointment = new Appointment();
  firstFormGroup!: FormGroup;
  selectedCardio: any = localStorage.getItem("cnp");
  cardiologList: any;
  bloackValid: any;
  loading = false;
  constructor(private _formBuilder: FormBuilder,private _doctor:DoctorService, private _http: HttpClient, private dialog: MatDialog, private _appointment: AppointmentService, private dialogref: MatDialogRef < DialogAddAppointmentByDoctorComponent >) { }

  ngOnInit() {
    console.log("Intra in on init")
    this.cardiologList = this._appointment.doctorListService
    this.firstFormGroup = this._formBuilder.group({
      cnp: [this._doctor.patientService.cnp, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: [this._doctor.patientService.firstName, Validators.required],
      lastName: [this._doctor.patientService.lastName, Validators.required],
    })

    this.firstFormGroup.controls['cnp'].disable()
    this.firstFormGroup.controls['firstName'].disable()
    this.firstFormGroup.controls['lastName'].disable()
  }

  onCardioChange(event) {
    this.dateP = ""
    this.hourP = ""
    this.validationDate()
  }

  myFilter = (d: Date): boolean => {
    const time=d.getTime()
    const day = d.getDay();
    console.log(time)

    console.log(this._appointment.blockedDataAppointment)
    return !this._appointment.blockedDataAppointment.find(x=>x==time);//&& day !==0 && day !==6;
  };

  validationDate(){

    console.log(this.selectedCardio)
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
  setAppointment(){
    this.appointment.cnp = this.firstFormGroup.value.cnp
    this.appointment.firstName = this.firstFormGroup.value.firstName
    this.appointment.lastName = this.firstFormGroup.value.lastName
    this.appointment.numberUser =this._doctor.patientService.numberUser
    this.appointment.emailUser = this._doctor.patientService.emailUser
    this.appointment.dataA = this.dataEdit
    this.appointment.hour = this.hourP
  }
  addAppointment(){
    this.setAppointment()
    console.log("selectedCardio")
    console.log(this.selectedCardio)
    console.log(this.appointment)
    return this._appointment.addAppointment(this.selectedCardio,this.appointment).subscribe((res)=>{
      console.log(res)
      if(res){
        this.loading = true;
    setTimeout(() => this.loading = false, 2000);
      this.dialogref.close("save");
      this.dialog.open(DialogAppointmentSuccessComponent,{
      width: '30%'
    })}
   })
  
  }

  

}
