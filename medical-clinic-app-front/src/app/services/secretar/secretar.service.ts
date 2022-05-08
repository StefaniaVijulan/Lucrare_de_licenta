import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Pacient } from 'src/app/interfaces/pacient';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';
import { Hospitalization } from 'src/app/interfaces/hospitalization';
import { Cardiolog } from 'src/app/interfaces/cardiolog';
import { StringifyOptions } from 'querystring';


@Injectable({
  providedIn: 'root'
})
export class SecretarService {
  cnpS: string;
  pacientListService: any;
  hospitaliationListService: any;
  doctorListService: any;

  pacient: Pacient;
  doctor: Cardiolog;
  cnpP: string;

  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  
  addPacient(pacient: Pacient){
    return this._http.post<any>(this.baseUrl + '/secretary/addPatient', pacient, this.publicHttpHeaders);
  }
 
  allCardiolog(){
    return this._http.get(this.baseUrl + '/secretary/allCardiolog', this.publicHttpHeaders)
  }


  deletePatient(data: any){
    return this._http.delete(this.baseUrl + '/secretary/deletePatient?cnpP='+ data, this.publicHttpHeaders);
  }

  getAllCurrentAppointments(){
    return this._http.get(this.baseUrl + '/secretary/allAppointments', this.publicHttpHeaders)
  }
  getAllTodayAppointments(){
    return this._http.get(this.baseUrl + '/secretary/todayAppointments', this.publicHttpHeaders)
  }

  checkPatient(cnp: any){
    return this._http.get(this.baseUrl + '/secretary/checkPatient?cnp='+cnp, this.publicHttpHeaders)
  }



  seePacient(){
    this._router.navigate(['/secretarPacient'])
  }
  seeInternari(){
    this._router.navigate(['/secretar'])
  }
  

}
