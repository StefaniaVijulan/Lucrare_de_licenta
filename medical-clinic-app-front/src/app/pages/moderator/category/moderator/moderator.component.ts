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
import { DialogCancelDeleteUserComponent } from 'src/app/components/dialog-cancel-delete-user/dialog-cancel-delete-user.component';


@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']
})

export class ModeratorComponent implements OnInit {
 
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
    public _moderator: ModeratorService,
     private _http: HttpClient, 
      public _service: AuthService,
   ) {
      
       }

  ngOnInit(): void {

    this.allUsers();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allUsers(){
    
    return this._moderator.getAllUsers()
    .subscribe((res:any)=>{
    this.setRoleCase(res)
     console.log("response in allUsers - moderator.ts")
     this.dataSource =  new MatTableDataSource(res);
     this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    }
   )};
   
  openDialog(){ 
     this.dialog.open(DialogComponent,{
      width: '20%',
      panelClass: 'my-panel'
     }).afterClosed().subscribe(val=>{
      console.log("nu aici")
      console.log(val)
      if(val === "save"){
        console.log("open dialog ",val)
        this.allUsers();
      }
    })
  };
  
deleteUser(data: any){
  console.log(data)
  this._moderator.deleteUser(data).subscribe((res)=>{
    if(res != null)
    this.dialog.open(DialogCancelDeleteUserComponent,{
      width: '20%',
      panelClass: 'my-panel'
     })
    this.allUsers()
  })
}
 
resetPass(element: any){
  
  return this._moderator.resetPassword(element).subscribe((res)=>{
    this.dialog.open(DialogResetPassComponent,{
      width: '30%',
      panelClass: 'my-panel',
      data:element
     })
  })

}
editUser(element: any){
    this.dialog.open(DialogAddUserComponent,{
      width: '30%',
      panelClass: 'my-panel',
      data:element
     }).afterClosed().subscribe(val=>{
      console.log(val)
      if(val === "update"){
        this.allUsers();
      }
    })
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}


