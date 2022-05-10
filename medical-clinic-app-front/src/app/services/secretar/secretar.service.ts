import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/interfaces/patient';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';
import { Cardiolog } from 'src/app/interfaces/cardiolog';
import { StringifyOptions } from 'querystring';
import { FisaPatient } from 'src/app/interfaces/fisaPatient';


@Injectable({
  providedIn: 'root'
})
export class SecretarService {
  cnpS: string;
  existaPacient: boolean;
  pacientListService: any;
  hospitaliationListService: any;
  doctorListService: any;


  doctor: Cardiolog;
  cnpP: string;

  fisaService: FisaPatient= new FisaPatient();
  pacientService: Patient =  new Patient();
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  
  addPatient(patient: Patient){
    return this._http.post<any>(this.baseUrl + '/secretary/addPatient', patient, this.publicHttpHeaders);
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


  addFisa(cnpP: string){

    return this._http.post<any>(this.baseUrl + '/secretary/addFisa?cnpP=' + cnpP, this.fisaService, this.publicHttpHeaders);
  }


  seePacient(){
    this._router.navigate(['/secretarPacient'])
  }
  seeInternari(){
    this._router.navigate(['/secretar'])
  }
  

}
