import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {
  
  public myForm!:FormGroup;
  email = new FormControl('', [Validators.required, Validators.email]);
  cnp = new FormControl('', [Validators.required, Validators.pattern('^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')]);
  user = new User()
  msg='';
  constructor(private _service:ModeratorService, private _router: Router, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      cnp:["", Validators.required],
      firstName:["", Validators.required],
      lastName:["", Validators.required],
      emailUser:["",[Validators.required, Validators.email]],
      numberUser:["", Validators.required]
    })
  }

  getErrorCnp(){
    if (this.cnp.hasError('required')) {
      return 'You must enter a value';
    }
    return this.cnp.hasError('cnp') ? 'Not a valid cnp' : '';
  }
  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
  register(data:any){
    if(data == "curant")
    {
      console.log("intra aici")
    
      this._service.addCurant(this.user).subscribe(
        data =>{
          console.log("response recive");
          localStorage.setItem('token', JSON.stringify(data))
          this.msg="Register successful";
          this._router.navigate(['/moderator']);
        },
        error =>{
          console.log("exception occured");
          this.msg = error.error;
        }
        
      )
}
  }
}
