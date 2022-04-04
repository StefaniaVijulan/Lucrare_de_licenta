import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

  
const AVATAR_KEY = 'AVATAR_KEY';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  roleAs: string;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(private _http: HttpClient,  private _router: Router) {}

  loginUser(data:any){
    return this._http.post(this.baseUrl+"/login",data,this.publicHttpHeaders);
  }
  deleteDoctor(data:any){
    return this._http.post(this.baseUrl+"/deleteDoctor",data,this.publicHttpHeaders);
  }
  loggedIn(){
    console.log("local")
    console.log( !!localStorage.getItem('token'))
    console.log("mesaj")
    console.log((localStorage.getItem('token')) != null ? true : false)
    return !!localStorage.getItem('token')
  }
  loggedInIf(){
 
    return !!localStorage.getItem('token')
  }
  logoutUser(){
    localStorage.removeItem('token')
    this._router.navigate(['/login-doctor'])
  }
  getToken(){
    return localStorage.getItem('token')
  }
  getUser(){
    return localStorage.getItem('user')
  }
  getRole(){
    return localStorage.getItem('role')
  }
  setAvatar(avatar: string){
    window.sessionStorage.removeItem(AVATAR_KEY);
    window.sessionStorage.setItem(AVATAR_KEY, avatar)
  }
  getAvatar(){
    return window.sessionStorage.getItem("AVATAR_KEY")
  }
  getImage(){
    
  }
}
