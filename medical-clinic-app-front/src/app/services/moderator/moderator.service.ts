import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { pipeFromArray } from 'rxjs/internal/util/pipe';
import { finalize } from 'rxjs/operators';
import { Cardiolog } from 'src/app/interfaces/cardiolog';
import { Hematolog } from 'src/app/interfaces/hematolog';
import { Imagist } from 'src/app/interfaces/imagist';
import { Secretar } from 'src/app/interfaces/secretar';
import { User } from 'src/app/interfaces/user';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';


@Injectable({
  providedIn: 'root'
})
export class ModeratorService {
  public newUserS: string;

  public newUserRole: string;
  public count;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('token')})
    
  };

  constructor( private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
 
  getAllUsers(){
    return this._http.get(this.baseUrl + '/moderator/allUsers', this.publicHttpHeaders);
  }

  getAllCardiolog(){
    console.log("Apelam backend pentru allCardiolog")
    return this._http.get(this.baseUrl + '/moderator/allCardiolog', this.publicHttpHeaders);
  }
  addCardiolog(cardiolog: Cardiolog){
    return this._http.post<any>(this.baseUrl + '/moderator/registerCardiolog', cardiolog, this.publicHttpHeaders);
  }

  getAllSecretaries(){
    return this._http.get(this.baseUrl + '/moderator/allSecretaries', this.publicHttpHeaders); 
  }
  addSecretaries(secretar: Secretar){
    return this._http.post<any>(this.baseUrl + '/moderator/registerSecretary', secretar, this.publicHttpHeaders);
  }
  getAllimagists(){
    return this._http.get(this.baseUrl + '/moderator/allImagist', this.publicHttpHeaders); 
  }
  addImagists(user: Imagist){
    return this._http.post<any>(this.baseUrl + '/moderator/registerImagist', user, this.publicHttpHeaders);
  }

  getAllHematolog(){
    return this._http.get(this.baseUrl + '/moderator/allHematolog', this.publicHttpHeaders); 
  }
  addHematolog(user: Hematolog){
    return this._http.post<any>(this.baseUrl + '/moderator/registerHematolog', user, this.publicHttpHeaders);
  }
  
  editUser(roleInfo: string, cnp: string, user: User){
    return this._http.put<any>(this.baseUrl + '/moderator/editUser?role='+roleInfo+'&cnp='+ cnp, user, this.publicHttpHeaders);
  }
  deleteUser(data: any){
    return this._http.delete(this.baseUrl + '/moderator/deleteUser?cnp='+ data, this.publicHttpHeaders);
  }
  resetPassword(cnp: string)
  {
    return this._http.get<any>(this.baseUrl + '/moderator/resetPass?cnp='+ cnp, this.publicHttpHeaders);
  }

  getAllAppointments(){
    console.log("aici")
    return this._http.get(this.baseUrl + '/moderator/allAppointment', this.publicHttpHeaders); 
  }

  getAllAppointmentsH(){
    console.log("aici")
    return this._http.get(this.baseUrl + '/moderator/allAppointmentHematology', this.publicHttpHeaders); 
  }

  getAllAppointmentsR(){
    console.log("aici")
    return this._http.get(this.baseUrl + '/moderator/allAppointmentRadiology', this.publicHttpHeaders); 
  }

  getAppointmentByDate(dataStart:any, dataEnd:any){
    return this._http.get(this.baseUrl + '/moderator/betweenDate?dataStart=' + dataStart +'&dataEnd=' +dataEnd, this.publicHttpHeaders); 
  }

  
  /*for menu*/
  seeEmployee(){
    this._router.navigate(['/moderator'])
  }
  seeCurant(){
    this._router.navigate(['/moderatorCardiolog'])
  }
  seeSecretaries(){
    this._router.navigate(['/moderatorSecretar'])
  }
  seeImagist(){
    this._router.navigate(['/moderatorImagist'])
  }
  seeHematolog(){
    this._router.navigate(['/moderatorHematolog'])
  }
  seeProgramari(){
    this._router.navigate(['/moderator/appointments'])
  }
  seeProgramariHema(){
    this._router.navigate(['/moderator/appointmentsHematology'])
  }
  seeProgramariRadio(){
    this._router.navigate(['/moderator/appointmentsRadiology'])
  }
}