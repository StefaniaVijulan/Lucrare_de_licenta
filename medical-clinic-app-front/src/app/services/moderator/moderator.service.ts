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
  public count;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getAllUsers(){
    return this._http.get(this.baseUrl + '/moderator/allUsers', this.publicHttpHeaders);
  
  }
  getAllCurant(){
  //  console.log('aici')
    return this._http.get(this.baseUrl + '/moderator/allCurant', this.publicHttpHeaders);
  }
  getAllSecretaries(){
    return this._http.get(this.baseUrl + '/moderator/allSecretaries', this.publicHttpHeaders);
  
  }
  addCurant(){
    return this._http.get(this.baseUrl + '/moderator/allCurant', this.publicHttpHeaders);
  }
  seeEmployee(){
    console.log("see empl")
    this.count = 1;
    console.log(this.count)
  }
  seeCurant(){
    console.log("see curant")
    this.count = 2;
    console.log(this.count)
  }
  seeSecretaries(){
    console.log("see secr")
    this.count = 3;
    console.log(this.count)
  }
  getCount(){
    return this.count;
  }
}