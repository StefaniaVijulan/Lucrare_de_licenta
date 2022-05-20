import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import { MatDialog, MatPaginator, MatSort, MAT_DIALOG_DATA, Sort } from '@angular/material';
import { DialogComponent } from 'src/app/components/moderator/dialog/dialog.component';
import { DialogAddUserComponent } from 'src/app/components/moderator/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from 'src/app/components/moderator/dialog-delete-user/dialog-delete-user.component';
import { element } from 'protractor';
import { DialogResetPassComponent } from 'src/app/components/moderator/dialog-reset-pass/dialog-reset-pass.component';
import { SecretarService } from 'src/app/services/secretar/secretar.service';
import { DialogAddPacientComponent } from 'src/app/components/dialog-add-pacient/dialog-add-pacient.component';
import { DialogMoreInfoPacientComponent } from 'src/app/components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { DialogEditAppointmentComponent } from 'src/app/components/dialog-edit-appointment/dialog-edit-appointment.component';
import { Appointment } from 'src/app/interfaces/appointment';

@Component({
  selector: 'app-secretar-pacienti',
  templateUrl: './secretar-pacienti.component.html',
  styleUrls: ['./secretar-pacienti.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SecretarPacientiComponent  {
  dataSource !: MatTableDataSource<any>;
  columnsToDisplay = ['dataA','hour','cnp','firstName', 'lastName', 'emailUser', 'numberUser','action'];
  expandedElement: Appointment | null;

  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;

  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;

  constructor(private dialog: MatDialog, 
    public _secretar: SecretarService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }
       ngOnInit(): void {
       // this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
        this.allFutureAppointments();
       }
       allFutureAppointments(){
    
        return this._secretar.getAllCurrentAppointments()
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
  this._secretar.appointmentListService = element;
  console.log("appointment =>", this._secretar.appointmentListService)
  console.log("edit appointment")
  this.dialog.open(DialogEditAppointmentComponent, {
    width: '35%',
    panelClass: 'my-panel'
  })
}
deleteUser(data: any){
  console.log("deleteUser=>",data)
  this._secretar.deleteAppointment(data).subscribe((res)=>{
    console.log("data in this=>", res)
    this.allFutureAppointments()
  })
}
};
