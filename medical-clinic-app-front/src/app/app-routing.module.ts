import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guard/authentification/auth.guard';
import { DoctorGuard } from './guard/doctor/doctor.guard';
import { HematologGuard } from './guard/hematolog/hematolog.guard';
import { ModeratorGuard } from './guard/moderator/moderator.guard';
import { SecretarGuard } from './guard/secretar/secretar.guard';
import { Hematolog } from './interfaces/hematolog';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DoctorProgramariComponent } from './pages/doctor/doctor-programari/doctor-programari.component';
import { HematologComponent } from './pages/hematolog/hematolog/hematolog.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ModeratorCardiologComponent } from './pages/moderator/category/moderator-cardiolog/moderator-cardiolog.component';
import { ModeratorHematologComponent } from './pages/moderator/category/moderator-hematolog/moderator-hematolog.component';
import { ModeratorImagistComponent } from './pages/moderator/category/moderator-imagist/moderator-imagist.component';
import { ModeratorSecretarComponent } from './pages/moderator/category/moderator-secretar/moderator-secretar.component';
import { ModeratorComponent } from './pages/moderator/category/moderator/moderator.component';
import { PacientComponent } from './pages/pacient/pacient/pacient.component';
import { SecretarPacientiComponent } from './pages/secretar/secretar-pacienti/secretar-pacienti.component';
import { SecretarComponent } from './pages/secretar/secretar/secretar.component';

import { ModeratorService } from './services/moderator/moderator.service';


const routes: Routes = [ 
  {
    path:"",
    component: DashboardComponent
  },
  {
    path:"home",
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
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderatorCardiolog",
    component: ModeratorCardiologComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderatorSecretar",
    component: ModeratorSecretarComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderatorImagist",
    component: ModeratorImagistComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderatorHematolog",
    component: ModeratorHematologComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"secretar",
    component: SecretarComponent,
    canActivate : [AuthGuard, SecretarGuard]
  },
  {
    path:"secretarPacient",
    component: SecretarPacientiComponent,
    canActivate : [AuthGuard, SecretarGuard]
  },
  {
    path:"doctorProgramari",
    component: DoctorProgramariComponent,
    canActivate : [AuthGuard, DoctorGuard]
  },
  {
    path:"hematolog",
    component: HematologComponent,
    canActivate : [AuthGuard, HematologGuard]
  },
  {
    path:"pacient",
    component: PacientComponent,
  }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
 
}
