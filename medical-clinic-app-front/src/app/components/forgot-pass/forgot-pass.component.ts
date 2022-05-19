import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-forgot-pass',
  templateUrl: './forgot-pass.component.html',
  styleUrls: ['./forgot-pass.component.scss']
})
export class ForgotPassComponent implements OnInit {

  formPass: FormGroup;
  constructor(private _service: AuthService,
    private formBuilder: FormBuilder,
    private dialogref: MatDialogRef < ForgotPassComponent >) { }
    msg=""
loading=false;
  ngOnInit() {
    this.formPass = this.formBuilder.group({
      cnp: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
    })
  }
  forgotPass(){
    console.log("intra in forgot")
    console.log(this.formPass.value.cnp)
    this.loading = true
 this._service.forgotPass(this.formPass.value.cnp).subscribe((res)=>{
  

    console.log("this.loa", this.loading)
      if(res == null){
        console.log(res)
        this.loading=false
        this.msg = "Acest cont nu exista"
      }
      else{
      this.loading=false
      console.log(res)
      this.dialogref.close("change");}
    }
 ) };
}
