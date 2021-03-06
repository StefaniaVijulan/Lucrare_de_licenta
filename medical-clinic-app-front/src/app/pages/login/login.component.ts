import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { ForgotPassComponent } from 'src/app/components/forgot-pass/forgot-pass.component';
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
  firstFormGroup: FormGroup;
  hideNew = true;
  isChecked: boolean = true;

  loading = false;
  constructor(private dialog: MatDialog, private _formBuilder: FormBuilder,private _service: AuthService, private _router: Router) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      password: ['', Validators.required]})
  }
  setUser()
  {
    this.userDto.username = this.firstFormGroup.value.username
    console.log("username=>", this.userDto.username)
    this.userDto.password = this.firstFormGroup.value.password
    console.log("password=>", this.userDto.password)


  }
  isNotValid(): boolean {
    return !this.userDto.username || !this.userDto.password;
  }
  doLoginUser() {
    this.loading = true
    console.log("isChesck", this.isChecked)
    this.setUser()
    console.log("intra in do login")
    this._service.loginUser(this.userDto).subscribe((response: any) => {
      this.loading = false
      console.log("doLogin")
      console.log(response);
      if (response && response.jwt) {
        localStorage.setItem('token', response.jwt);
        localStorage.setItem('role', response.user.role)  
        localStorage.setItem('user', JSON.stringify(response.user))
        localStorage.setItem('cnp', response.user.cnp)
        localStorage.setItem('image', response.user.imageUser)
        localStorage.setItem("name", response.user.firstName + " " + response.user.lastName)
        if(localStorage.getItem("role") == "MODERATOR"){
          window.location.href = "/moderator"
        } else if(localStorage.getItem("role") == "SECRETAR"){
          window.location.href = "/secretar"
        }
        else if(localStorage.getItem("role") == "CARDIOLOG"){
          window.location.href = "/doctor/consultatii"
        }
        else if(localStorage.getItem("role") == "HEMATOLOG"){
          window.location.href = "/hematolog/appointments"

        }
        else if(localStorage.getItem("role") == "IMAGIST"){
          window.location.href = "/imagist/appointments"

        }
        else{
          this._service.logoutUser()
          window.location.href = "/dashboard"

        }

      }
      else{
        console.log(response)
        if(response ==  null){
          this.msg ="Contul nu este valid!"
        }
      }
    })
  }
  doLoginPatient() {
    this.loading = true
    this.setUser()
    console.log("intra aici pacient")
    console.log(this.userDto)
  //  window.location.href = "/pacient"
    this._service.loginPatient(this.userDto).subscribe((response: any) => {
      this.loading = false
      console.log("doLogin")
      console.log(response);

      if(response == null){
      
          this.msg ="Contul nu este valid!"

      }
      if (response && response.jwt) {
        console.log("intra in if")
        localStorage.setItem('token', response.jwt)
        localStorage.setItem('role', response.patient.role)
        localStorage.setItem('patient', JSON.stringify(response.patient))
        localStorage.setItem('cnp', response.patient.cnp) 
        if(localStorage.getItem("role") == "PACIENT"){
          window.location.href = "/pacient/home"
        }else{
          this._service.logoutUser()
          window.location.href = "/dashboard"

        }
        console.log("dupa token")
      }
     
    })

  }
  openForgotPassDialog(){
    
    this.dialog.open(ForgotPassComponent,{
     width: '30%',
     panelClass: 'my-panel'
    });
  }
}
