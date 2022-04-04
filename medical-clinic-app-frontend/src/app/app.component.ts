import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, ViewChild } from '@angular/core';
import { MatDrawer, MatSidenav } from '@angular/material';
import { User } from './interfaces/User';
import { DoctorDashboardComponent } from './pages/doctor-dashboard/doctor-dashboard.component';
import { LoginDoctorComponent } from './pages/login-doctor/login-doctor.component';
import { AuthService } from './services/auth.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild('drawer', { static: true }) public drawer: MatDrawer
  showFiller = false;
  constructor(public _service: AuthService, private observer: BreakpointObserver){

  }

 
 }
