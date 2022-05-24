import { ContentObserver } from '@angular/cdk/observers';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HematologyResult } from 'src/app/interfaces/hematologyResult';
import { environment } from 'src/environments/environment';

import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class HematologService {

  resultHematology: HematologyResult;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  getAllHema(){
    return this._http.get(this.baseUrl + '/hematolog/allTodayAppointmentHematology', this.publicHttpHeaders);
  }
  seeAppointmentDone(element: any)
  {
    return this._http.put<any>(this.baseUrl + '/hematolog/appointmentHematologyDone?idA=' + element,null, this.publicHttpHeaders)
  }
  getAllAppointemntWithoutResult(){
    return this._http.get(this.baseUrl + '/hematolog/seeAppointmentWithoutResult', this.publicHttpHeaders)
  }
  getAllResult(){
    return this._http.get(this.baseUrl + '/hematolog/seeAllResult', this.publicHttpHeaders)
  }


  editAppointmentResult(idA: any, result: any){
    console.log("intra in service")
    return this._http.put(this.baseUrl + '/hematolog/editAppointmentResult?idR='+idA, result, this.publicHttpHeaders)
  }


  seeAllProgramari(){
    this._router.navigate(['/hematolog/appointments'])
  }
  seeAddResult(){
    this._router.navigate(['/hematolog/result/add'])
  }
  seeAllResult(){
    this._router.navigate(['/hematolog/result/all'])
  }
}
