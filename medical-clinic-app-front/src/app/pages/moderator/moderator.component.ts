import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']
})

export class ModeratorComponent implements OnInit {

  usersList: User[] = [];
  curantsList: User[] = [];
  secretariesList: User[] = [];
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(public _moderator: ModeratorService, private _http: HttpClient, public _service: AuthService) { }

  ngOnInit() {
    this.allUsers()
  
  }
  

  allUsers(){
    return this._moderator.getAllUsers().subscribe((response: any) => {
      this.usersList = response
      this.dataSource.data = response
      console.log(this.usersList)
    })};
  allCurants(){
    return this._moderator.getAllCurant().subscribe((response: any) => {
      this.usersList = response
      this.curantsSource.data = response
      console.log(this.usersList)  
      })};
  allSecretaries(){
    return this._moderator.getAllSecretaries().subscribe((response: any) => {
    this.secretariesList = response
    this.secretariesSource.data = response
    console.log(this.secretariesList)  
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
}