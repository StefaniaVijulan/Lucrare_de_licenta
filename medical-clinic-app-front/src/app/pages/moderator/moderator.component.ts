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
  imagistList: User[] = [];
  hematologList: User[] = [];
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(public _moderator: ModeratorService, private _http: HttpClient, public _service: AuthService) { }

  ngOnInit() {
    this.allUsers()
    this.allSecretaries()
    this.allCurants()
    this.allImagists()
    this.allHematolog()
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
}