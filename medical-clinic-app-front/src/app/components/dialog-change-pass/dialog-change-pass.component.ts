import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-dialog-change-pass',
  templateUrl: './dialog-change-pass.component.html',
  styleUrls: ['./dialog-change-pass.component.scss']
})
export class DialogChangePassComponent implements OnInit {
  hide = true;
  hideNew = true;
  oldPass: string;
  newPass: string;
  constructor(private _service: AuthService,
    private formBuilder: FormBuilder,
    private dialogref: MatDialogRef < DialogChangePassComponent >) { }


  ngOnInit() {
  }
  changePass(){
    console.log(this.oldPass)
    console.log(this.newPass)
 this._service.changePass(this.oldPass, this.newPass).subscribe({
    
    next: (res) => {
      console.log("intra in next")
      console.log(this.oldPass)
      console.log(this.newPass)
      this.dialogref.close("change");
    },
    error:(error)=>{
      console.log(error)
    
    }
  })
}
}
