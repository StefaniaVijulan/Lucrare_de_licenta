import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']
})
export class ModeratorComponent implements OnInit {
  usersList: User[] = null;
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders= {
    headers: new HttpHeaders({'content-type':'application/json'})
  };
  constructor(public _moderator: ModeratorService, private _http: HttpClient, public _service: AuthService) { }

  ngOnInit() {
  
  }

  allUsers(){
    console.log(this._moderator.getAllUsers())
    return this._moderator.getAllUsers().subscribe((response: any) => {
      console.log("hello")
      this.usersList = response
      console.log(response)
     
    })};
  getModeratorRol(){
     
      if(this._service.getRole()=="MODERATOR")
        return true
      else
        return false
    };
    displayedColumns: string[] = ['cnp', 'firstName', 'numberUser', 'emailUser','role'];
    dataSource = this.usersList;
}
