import { Component, OnInit } from '@angular/core';
import { LoginUserDTO } from 'src/app/interfaces/LoginUserDTO';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {

  constructor(public _service: AuthService) { }
  
  ngOnInit() {
  }

}
