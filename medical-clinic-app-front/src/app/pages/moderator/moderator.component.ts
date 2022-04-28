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
  paginatorCurant!: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort!: MatSort;
  sortCurant!: MatSort;

  constructor(private dialog: MatDialog, 
    public _moderator: ModeratorService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }

  ngOnInit(): void {
    this.allUsers();
    this.allSecretaries();
    this.allCurants();
    this.allImagists();
    this.allHematolog();
  
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
     this.dataSource =  new MatTableDataSource(res);
     this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    }
   )};
  allCurants(){
    return this._moderator.getAllCurant().subscribe((response: any) => {  
      this.setRoleCase(response)
      this.curantsSource = new MatTableDataSource<User>( response);
      this.curantsSource.paginator = this.paginator;
      this.curantsSource.sort = this.sort;
   
      })};
  allSecretaries(){
    return this._moderator.getAllSecretaries().subscribe((response: any) => {  
    this.setRoleCase(response)
    this.secretariesSource = new MatTableDataSource<User>( response);
 
 
  })};
  allImagists(){
    return this._moderator.getAllimagists().subscribe((response: any) => {
  
    this.setRoleCase(response)
    this.imagistSource = new MatTableDataSource<User>( response);
   
 
  })};
  allHematolog(){
    return this._moderator.getAllHematolog().subscribe((response: any) => {
    this.setRoleCase(response)
    this.hematologSource = new MatTableDataSource<User>( response);

 
  })};
  getModeratorRol(){
      if(this._service.getRole()=="MODERATOR")
        return true
      else
        return false
  };
  openDialog(){
     this.dialog.open(DialogComponent,{
      width: '30%'
     });

  };
  editUser(element: any){
    this.dialog.open(DialogComponent,{
      width: '30%',
      data:element
     });
  }

}


