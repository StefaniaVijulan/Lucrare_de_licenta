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
  hospitalizationNo: string;

  pacientListService: any;
  hospitaliationListService: any;
  doctorListService: any;

  pacient: Pacient;
  hospitalization: Hospitalization;
  doctor: Cardiolog;
  cnpP: string;

  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }

  getAllHospitalizationActive(){
    return this._http.get(this.baseUrl + '/secretary/allHospitalizationActive', this.publicHttpHeaders);
  }
  
  getAllHospitalization(){
    return this._http.get(this.baseUrl + '/secretary/allHospitalization', this.publicHttpHeaders);
  }
  
  addPacient(pacient: Pacient){
    return this._http.post<any>(this.baseUrl + '/secretary/addPatient', pacient, this.publicHttpHeaders);
  }
  addHospitalization(cnpD: string, cnpP: any, hospitalization: Hospitalization){
    this.cnpS = localStorage.getItem('cnp')
    return this._http.post<any>(this.baseUrl + '/hospitalization/addHospitalization?cnpS=' + this.cnpS + '&cnpD=' + cnpD + '&cnpP=' + cnpP, hospitalization, this.publicHttpHeaders);
  }
  getSpecificHospitalization(){
    console.log("in secretar service => " + this.hospitalizationNo)
    return this._http.get(this.baseUrl + '/secretary/specificHospitalization?noHosp=' + this.hospitalizationNo, this.publicHttpHeaders);
  }
allCardiolog(){
    return this._http.get(this.baseUrl + '/secretary/allCardiolog', this.publicHttpHeaders)
  }
  externarePacient(registrationNoHospitalization: string){
    console.log("externeaza service")
    console.log(registrationNoHospitalization)
    return this._http.get<any>(this.baseUrl + '/secretary/editHospitalization?idHospitalization='+ registrationNoHospitalization, this.publicHttpHeaders);
  }




  deletePatient(data: any){
    return this._http.delete(this.baseUrl + '/secretary/deletePatient?cnpP='+ data, this.publicHttpHeaders);
  }



  seePacient(){
    this._router.navigate(['/secretarPacient'])
  }
  seeInternari(){
    this._router.navigate(['/secretar'])
  }
  

}
