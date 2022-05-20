import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';
import { ChangeImg } from 'src/app/interfaces/changeImg';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-photo-change',
  templateUrl: './photo-change.component.html',
  styleUrls: ['./photo-change.component.scss']
})
export class PhotoChangeComponent implements OnInit {
  form: any ={};
  changeImg: ChangeImg;
  error: any ={
    messange:"no"
  }
  success:any ={
    messange:"yes"
  }
  status = 'Alege o imagine'
    constructor(private dialogref: MatDialogRef < PhotoChangeComponent > , private _service: AuthService, private router: Router) { }
  
    ngOnInit() {
    }
    onSubmit(){
    console.log("this.form.avatar =>", this.form.avatar)
    this.changeImg = new ChangeImg(this.form.avatar);
      this._service.changeImg(this.changeImg).subscribe(res=>{
        localStorage.removeItem("image")
          localStorage.setItem("image", this.form.avatar)
          console.log(localStorage.getItem("image"))
      if(JSON.stringify(res)==JSON.stringify(this.error)){
          this.status ="Vă rugăm să încărcați o poză"
        }
        if(JSON.stringify(res)==JSON.stringify(this.success)){

           window.location.reload();

        }
        this.dialogref.close("save");
      }, error=>{
        alert ('nu a mers modificarea de imagine')
      });
    }
    uploadAvatar($event){
      this.form.avatar = $event;
    }
  
  }
  