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
  loading: boolean=false;
  msg: string;
  constructor(private _service: AuthService,
    private formBuilder: FormBuilder,
    private dialogref: MatDialogRef < DialogChangePassComponent >) { }


  ngOnInit() {
  }
  changePass(){
    this.loading = true;
    console.log(this.oldPass)
    console.log(this.newPass)
    this._service.changePass(this.oldPass, this.newPass).subscribe({
    
    next: (res) => {
      this.loading = false;
      if(res){
        this.dialogref.close("save");
    }else{
      this.msg = "Parola curentă nu corespunde!"
    }
    },
    error:(error)=>{
      console.log(error)
    
    }
  })
}
}
