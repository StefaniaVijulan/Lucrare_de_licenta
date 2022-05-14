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
  doctorListService: any;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getDataBlock(cnpC: string): Observable<any>{
    return this._http.get(this.baseUrl + '/blockDateForCardio?cnpC=' + cnpC );
  }
  getValidationData(cnpC: string, element: string){
    return this._http.get(this.baseUrl + '/checkAvailabilityHourCardio?cnpC=' + cnpC + '&date=' + element);
  }
  addAppointment(cnpC: string, element: Appointment){
    return this._http.post(this.baseUrl + '/addAppointment?cnpC='+cnpC, element);
  }
  allCardiolog(){
    console.log("intra ins ervice")
    return this._http.get(this.baseUrl + '/allCardiolog')
  }
  getPatient(cnpP: string){
    return this._http.get(this.baseUrl + '/infoPatient?cnpP=' + cnpP );
  }

}
