import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ModeratorService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(private _http: HttpClient,  private _router: Router) { }
  getAllUsers(){
    return this._http.post(this.baseUrl+"/moderator/allUsers",this.publicHttpHeaders);
  }
  
}
