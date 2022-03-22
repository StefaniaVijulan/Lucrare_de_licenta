import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

  

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(private _http: HttpClient,  private _router: Router) {}

  loginUser(data:any){
    return this._http.post(this.baseUrl+"/login",data,this.publicHttpHeaders);
  }
  loggedIn(){
    return !!localStorage.getItem('token')
  }

  logoutUser(){
    localStorage.removeItem('token')
    this._router.navigate(['/login-doctor'])
  }

  getToken(){
    return localStorage.getItem('token')
  }

}
