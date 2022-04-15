import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']
})
export class ModeratorComponent implements OnInit {
  usersList: any = null;
  constructor(public _moderator: ModeratorService, private _http: HttpClient, public _service: AuthService) { }

  ngOnInit() {
  }
  allUser(){
    return this._moderator.getAllUsers().subscribe((response: any) => {
      console.log("hello")
      this.usersList = response
      console.log(response)
    })}

}
