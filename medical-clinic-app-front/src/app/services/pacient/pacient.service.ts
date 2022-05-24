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
export class PacientService {

  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };
  doctorListService: any;
  blockedDataAppointment: any = new Array();
cnpP: any;

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  addAppointment(cnpC: string, element: Appointment){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.post(this.baseUrl + '/pacient/addAppointment?cnpP=' + this.cnpP +'&cnpC='+cnpC, element, this.publicHttpHeaders);
  }
  getNextAppointment(){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.get(this.baseUrl + '/pacient/nextAppointment?cnpP=' + this.cnpP, this.publicHttpHeaders)
  }

  allCardiolog(){
    return this._http.get(this.baseUrl + '/pacient/allCardiolog', this.publicHttpHeaders)
  }
  getDataBlock(cnpC: string): Observable<any>{
    console.log("cnpC=>", cnpC)
    return this._http.get(this.baseUrl + '/pacient/blockDateForCardio?cnpC=' + cnpC, this.publicHttpHeaders );
  }
  getValidationData(cnpC: string, element: string){
    return this._http.get(this.baseUrl + '/pacient/checkAvailabilityHourCardio?cnpC=' + cnpC + '&date=' + element, this.publicHttpHeaders);
  }
  
  goHome(){
    this._router.navigate(['/pacient/home'])
  }
}
