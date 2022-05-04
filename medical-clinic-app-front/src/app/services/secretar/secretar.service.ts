import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Pacient } from 'src/app/interfaces/pacient';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';
import { Hospitalization } from 'src/app/interfaces/hospitalization';


@Injectable({
  providedIn: 'root'
})
export class SecretarService {
  cnpS: string;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  
  seePacient(){
    this._router.navigate(['/secretarPacient'])
  }
  getAllPacients(){
    return this._http.get(this.baseUrl + '/secretary/allPatients', this.publicHttpHeaders);
  }
  addPacient(pacient: Pacient){
    return this._http.post<any>(this.baseUrl + '/secretary/addPatient', pacient, this.publicHttpHeaders);
  }
  allCardio(){
    return this._http.get(this.baseUrl + '/secretary/allCurants', this.publicHttpHeaders)
  }
  addHospitalization(cnpD: string, cnpP: any, hospitalization: Hospitalization){
    console.log("intra in hospitalization")
    console.log(localStorage.getItem('cnp'))
    this.cnpS = localStorage.getItem('cnp')
    return this._http.post<any>(this.baseUrl + '/hospitalization/addHospitalization?cnpS=' + this.cnpS + '&cnpD=' + cnpD + '&cnpP=' + cnpP, hospitalization, this.publicHttpHeaders);
  }
}
