import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AppRoutingModule } from './app-routing.module';
import { MatNativeDateModule, MatProgressBar, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatStepperModule } from '@angular/material';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatDialogModule, MatFormFieldModule, MatOptionModule, MatPaginatorModule, MatSelectModule, MatSlideToggleModule, MatSortModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { MatSidenavModule } from '@angular/material';
import { MatIconModule } from '@angular/material';
import { MatDividerModule } from '@angular/material';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { DialogComponent } from './components/moderator/dialog/dialog.component';
import { DialogAddUserComponent } from './components/moderator/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from './components/moderator/dialog-delete-user/dialog-delete-user.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { ModeratorComponent } from './pages/moderator/category/moderator/moderator.component';
import { ModeratorCardiologComponent } from './pages/moderator/category/moderator-cardiolog/moderator-cardiolog.component';
import { ModeratorSecretarComponent } from './pages/moderator/moderator-secretar/moderator-secretar.component';
import { ModeratorImagistComponent } from './pages/moderator/category/moderator-imagist/moderator-imagist.component';
import { ModeratorHematologComponent } from './pages/moderator/category/moderator-hematolog/moderator-hematolog.component';
import { DialogResetPassComponent } from './components/moderator/dialog-reset-pass/dialog-reset-pass.component';
import { SecretarComponent } from './pages/secretar/secretar/secretar.component';
import { SecretarPacientiComponent } from './pages/secretar/secretar-pacienti/secretar-pacienti.component';
import { DialogChangePassComponent } from './components/dialog-change-pass/dialog-change-pass.component';
import { DialogAddPacientComponent } from './components/dialog-add-pacient/dialog-add-pacient.component';
import { FlexLayoutModule, FlexModule } from '@angular/flex-layout';
import { DialogMoreInfoPacientComponent } from './components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import {MatListModule} from '@angular/material/list';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { DialogAddAppointmentComponent } from './components/dialog-add-appointment/dialog-add-appointment.component';
import { DialogAppointmentSuccessComponent } from './components/dialog-appointment-success/dialog-appointment-success.component';
import { DoctorProgramariComponent } from './pages/doctor/doctor-programari/doctor-programari.component';
import { DialogEditFisaComponent } from './components/dialog-edit-fisa/dialog-edit-fisa.component';
import { DialogAddAppointmentsHrComponent } from './components/dialog-add-appointments-hr/dialog-add-appointments-hr.component';
import { DialogAddAppointmentsHematologyComponent } from './components/dialog-add-appointments-hematology/dialog-add-appointments-hematology.component';
import { DialogAddAppointmentsRadiologyComponent } from './components/dialog-add-appointments-radiology/dialog-add-appointments-radiology.component';
import { HematologComponent } from './pages/hematolog/hematolog/hematolog.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { PacientComponent } from './pages/pacient/pacient/pacient.component';
import { AngularFireModule } from '@angular/fire';
import { AngularFireStorageModule } from '@angular/fire/storage';
import 'firebase/storage';
import { environment } from 'src/environments/environment.prod';
import { UploadImgComponent } from './pages/upload-img/upload-img.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ForgotPassComponent } from './components/forgot-pass/forgot-pass.component';
import { AppointmentsComponent } from './pages/moderator/appointments/appointments.component';
import { PhotoChangeComponent } from './components/photo-change/photo-change.component';
import { DialogCancelDeleteUserComponent } from './components/dialog-cancel-delete-user/dialog-cancel-delete-user.component';
import { AppointmentsHematologyComponent } from './pages/moderator/appointments-hematology/appointments-hematology.component';
import { AppointmentsRadiologyComponent } from './pages/moderator/appointments-radiology/appointments-radiology.component';
import { DialogEditAppointmentComponent } from './components/dialog-edit-appointment/dialog-edit-appointment.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent,
    ModeratorComponent,
    DialogComponent,
    DialogAddUserComponent,
    DialogDeleteUserComponent,
    ModeratorCardiologComponent,
    ModeratorSecretarComponent,
    ModeratorImagistComponent,
    ModeratorHematologComponent,
    DialogResetPassComponent,
    SecretarComponent,
    SecretarPacientiComponent,
    DialogChangePassComponent,
    DialogAddPacientComponent,
    DialogMoreInfoPacientComponent,
    DialogAddAppointmentComponent,
    DialogAppointmentSuccessComponent,
    DoctorProgramariComponent,
    DialogEditFisaComponent,
    DialogAddAppointmentsHrComponent,
    DialogAddAppointmentsHematologyComponent,
    DialogAddAppointmentsRadiologyComponent,
    HematologComponent,
    PacientComponent,
    UploadImgComponent,

    ForgotPassComponent,
    MatProgressBar,
    AppointmentsComponent,
    PhotoChangeComponent,
    DialogCancelDeleteUserComponent,
    AppointmentsHematologyComponent,
    AppointmentsRadiologyComponent,
    DialogEditAppointmentComponent,

  
  ],
  imports: [
    FlexLayoutModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatDividerModule,
    FormsModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatTableModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatDialogModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatSortModule,
    MatRadioModule,
    MatCheckboxModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatStepperModule,
    MatListModule,
    MatAutocompleteModule,
    MatExpansionModule,
    AngularFireStorageModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    MatProgressSpinnerModule,
    MatTooltipModule
  ],
  entryComponents: [
    DialogComponent, 
    DialogAddUserComponent,
    DialogDeleteUserComponent,
    DialogResetPassComponent,
     DialogChangePassComponent,
     DialogAddPacientComponent,
     DialogMoreInfoPacientComponent,
     DialogAddAppointmentComponent,
     DialogAppointmentSuccessComponent,
     DialogEditFisaComponent,
     DialogAddAppointmentsHrComponent,
     DialogAddAppointmentsHematologyComponent,
     DialogAddAppointmentsRadiologyComponent,
     ForgotPassComponent,
     PhotoChangeComponent,
     DialogCancelDeleteUserComponent,
     DialogEditAppointmentComponent
    ],
    
  providers:[],

  bootstrap: [AppComponent]
})
export class AppModule { }
