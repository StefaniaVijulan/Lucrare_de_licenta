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

@Component({
  selector: 'app-secretar-pacienti',
  templateUrl: './secretar-pacienti.component.html',
  styleUrls: ['./secretar-pacienti.component.scss']
})
export class SecretarPacientiComponent implements OnInit {

  displayedColumns = ['dataA','hour','cnp','firstName', 'lastName', 'emailUser', 'numberUser'];
  dataSource !: MatTableDataSource<any>;


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
    this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
    this.allFutureAppointments();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allFutureAppointments(){
    
    return this._secretar.getAllCurrentAppointments()
    .subscribe((res:any)=>{
     this.dataSource =  new MatTableDataSource(res);
     this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    }
   )};
  getModeratorRol(){
      if(this._service.getRole()=="MODERATOR")
        return true
      else
        return false
  };
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 /* readMoreDialog(element: any) {
    //salvam idul internarii pentru care vrem sa aflam mai multe detalii
    this._secretar.hospitalizationNo = element.registrationNoHospitalization
 
    this.dialog.open(DialogMoreInfoPacientComponent, {
          width: '40%',
          data: element
        }).afterClosed().subscribe(val => {
          
          if (val === "done") {
            this.allHospitalization();
          }
        })
  }*/
}
