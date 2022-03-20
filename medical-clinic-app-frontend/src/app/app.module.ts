import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginPacientComponent } from './pages/login-pacient/login-pacient.component';
import { LoginDoctorComponent } from './pages/login-doctor/login-doctor.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { DoctorDashboardComponent } from './pages/doctor-dashboard/doctor-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginPacientComponent,
    LoginDoctorComponent,
    DoctorDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
