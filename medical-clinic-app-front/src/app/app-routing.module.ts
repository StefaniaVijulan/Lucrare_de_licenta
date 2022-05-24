import { ChangeDetectorRef, NgModule } from '@angular/core';
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
import { AppointmentsComponent } from './pages/moderator/appointments/appointments.component';
import { ModeratorCardiologComponent } from './pages/moderator/category/moderator-cardiolog/moderator-cardiolog.component';
import { ModeratorHematologComponent } from './pages/moderator/category/moderator-hematolog/moderator-hematolog.component';
import { ModeratorImagistComponent } from './pages/moderator/category/moderator-imagist/moderator-imagist.component';
import { ModeratorSecretarComponent } from './pages/moderator/moderator-secretar/moderator-secretar.component';
import { ModeratorComponent } from './pages/moderator/category/moderator/moderator.component';
import { PacientComponent } from './pages/pacient/pacient/pacient.component';
import { SecretarPacientiComponent } from './pages/secretar/secretar-pacienti/secretar-pacienti.component';
import { SecretarComponent } from './pages/secretar/secretar/secretar.component';
import { UploadImgComponent } from './pages/upload-img/upload-img.component';
import { AppointmentsHematologyComponent } from './pages/moderator/appointments-hematology/appointments-hematology.component';
import { AppointmentsRadiologyComponent } from './pages/moderator/appointments-radiology/appointments-radiology.component';
import { SecretarAppointmentComponent } from './pages/secretar/secretar-appointment/secretar-appointment.component';
import { DoctorFutureAppointmentsComponent } from './pages/doctor/doctor-future-appointments/doctor-future-appointments.component';
import { DoctorAllAppointmentsComponent } from './pages/doctor/doctor-all-appointments/doctor-all-appointments.component';
import { HematologResultComponent } from './pages/hematolog/hematolog-result/hematolog-result.component';
import { HematologAllResultComponent } from './pages/hematolog/hematolog-all-result/hematolog-all-result.component';
import { ImagistGuard } from './guard/imagist/imagist.guard';
import { ImagistComponent } from './pages/imagist/imagist/imagist.component';
import { ImagistResultComponent } from './pages/imagist/imagist-result/imagist-result.component';

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
    path:"moderator/appointments",
    component: AppointmentsComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderator/appointmentsHematology",
    component: AppointmentsHematologyComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"moderator/appointmentsRadiology",
    component: AppointmentsRadiologyComponent,
    canActivate : [AuthGuard, ModeratorGuard]
  },
  {
    path:"secretar",
    component: SecretarComponent,
    canActivate : [AuthGuard, SecretarGuard]
  },
  {
    path:"secretar/appointment/next",
    component: SecretarPacientiComponent,
    canActivate : [AuthGuard, SecretarGuard]
  },
  {
    path:"secretar/appointments/all",
    component: SecretarAppointmentComponent,
    canActivate : [AuthGuard, SecretarGuard]
  },
  {
    path:"doctor/consultatii",
    component: DoctorProgramariComponent,
    canActivate : [AuthGuard, DoctorGuard]
  },
  {
    path:"doctor/programari/viitoare",
    component: DoctorFutureAppointmentsComponent,
    canActivate : [AuthGuard, DoctorGuard]
  },
  {
    path:"doctor/programari/all",
    component: DoctorAllAppointmentsComponent,
    canActivate : [AuthGuard, DoctorGuard]
  },
  {
    path:"hematolog/appointments",
    component: HematologComponent,
    canActivate : [AuthGuard, HematologGuard]
  },
  {
    path:"hematolog/result/add",
    component: HematologResultComponent,
    canActivate : [AuthGuard, HematologGuard]
  },
  {
    path:"hematolog/result/all",
    component: HematologAllResultComponent,
    canActivate : [AuthGuard, HematologGuard]
  },
  {
    path:"imagist/appointments",
    component: ImagistComponent,
    canActivate : [AuthGuard, ImagistGuard]
  },
  {
    path:"imagist/result/add",
    component: ImagistResultComponent,
    canActivate : [AuthGuard, ImagistGuard]
  },
  {
    path:"pacient/home",
    component: PacientComponent,
  },
   {
    path:"upload",
    component: UploadImgComponent,
  },

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
 
}
