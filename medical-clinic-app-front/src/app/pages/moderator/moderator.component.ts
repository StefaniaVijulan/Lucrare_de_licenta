import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import { MatDialog, MatPaginator, MAT_DIALOG_DATA } from '@angular/material';
import { DialogComponent } from 'src/app/components/dialog/dialog.component';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']
})

export class ModeratorComponent implements OnInit {

  usersList: User[] = [];
  curantsList: User[] = [];
  secretariesList: User[] = [];
  imagistList: User[] = [];
  hematologList: User[] = [];
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  @ViewChild(MatTable,{static:true}) table: MatTable<any>;
  @ViewChild(MatPaginator,{static:true})
  paginatorCurant: MatPaginator;
  paginator: MatPaginator;
  paginatorSecretary: MatPaginator;
  paginatorImagist: MatPaginator;
  paginatorHematolog: MatPaginator;
  constructor(private dialog: MatDialog, public _moderator: ModeratorService, private _http: HttpClient, public _service: AuthService) { }

  ngOnInit() {
    this.allUsers();
    this.dataSource.paginator = this.paginator;
    this.allSecretaries();
    this.secretariesSource.paginator = this.paginatorSecretary
    this.allCurants();
    this.curantsSource.paginator = this.paginatorCurant;
    this.allImagists();
    this.imagistSource.paginator = this.paginatorImagist;
    this.allHematolog();
    this.hematologSource.paginator = this.paginatorHematolog;
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allUsers(){
    return this._moderator.getAllUsers().subscribe((response: any) => {
      this.usersList = response
      this.setRoleCase(response)
      this.dataSource.data = response
      console.log(this.usersList)
    })};
  allCurants(){
    return this._moderator.getAllCurant().subscribe((response: any) => {
      this.usersList = response
      this.setRoleCase(response)
      this.curantsSource.data = response
      console.log(this.usersList)  
      })};
  allSecretaries(){
    return this._moderator.getAllSecretaries().subscribe((response: any) => {
    this.secretariesList = response
    this.setRoleCase(response)
    this.secretariesSource.data = response
    console.log(this.secretariesList)  
  })};
  allImagists(){
    return this._moderator.getAllimagists().subscribe((response: any) => {
    this.imagistList = response
    this.setRoleCase(response)
    this.imagistSource.data = response
    console.log(this.imagistList)  
  })};
  allHematolog(){
    return this._moderator.getAllHematolog().subscribe((response: any) => {
    this.hematologList = response
    this.setRoleCase(response)
    this.hematologSource.data = response
    console.log(this.hematologList)  
  })};
  getModeratorRol(){
      if(this._service.getRole()=="MODERATOR")
        return true
      else
        return false
  };

    displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser','numberUser', 'role'];
    public dataSource = new MatTableDataSource<User>();
    public curantsSource = new MatTableDataSource<User>()
    public secretariesSource = new MatTableDataSource<User>()
    public imagistSource = new MatTableDataSource<User>()
    public hematologSource = new MatTableDataSource<User> ()

    openDialog(){
     this.dialog.open(DialogComponent,{
      width: '30%'
     });

    }
}


