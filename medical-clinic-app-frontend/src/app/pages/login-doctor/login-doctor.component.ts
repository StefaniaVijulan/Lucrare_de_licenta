import { Component, OnInit } from '@angular/core';
import { NgForm  } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginUserDTO } from 'src/app/interfaces/LoginUserDTO';

import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-doctor',
  templateUrl: './login-doctor.component.html',
  styleUrls: ['./login-doctor.component.css']
})
export class LoginDoctorComponent implements OnInit {
  public user:LoginUserDTO={
    username: '6001106174436',
    password: '',
  };
  msg='';
  public error: boolean | string = false;
  constructor(private _service: AuthService, private _router: Router ) { }

  ngOnInit() {
  }
  isNotValid(): boolean {
    return !this.user.username || !this.user.password;
  }
  doLoginUser(){
      this._service.loginUser(this.user).subscribe((response: any) =>{
        console.log(response);
        if (response && response.jwt) {
          localStorage.setItem('token', response.jwt);
          this._router.navigate(['/doctor-dashboard']);
        }

      });
 /* loginDoctor(){
      this._service.loginDoctorFromRemote(this.user).subscribe(
        data => {
          console.log("Response recieved");
          this._router.navigate(['/doctor-dashboard'])
        },
        error =>{
          console.log("Exception occured");
          this.msg='Bad credentials, pleaase enter valid email and password';
        }
      )
  }*/

}
}
