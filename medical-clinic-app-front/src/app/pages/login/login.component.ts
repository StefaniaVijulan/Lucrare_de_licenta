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

  constructor(private _service: AuthService, private _router: Router) { }

  ngOnInit() {
  }
  isNotValid(): boolean {
    return !this.userDto.username || !this.userDto.password;
  }
  doLoginUser() {
    this._service.loginUser(this.userDto).subscribe((response: any) => {
      console.log(response.user);
      if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)
        localStorage.setItem('user', JSON.stringify(response.user))
        if(response.user.role == "MODERATOR"){
          this._router.navigate(['/moderator']);
        }
        else{
          this._router.navigate(['/dashboard']);
        }

      }
    })
  }
 
  doLoginUserPatient() {
    this._service.loginUser(this.userDto).subscribe((response: any) => {
      console.log(response.user);
      if (response.user.role=="PATIENT" && response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)
        localStorage.setItem('user', JSON.stringify(response.user))
        
        this._router.navigate(['/dashboard']);
      }
    })
  }
}
