import { TOUCH_BUFFER_MS } from '@angular/cdk/a11y';
import {Component,OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginUserDTO} from 'src/app/interfaces/LoginUserDTO';
import { User } from 'src/app/interfaces/User';
import {AuthService} from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-doctor',
  templateUrl: './login-doctor.component.html',
  styleUrls: ['./login-doctor.component.css']
})
export class LoginDoctorComponent implements OnInit {
  public showPassword: boolean = false;
  public user: LoginUserDTO = {
    username: '',
    password: '',
  };
  public userCurrent: User;
  message = '';
  public error: boolean | string = false;

  constructor(private _service: AuthService, private _router: Router) {}
 

  ngOnInit() {console.log("sper")}
  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
  isNotValid(): boolean {
    return !this.user.username || !this.user.password;
  }
  doLoginUser() {
    this._service.loginUser(this.user).subscribe((response: any) => {
      this.userCurrent = response.user;
      console.log(response.user);
      if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)
        localStorage.setItem('user', JSON.stringify(response.user))
        
        this._router.navigate(['/doctor-dashboard']);
      }
     // console.log(localStorage.getItem('user'))

    });


  }
  getUserCurrent(){
    return this.userCurrent;
  }
  getAvatar(){
    return this.userCurrent.image
  }
}