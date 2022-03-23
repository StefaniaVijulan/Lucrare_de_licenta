import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth/auth.guard';
import { RoleGuard } from './guards/role/role.guard';


import { DoctorDashboardComponent } from './pages/doctor-dashboard/doctor-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginDoctorComponent } from './pages/login-doctor/login-doctor.component';
import { LoginPacientComponent } from './pages/login-pacient/login-pacient.component';

const routes: Routes = [ 
  {
    path:"home",
    component: HomeComponent
  },
  {
    path:"",
   // redirectTo:"/home",
    component: HomeComponent
  },
  {
    path:"login-doctor",
    component:LoginDoctorComponent
  },
  {
    path: "login-pacient",
    component: LoginPacientComponent
  },
  {
    path: "doctor-dashboard",
    component: DoctorDashboardComponent,
    canActivate : [AuthGuard]
  },
  {
    path: "deleteDoctor",
    component: DoctorDashboardComponent,
    canActivate : [AuthGuard, RoleGuard]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
