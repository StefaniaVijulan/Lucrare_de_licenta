import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import { MatDialog, MatPaginator, MatSort, MAT_DIALOG_DATA, Sort } from '@angular/material';
import { DialogComponent } from 'src/app/components/dialog/dialog.component';
import { DialogAddUserComponent } from 'src/app/components/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from 'src/app/components/dialog-delete-user/dialog-delete-user.component';
import { element } from 'protractor';

@Component({
  selector: 'app-moderator-hematolog',
  templateUrl: './moderator-hematolog.component.html',
  styleUrls: ['./moderator-hematolog.component.scss']
})
export class ModeratorHematologComponent  implements OnInit {

  displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser','numberUser', 'role', 'action'];
  dataSource !: MatTableDataSource<User>;

  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;
  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;



  constructor(private dialog: MatDialog, 
    public _moderator: ModeratorService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }

  ngOnInit(): void {
    this.allHematolog();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }

  allHematolog(){
    return this._moderator.getAllHematolog().subscribe((response: any) => {  
      this.setRoleCase(response)
      this.dataSource = new MatTableDataSource<User>( response); 
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      })};
  
  openAddDialog(data: any){

    this._moderator.newUserS = data;
    this.dialog.open(DialogAddUserComponent,{
     width: '30%'
    }).afterClosed().subscribe(val=>{

      if(val === "save"){
        this.allHematolog();
      }
    });
  }
  deleteUser(data: any){

    this._moderator.deleteUser(data).subscribe((res)=>{
      this.allHematolog()
    })
  }
   
    editUser(element: any){
      this.dialog.open(DialogAddUserComponent,{
        width: '30%',
        data:element
       }).afterClosed().subscribe(val=>{

        if(val === "update"){
          this.allHematolog();
        }
      })
    }
  }
  
  
