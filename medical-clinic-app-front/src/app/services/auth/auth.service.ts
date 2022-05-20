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
import { timeStamp } from 'console';
import { User } from 'src/app/interfaces/user';
import {
  environment
} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private cnpUser: string;
  userListService:any;
  private publicHttpHeaders = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };

  constructor(private _http: HttpClient, private _router: Router) {}
  loginUser(data: any) {
    return this._http.post(this.baseUrl + "/login", data, this.publicHttpHeaders);
  }
  loggedIn() {
    return !!localStorage.getItem('token')
  }
  loginPatient(data: any){
    return this._http.post(this.baseUrl + "/loginPatient", data, this.publicHttpHeaders);
  }
  forgotPass(data: any){

    return this._http.put(this.baseUrl + "/forgotPass?cnpU="+ data, this.publicHttpHeaders);
  }
  deleteImg(){
    console.log("apoi")
    this.cnpUser = localStorage.getItem("cnp")
    return this._http.put<User>(this.baseUrl + "/deleteImage?cnpU="+ this.cnpUser, this.publicHttpHeaders);
  }
  logoutUser() {
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    localStorage.removeItem('user')
    localStorage.removeItem('cnp')
    localStorage.removeItem('patient')
    localStorage.removeItem('image')
        localStorage.removeItem("name")
    this._router.navigate(['/dashboard'])
  }
  logInUser(){
    console.log("aici")
    this._router.navigate(['/login'])
  }
  getRole() {
    return localStorage.getItem('role')
  }

  changePass(oldPass: string, newPass: string){

      this.cnpUser =  localStorage.getItem('cnp')
      return this._http.get<any>(this.baseUrl + '/changePass?oldPass='+ oldPass + '&newPass=' + newPass + '&cnpU=' + this.cnpUser, this.publicHttpHeaders);
  }
  changeImg(img: any){
    this.cnpUser = localStorage.getItem("cnp")
    return this._http.put(this.baseUrl + '/changeimage?cnpU='+ this.cnpUser, img, this.publicHttpHeaders);
  }
  getAllUsers(){
    return this._http.get(this.baseUrl + '/allUsers', this.publicHttpHeaders);
  }
}