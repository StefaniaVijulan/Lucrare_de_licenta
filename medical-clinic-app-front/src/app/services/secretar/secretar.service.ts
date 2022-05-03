import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Pacient } from 'src/app/interfaces/pacient';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SecretarService {
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
    console.log("in service")
    return this._http.post<any>(this.baseUrl + '/secretary/addPatient', pacient, this.publicHttpHeaders);
  }
}
