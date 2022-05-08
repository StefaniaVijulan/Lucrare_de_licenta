import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Appointment } from 'src/app/interfaces/appointment';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';


@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getDataBlock(): Observable<any>{
    return this._http.get(this.baseUrl + '/blockDate');
  }
  getValidationData(element: string){
    console.log(element)
    return this._http.get(this.baseUrl + '/checkAvailabilityHour?date=' + element);
  }
  addAppointment(element: Appointment){
    return this._http.post(this.baseUrl + '/addAppointment', element);
  }
}
