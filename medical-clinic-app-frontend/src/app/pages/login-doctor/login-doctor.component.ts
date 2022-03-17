import { Component, OnInit } from '@angular/core';
import { NgForm  } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-doctor',
  templateUrl: './login-doctor.component.html',
  styleUrls: ['./login-doctor.component.css']
})
export class LoginDoctorComponent implements OnInit {
  user = new User()
  constructor(private _service: AuthService ) { }

  ngOnInit() {
  }
  loginDoctor(){
      this._service.loginDoctorFromRemote(this.user).subscribe(
        data => console.log("Response recieved"),
        error =>console.log("Exception occured")
      )
  }
}
