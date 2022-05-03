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

@Component({
  selector: 'app-secretar-pacienti',
  templateUrl: './secretar-pacienti.component.html',
  styleUrls: ['./secretar-pacienti.component.scss']
})
export class SecretarPacientiComponent implements OnInit {

  displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser','numberUser', 'role', 'action'];
  dataSource !: MatTableDataSource<any>;
  curantsSource !: MatTableDataSource<User>;
  secretariesSource !: MatTableDataSource<User>;
  imagistSource !: MatTableDataSource<User>;
  hematologSource !: MatTableDataSource<User> ;

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

    this.allPatients();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allPatients(){
    
    return this._secretar.getAllPacients()
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
  openAddDialog(){ 
    this.dialog.open(DialogAddPacientComponent,{
     width: '50%'
    })
 };
}
