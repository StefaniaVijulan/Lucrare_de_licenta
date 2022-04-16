import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { pipeFromArray } from 'rxjs/internal/util/pipe';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ModeratorService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getAllUsers(){
    console.log('aici')
    if(this._service.getRole()=="MODERATOR")
      return this._http.get('http://localhost:8080/moderator/allUsers', this.publicHttpHeaders);
  
  }
}
