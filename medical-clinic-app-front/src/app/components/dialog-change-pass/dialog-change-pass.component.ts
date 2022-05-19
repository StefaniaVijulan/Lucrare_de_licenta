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
      if(res){
      this.loading = true;
      setTimeout(() => this.loading = false, 2000);
        this.dialogref.close("save");
        this.loading = false;
        console.log("intra in next")
      console.log(this.oldPass)
      console.log(this.newPass)
      this.dialogref.close("change");
    }
    },
    error:(error)=>{
      console.log(error)
    
    }
  })
}
}
