import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
