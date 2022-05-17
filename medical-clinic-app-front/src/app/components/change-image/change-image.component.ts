import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChangeImg } from 'src/app/interfaces/changeImg';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-change-image',
  templateUrl: './change-image.component.html',
  styleUrls: ['./change-image.component.scss']
})
export class ChangeImageComponent implements OnInit {
form: any ={};
changeImg: ChangeImg;
error: any ={
  messange:"no"
}
success:any ={
  messange:"yes"
}
status = 'Alege o imagine'
  constructor(private _service: AuthService, private router: Router) { }

  ngOnInit() {
  }
  onSubmit(){
  console.log("this.form.avatar =>", this.form.avatar)
   this.changeImg = new ChangeImg(this.form.avatar);
    this._service.changeImg(this.changeImg).subscribe(res=>{
      if(JSON.stringify(res)==JSON.stringify(this.error)){
        this.status ="Va rugam sa incarcati o poza"
      }
      if(JSON.stringify(res)==JSON.stringify(this.success)){
        this.status ="poza a fost schimbata"
        window.location.reload();
      }

    }, error=>{
      alert ('nu a mers modificarea de imagine')
    });
  }
  uploadAvatar($event){
    this.form.avatar = $event;
  }

}
