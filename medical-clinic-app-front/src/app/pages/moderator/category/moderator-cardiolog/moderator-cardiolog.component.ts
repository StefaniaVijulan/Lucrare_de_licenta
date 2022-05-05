import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
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
import { Cardiolog } from 'src/app/interfaces/cardiolog';

@Component({
  selector: 'app-moderator-cardiolog',
  templateUrl: './moderator-cardiolog.component.html',
  styleUrls: ['./moderator-cardiolog.component.scss']
})
export class ModeratorCardiologComponent implements OnInit {

  displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser','numberUser', 'role', 'action'];
  dataSource !: MatTableDataSource<Cardiolog>;

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
  
    this.allCurants();

  
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }

  allCurants(){
    return this._moderator.getAllCardiolog().subscribe((response: any) => {  
      this.setRoleCase(response)
      this.dataSource = new MatTableDataSource<Cardiolog>( response); 
      this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
      })};
  
  openAddDialog(data: any){
    console.log(data)
    this._moderator.newUserS = data;
    this.dialog.open(DialogAddUserComponent,{
     width: '30%'
    }).afterClosed().subscribe(val=>{
      console.log(val)
      if(val === "save"){
        this.allCurants();
      }
    });
  }
  deleteUser(data: any){
    console.log(data)
    this._moderator.deleteUser(data).subscribe((res)=>{
      this.allCurants()
    })
  }
   
    editUser(element: any){
      this.dialog.open(DialogAddUserComponent,{
        width: '30%',
        data:element
       }).afterClosed().subscribe(val=>{
        console.log("intra aici")
        console.log(val)
        if(val === "update"){
          this.allCurants();
        }
      })
    }
  
  }
  
  