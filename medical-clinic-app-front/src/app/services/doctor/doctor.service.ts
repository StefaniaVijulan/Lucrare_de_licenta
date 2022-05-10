import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { FisaPatient } from 'src/app/interfaces/fisaPatient';
import { Patient } from 'src/app/interfaces/patient';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  cnpCurrantCardioService: any;
  cnpCurrantpatientService: any;
  infoFisaService: any;
  private baseUrl = environment.baseUrl;
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  allAppointementSpecifc(){
    this.cnpCurrantCardioService = localStorage.getItem("cnp")
    return this._http.get(this.baseUrl + '/cardiolog/allSpecificAppointment?cnpC='+this.cnpCurrantCardioService, this.publicHttpHeaders)
  }
  infoSpecific(cnpP: any){
    return this._http.get(this.baseUrl + '/cardiolog/specificFisa?cnpP=' + cnpP, this.publicHttpHeaders)
  }
  editFisaPatient(fisa: FisaPatient){
    return this._http.put<any>(this.baseUrl + '/cardiolog/editFisaP?cnpP='+ this.cnpCurrantpatientService, fisa, this.publicHttpHeaders);
  }
  seeProgramari(){
    this._router.navigate(['/doctorProgramari'])
  }
  seeInternari(){
  //  this._router.navigate(['/secretar'])
  }
}
