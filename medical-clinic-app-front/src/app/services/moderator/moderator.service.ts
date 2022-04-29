import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { pipeFromArray } from 'rxjs/internal/util/pipe';
import { User } from 'src/app/interfaces/user';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ModeratorService {
  public newUserS: string;
  public count;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getAllUsers(){
    return this._http.get(this.baseUrl + '/moderator/allUsers', this.publicHttpHeaders);
  }
  editUser(roleInfo: string, cnp: string, user: User){
    return this._http.put<any>(this.baseUrl + '/moderator/editUser?role='+roleInfo+'&cnp='+ cnp, user, this.publicHttpHeaders);
  }
  deleteUser(data: any){
    console.log("inntra aici")
    return this._http.delete(this.baseUrl + '/moderator/deleteUser?cnp='+ data, this.publicHttpHeaders);
  }


  getAllCardiolog(){
    return this._http.get(this.baseUrl + '/moderator/allCurant', this.publicHttpHeaders);
  }
  addCardiolog(user: User){
    return this._http.post<any>(this.baseUrl + '/moderator/registerCardiolog', user, this.publicHttpHeaders);
  }
  getAllHospitalizationCardiolog(data: any){
    return this._http.get(this.baseUrl + '/moderator/allHospitalizationCardiolog?cnp='+ data, this.publicHttpHeaders);
  }


  getAllSecretaries(){
    return this._http.get(this.baseUrl + '/moderator/allSecretaries', this.publicHttpHeaders); 
  }
  addSecretaries(user: User){
    return this._http.post<any>(this.baseUrl + '/moderator/registerSecretary', user, this.publicHttpHeaders);
  }


  getAllimagists(){
    return this._http.get(this.baseUrl + '/moderator/allImagist', this.publicHttpHeaders); 
  }
  addImagists(user: User){
    return this._http.post<any>(this.baseUrl + '/moderator/registerImagist', user, this.publicHttpHeaders);
  }

  getAllHematolog(){
    return this._http.get(this.baseUrl + '/moderator/allHematologs', this.publicHttpHeaders); 
  }
  addHematolog(user: User){
    return this._http.post<any>(this.baseUrl + '/moderator/registerHematolog', user, this.publicHttpHeaders);
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
  seeImagist(){
    console.log("see imagist")
    this.count = 4;
    console.log(this.count)
  }
  seeHematolog(){
    console.log("see hema")
    this.count = 5;
    console.log(this.count)
  }
  getCount(){
    return this.count;
  }
}