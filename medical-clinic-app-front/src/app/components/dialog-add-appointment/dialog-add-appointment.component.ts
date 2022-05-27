import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
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
  selectedCardio: any;
  cardiologList: any;
  bloackValid: any;

  msg=''

  loading = false;
  constructor(private _formBuilder: FormBuilder, private _http: HttpClient, private dialog: MatDialog, private _appointment: AppointmentService, private dialogref: MatDialogRef < DialogAddAppointmentComponent >) { }

  ngOnInit() {
    console.log("next date =>", this.tomorrowD)
    console.log("Intra in on init")
    this.cardiologList = this._appointment.doctorListService
    this.firstFormGroup = this._formBuilder.group({
      cnp: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      numberUser: ['', Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      emailUser:['', Validators.compose([Validators.required, Validators.email])]
    })
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
    return !this._appointment.blockedDataAppointment.find(x=>x==time) && day !==0 && day !==6;
  };

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
    this.appointment.numberUser = this.firstFormGroup.value.numberUser
    this.appointment.emailUser = this.firstFormGroup.value.emailUser
    this.appointment.dataA = this.dataEdit
    this.appointment.hour = this.hourP
  }
  addAppointment(){
    this.loading = true;
    this.setAppointment()
    console.log("selectedCardio")
    console.log(this.selectedCardio)
    console.log(this.appointment)
    return this._appointment.addAppointment(this.selectedCardio,this.appointment).subscribe((res)=>{
      this.loading = false;
      console.log(res)
      if(res == 0){
      
        this.dialogref.close("save");
        this.dialog.open(DialogAppointmentSuccessComponent,{
        width: '30%'
      })}
      else if(res ==1 ){
        this.msg ='Există deja o programare făcută pe acest CNP'
      } else
      if(res == 2){
        this.msg = 'Exista deja un cont creat pentru acest cnp. Pentru a face o progrmare vă recomandăm să vă conectați la acesta'
      }
   })
  
  }

  

}
