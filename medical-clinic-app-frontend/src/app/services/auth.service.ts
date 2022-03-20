import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

  

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(private _http: HttpClient) {}

  loginUser(data:any){
    return this._http.post(this.baseUrl+"/login",data,this.publicHttpHeaders);
  }
/*  public loginDoctorFromRemote(user: User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/login", user)

  }*/

}
