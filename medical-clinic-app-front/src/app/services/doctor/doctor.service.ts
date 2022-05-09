import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private _http: HttpClient,  private _router: Router, public _service: AuthService) { }
  seeProgramari(){
  this._router.navigate(['/doctorProgramari'])
  }
  seeInternari(){
  //  this._router.navigate(['/secretar'])
  }
}
