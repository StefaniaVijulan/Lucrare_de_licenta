import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';
import {
  HostListener,
  Injectable
} from '@angular/core';
import {
  Router
} from '@angular/router';
import {
  environment
} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private cnpUser: string;
  private publicHttpHeaders = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };
  private publicHttpHeadersAuth = {
    headers: new HttpHeaders({'Content-type':'application/json',
    'Authorization': 'Bearer ' + localStorage.getItem('token')})
  };
  constructor(private _http: HttpClient, private _router: Router) {}
  loginUser(data: any) {
    return this._http.post(this.baseUrl + "/login", data, this.publicHttpHeaders);
  }
  loggedIn() {
    return !!localStorage.getItem('token')
  }
  logoutUser() {
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    localStorage.removeItem('user')
    localStorage.removeItem('cnp')
    this._router.navigate(['/home'])
  }
  getRole() {
    return localStorage.getItem('role')
  }

  changePass(oldPass: string, newPass: string){
    
      console.log("here")
      console.log(oldPass)
      console.log(newPass)
      this.cnpUser =  localStorage.getItem('cnp')
      console.log(localStorage.getItem('cnp'))
      return this._http.get<any>(this.baseUrl + '/changePass?oldPass='+ oldPass + '&newPass=' + newPass + '&cnp=' + this.cnpUser, this.publicHttpHeaders);
  }
}