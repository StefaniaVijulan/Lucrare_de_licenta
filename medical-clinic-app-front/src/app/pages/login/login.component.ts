import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/interfaces/login-dto';
import { AuthService } from 'src/app/services/auth/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public userDto: LoginDto ={
    username: '',
    password: '',
  };
  public message = '';
  public error: boolean | string = false;
  msg: string;

  constructor(private _service: AuthService, private _router: Router) { }

  ngOnInit() {
  }
  isNotValid(): boolean {
    return !this.userDto.username || !this.userDto.password;
  }
  doLoginUser() {
    this._service.loginUser(this.userDto).subscribe((response: any) => {
      console.log("doLogin")
      console.log(response);
      if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)  
        localStorage.setItem('user', JSON.stringify(response.user))
        localStorage.setItem('cnp', response.user.cnp)
        if(localStorage.getItem("role") == "MODERATOR"){
          window.location.href = "/moderator"
        } else if(localStorage.getItem("role") == "SECRETAR"){
          window.location.href = "/secretar"
        }
        else if(localStorage.getItem("role") == "CARDIOLOG"){
          window.location.href = "/doctorProgramari"
        }
        else if(localStorage.getItem("role") == "HEMATOLOG"){
          window.location.href = "/hematolog"

        }
        else{
          this._service.logoutUser()
          window.location.href = "/dashboard"

        }

      }
      else{
        console.log(response)
        if(response ==  null){
          this.msg ="CNP-ul sau parola este gresită!"
        }
      }
    })
  }
 
  doLoginPatient() {
    console.log("intra aici")
  //  window.location.href = "/pacient"
    this._service.loginPatient(this.userDto).subscribe((response: any) => {
      console.log("doLogin")
      console.log(response);
      console.log(response.jwt)
      if (response && response.jwt) {
        console.log("intra in if")
        localStorage.setItem('token', response.jwt)
        localStorage.setItem('role', response.patient.role)
        localStorage.setItem('patient', JSON.stringify(response.patient))
        localStorage.setItem('cnp', response.patient.cnp) 
        if(localStorage.getItem("role") == "PACIENT"){
          window.location.href = "/pacient"
        }else{
          this._service.logoutUser()
          window.location.href = "/dashboard"

        }
        console.log("dupa token")
      }else{
          console.log(response)
          if(response ==  null){
            this.msg ="CNP-ul sau parola este gresită!"
          }
        }
      //window.location.href = "/pacient"
     /* if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)  
        localStorage.setItem('user', JSON.stringify(response.user))
        localStorage.setItem('cnp', response.user.cnp)
        if(localStorage.getItem("role") == "MODERATOR"){
          window.location.href = "/moderator"
        } else if(localStorage.getItem("role") == "SECRETAR"){
          window.location.href = "/secretar"
        }
        else if(localStorage.getItem("role") == "CARDIOLOG"){
          window.location.href = "/doctorProgramari"
        }
        else if(localStorage.getItem("role") == "HEMATOLOG"){
          window.location.href = "/hematolog"

        }
        else{
          this._service.logoutUser()
          window.location.href = "/dashboard"

        }

      }
      else{
        console.log(response)
        if(response ==  null){
          this.msg ="CNP-ul sau parola este gresită!"
        }
      }*/
    })
  
  }
}
