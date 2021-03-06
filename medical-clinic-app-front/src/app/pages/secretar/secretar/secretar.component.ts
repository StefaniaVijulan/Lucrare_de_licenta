import {  Component,  OnInit,  ViewChild} from '@angular/core';
import {  FormGroup,  FormControl} from '@angular/forms';
import {  FlexLayoutModule} from '@angular/flex-layout';

import {  SecretarService} from 'src/app/services/secretar/secretar.service';
;
import {  timeStamp} from 'console';
import {  DialogMoreInfoPacientComponent} from 'src/app/components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import {  MatDialog,  MatPaginator,  MatSort,  MatTableDataSource} from '@angular/material';
import {  DialogAddPacientComponent} from 'src/app/components/dialog-add-pacient/dialog-add-pacient.component';
import { Cardiolog } from 'src/app/interfaces/cardiolog';
import { Patient } from 'src/app/interfaces/patient';
import { Appointment } from 'src/app/interfaces/appointment';
import { DialogEditAppointmentComponent } from 'src/app/components/dialog-edit-appointment/dialog-edit-appointment.component';
@Component({
  selector: 'app-secretar',
  templateUrl: './secretar.component.html',
  styleUrls: ['./secretar.component.scss']
})
export class SecretarComponent implements OnInit {

  listAppointment: any
  currentList: any;
  pacientL: any;
  currentItemsToShow: any;
  listCardiolog: any;
  newPacient: Patient = new Patient();
 
  existaP: boolean;

  @ViewChild(MatPaginator, {
    static: true
  })
  paginator!: MatPaginator;

  @ViewChild(MatSort, {
    static: true
  })
  sort!: MatSort;
  constructor(public _secretar: SecretarService, private dialog: MatDialog) {}

  ngOnInit() {
    this.allTodayAppointments();
   
    this. allCardiolog();
  }
  editeazaPacient(element){
    this._secretar.pacientService = element;
    console.log("element=> ", this._secretar.pacientService)
  }


  openAddDialog(element: any) {
    console.log(element.cnp)
    this._secretar.pacientService.cnp = element.cnp;
    this._secretar.pacientService.firstName = element.firstName;
    this._secretar.pacientService.lastName = element.lastName;
    this._secretar.pacientService.numberUser = element.numberUser;
    this._secretar.pacientService.emailUser = element.emailUser;
    console.log(this._secretar.pacientService)
    this._secretar.checkPatient(element.cnp).subscribe((res)=>{
      if(res == null){
        this._secretar.existaPacient = false;
        this.dialog.open(DialogAddPacientComponent, {
          width: '35%',
          panelClass: 'my-panel'
        })
      }else{
        this.dialog.open(DialogAddPacientComponent, {
          width: '35%',
          panelClass: 'my-panel'
        })
        this._secretar.existaPacient = true;
      }
    })
    
  }; 
  allTodayAppointments(){
    return this._secretar.getAllTodayAppointments().subscribe((res)=>{
      this.listAppointment = res
      this.currentItemsToShow = res
      console.log(res)
    })
  }
  allCardiolog(){
    return this._secretar.allCardiolog().subscribe((res)=>{
      this._secretar.doctorListService = res;
      console.log(this._secretar.doctorListService)
    })
  }

 
  /*onPageChange($event) {
    this.listAppointment =  this.currentItemsToShow.slice($event.pageIndex*$event.pageSize, $event.pageIndex*$event.pageSize + $event.pageSize);
  }
  https://stackblitz.com/edit/angular-paginator-example-customized?file=app%2Fpaginator-overview-example.ts
  
  */
}