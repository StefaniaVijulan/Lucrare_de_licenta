import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ImagistService {

 
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  resultRadiology: any;

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  getAllRadio(){
    return this._http.get(this.baseUrl + '/imagist/allTodayAppointmentRadiology', this.publicHttpHeaders);
  }
  seeAppointmentDone(element: any)
  {
    return this._http.put(this.baseUrl + '/imagist/appointmentRadiologyDone?idA=' + element,null, this.publicHttpHeaders)
  }
  getAllAppointemntWithoutResult(){

    return this._http.get(this.baseUrl + '/imagist/seeAppointmentWithoutResult', this.publicHttpHeaders)
  }
  
  seeAllProgramari(){
    this._router.navigate(['/imagist/appointments'])
  }
  seeAddResult(){
    this._router.navigate(['/imagist/result/add'])
  }
}
