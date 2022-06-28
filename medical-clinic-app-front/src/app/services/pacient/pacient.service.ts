import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { Observable } from 'rxjs';
import { Appointment } from 'src/app/interfaces/appointment';
import { AppointmentsHematology } from 'src/app/interfaces/appointmentHematology';
import { AppointmentsRadiology } from 'src/app/interfaces/appointmentRadiology';
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
cardiologService: any;
reprogrameazaType: any;
  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  addAppointment(cnpC: string, element: Appointment){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.post(this.baseUrl + '/pacient/addAppointment?cnpP=' + this.cnpP +'&cnpC='+cnpC, element, this.publicHttpHeaders);
  }
  getNextAppointment(){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.get(this.baseUrl + '/pacient/nextAppointment?cnpP=' + this.cnpP, this.publicHttpHeaders)
  }
  reprogramareAppointment(appoinmtemt: Appointment){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.put(this.baseUrl + '/pacient/reprogrameazaAppointment?cnpP=' + this.cnpP,appoinmtemt, this.publicHttpHeaders)
  }
  getNextAppointmentHematology(){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.get(this.baseUrl + '/pacient/nextAppointmentHematology?cnpP=' + this.cnpP, this.publicHttpHeaders)
  }
  reprogramareAppointmentHematology(appointment: AppointmentsHematology){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.put(this.baseUrl + '/pacient/reprogrameazaAppointmentHematology?cnpP=' + this.cnpP,appointment, this.publicHttpHeaders)
  }
  getNextAppointmentRadiology(){
    this.cnpP = localStorage.getItem("cnp")
    return this._http.get(this.baseUrl + '/pacient/nextAppointmentRadiology?cnpP=' + this.cnpP, this.publicHttpHeaders)
  }
  reprogramareAppointmentRadiology(appointment: AppointmentsRadiology){
    this.cnpP = localStorage.getItem("cnp")
    console.log("element", appointment)
    return this._http.put(this.baseUrl + '/pacient/reprogrameazaAppointmentRadiology?cnpP=' + this.cnpP,appointment, this.publicHttpHeaders)
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
  getDataBlockHema(): Observable<any>{
    return this._http.get(this.baseUrl + '/pacient/blockDateHematology', this.publicHttpHeaders);
  }
  getValidationDataHema( element: string){
    return this._http.get(this.baseUrl + '/pacient/checkAvailabilityHematology?dateA=' + element, this.publicHttpHeaders);
  }

  getDataBlockRadio(): Observable<any>{
    return this._http.get(this.baseUrl + '/pacient/blockDateRadiology', this.publicHttpHeaders);
  }
  getValidationDataRadio( element: string){
    return this._http.get(this.baseUrl + '/pacient/checkAvailabilityRadiology?dateA=' + element, this.publicHttpHeaders);
  }
  getHematologyResult(){
    return this._http.get(this.baseUrl + '/pacient/hematologyResult?cnpP=' + element, this.publicHttpHeaders);
  }
  getRadiologyResult(){
    return this._http.get(this.baseUrl + '/pacient/radiologyResult?cnpP=' + element, this.publicHttpHeaders);
  }
  goHome(){
    this._router.navigate(['/pacient/home'])
  }
  seeDoctor(){
    this._router.navigate(['/pacient/all/doctors'])
  }
  seeHematologyResult(){
  //  this._router.navigate(['/pacient/hematologie/rezultat'])
  }
}
