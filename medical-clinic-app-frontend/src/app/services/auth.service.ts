import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
  

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _http: HttpClient) { }

  public loginDoctorFromRemote(user: User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/login", user)

  }
}
