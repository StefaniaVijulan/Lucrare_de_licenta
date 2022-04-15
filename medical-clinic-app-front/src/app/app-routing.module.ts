import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guard/auth.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ModeratorComponent } from './pages/moderator/moderator.component';
import { ModeratorService } from './services/moderator/moderator.service';


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
  },
  {
    path:"moderator",
    component: ModeratorComponent,
    canActivate : [AuthGuard]
  }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
 
}
