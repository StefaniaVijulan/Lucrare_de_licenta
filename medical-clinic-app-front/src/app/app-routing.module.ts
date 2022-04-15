import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';


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
    path:"login",
    component: LoginComponent
  },
  {
    path:"dashboard",
    component: DashboardComponent
  }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
 
}
