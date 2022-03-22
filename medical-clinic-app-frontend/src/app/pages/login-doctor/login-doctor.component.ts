import {Component,OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginUserDTO} from 'src/app/interfaces/LoginUserDTO';
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
  message = '';
  public error: boolean | string = false;

  constructor(private _service: AuthService, private _router: Router) {}
 

  ngOnInit() {}
  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
  isNotValid(): boolean {
    return !this.user.username || !this.user.password;
  }
  doLoginUser() {
    this._service.loginUser(this.user).subscribe((response: any) => {
      console.log(response);
      if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        this._router.navigate(['/doctor-dashboard']);
      }

    });


  }
  
}