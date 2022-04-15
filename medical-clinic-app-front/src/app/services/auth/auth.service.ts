import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(private _http: HttpClient,  private _router: Router) { }
  loginUser(data:any){
    return this._http.post(this.baseUrl+"/login",data,this.publicHttpHeaders);
  }
  loggedIn(){
    return !!localStorage.getItem('token')
  }
  logoutUser(){
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    localStorage.removeItem('user')
    this._router.navigate(['/home'])
  }
  getRole(){
    return localStorage.getItem('role')
  }
}
