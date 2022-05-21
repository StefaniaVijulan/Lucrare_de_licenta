import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { DialogEditAppointmentDoctorComponent } from 'src/app/components/dialog-edit-appointment-doctor/dialog-edit-appointment-doctor.component';
import { DialogEditAppointmentComponent } from 'src/app/components/dialog-edit-appointment/dialog-edit-appointment.component';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentService } from 'src/app/services/appointment/appointment.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-doctor-future-appointments',
  templateUrl: './doctor-future-appointments.component.html',
  styleUrls: ['./doctor-future-appointments.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class DoctorFutureAppointmentsComponent implements OnInit {
  dataSource !: MatTableDataSource<any>;
  columnsToDisplay = ['dataA','hour','cnp','firstName', 'lastName', 'emailUser', 'numberUser','action'];
  expandedElement: Appointment | null;

  dateP: string="";
  formatedDate: any;
  luna: any;
  ziua: any;
  dataEdit: any;

  msg="";
  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;

  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;

  constructor(private dialog: MatDialog, 
    public _doctor: DoctorService,
    public _appointment: AppointmentService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }
       ngOnInit(): void {
       // this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
        this.allFutureAppointments();
       }
       allFutureAppointments(){
    
        return this._doctor.allFutureAppointementSpecifc()
        .subscribe((res:any)=>{
            this.dataSource =  new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        })
       

}
 applyFilter(event: Event) {
          const filterValue = (event.target as HTMLInputElement).value;
          this.dataSource.filter = filterValue.trim().toLowerCase();
        }
openEditAppointmentDialog(element: any){
  this._doctor.appointmentListService = element;
  console.log("appointment =>", this._doctor.appointmentListService)
  console.log("edit appointment")
  this.dialog.open(DialogEditAppointmentDoctorComponent, {
    width: '35%',
    panelClass: 'my-panel'
  })
}
checkDate(){
  if(this.dateP == "")
  {
    this.msg ="Selectați o dată!"
    console.log(this.msg)
  }else{
    var formatedDate = new Date(this.dateP).toLocaleString();
   // this._appointment.blockedData = formatedDate;
    console.log(" formateDate => " +formatedDate)
    if(formatedDate.split(",")[0].split("/")[0].length ==1)
    {
      this.luna = "0"+ formatedDate.split(",")[0].split("/")[0];
    }
    else{
      this.luna = formatedDate.split(",")[0].split("/")[0]
    }
    console.log(" luna => " +this.luna)
  
   if(formatedDate.split(",")[0].split("/")[1].length ==1)
    {
      this.ziua = "0"+ formatedDate.split(",")[0].split("/")[1];
    }
    else{
      this.ziua = formatedDate.split(",")[0].split("/")[1]
    }
    console.log(" zi => " +this.ziua)
  
    this.dataEdit = this.ziua +"-"+ this.luna + "-"+formatedDate.split(",")[0].split("/")[2]
    console.log(this.dataEdit)
    /*this._doctor.checkData(this.dataEdit).subscribe((res)=>{
      console.log(res)
      if(res == true){
        console.log("este true")
        console.log(this._appointment.blockedData)
        console.log(formatedDate)
        //this._appointment.blockedData.add(formatedDate)
        console.log(this._appointment.blockedData)
        
      }else{
        console.log("este false")
      }
    })*/
 
  }
 
}

};
