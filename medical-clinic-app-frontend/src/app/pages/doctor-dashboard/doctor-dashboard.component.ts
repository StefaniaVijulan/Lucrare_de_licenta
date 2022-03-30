import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { LoginUserDTO } from 'src/app/interfaces/LoginUserDTO';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { setupTestingRouter } from '@angular/router/testing';
import { User } from 'src/app/interfaces/User';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})

export class DoctorDashboardComponent implements OnInit {
  selectedFile: File = null;
  private baseUrl = environment.baseUrl;
  public oldPass: String;
  public newPass: String;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  usersList: any = null;
  user: User;
  newUser: User;
  public errorMessage = "";
  public loading: boolean;
  constructor(public _service: AuthService, private _http: HttpClient) { }
  
  ngOnInit() {
    this.setDataUser()
  }
  onFileSelected(event){
    this.selectedFile = <File>event.target.files[0];
    console.log(event)
  }
  moderatorRole(){
    if(this._service.getRole()=="MODERATOR")
      return true
    else
      return false
  }
  getUser(){
   
    return this._http.get(this.baseUrl+ '/user/all');
  }
  
  allUser(){
    return this.getUser().subscribe((response: any) => {
      console.log("hello")
      this.usersList = response
      console.log(response)
    })}
  setDataUser(){
    var parseUser = JSON.parse(this._service.getUser())
    this.user = parseUser;

  }

  setPass(oldPass, newPass) {
    
    let par = new HttpParams();
    console.log(this.oldPass)
    console.log(this.newPass)
    par = par.append('oldPass', oldPass)
    par = par.append('newPass', newPass)
    //console.log(par)
    return this._http.post(this.baseUrl+`/user/changePass`,this.publicHttpHeaders, {
      params: par
    })
  }
  
  changePass() {console.log("aa")
    this.setPass(this.oldPass, this.newPass).subscribe((response: any) => {
      
        console.log(response)
        this.newUser = response

    },
    (error) => {                              //Error callback
      console.error('error caught in component')
      this.errorMessage = error;
      this.loading = false;

      //throw error;   //You can also throw the error to a global error handler
    })
  }
  /*onUpload(){
    const fd = new FormData()
    fd.append('image', this.selectedFile, this.selectedFile.name);


    )
  }*/
}
