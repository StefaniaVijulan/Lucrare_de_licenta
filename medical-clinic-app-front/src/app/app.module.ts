import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AppRoutingModule } from './app-routing.module';
import { MatNativeDateModule, MatRadioModule, MatStepperModule } from '@angular/material';
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
import { HttpClientModule} from '@angular/common/http';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { DialogComponent } from './components/moderator/dialog/dialog.component';
import { DialogAddUserComponent } from './components/moderator/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from './components/moderator/dialog-delete-user/dialog-delete-user.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { ModeratorComponent } from './pages/moderator/category/moderator/moderator.component';
import { ModeratorCardiologComponent } from './pages/moderator/category/moderator-cardiolog/moderator-cardiolog.component';
import { ModeratorSecretarComponent } from './pages/moderator/category/moderator-secretar/moderator-secretar.component';
import { ModeratorImagistComponent } from './pages/moderator/category/moderator-imagist/moderator-imagist.component';
import { ModeratorHematologComponent } from './pages/moderator/category/moderator-hematolog/moderator-hematolog.component';
import { DialogResetPassComponent } from './components/moderator/dialog-reset-pass/dialog-reset-pass.component';
import { SecretarComponent } from './pages/secretar/secretar/secretar.component';
import { SecretarPacientiComponent } from './pages/secretar/secretar-pacienti/secretar-pacienti.component';
import { DialogChangePassComponent } from './components/dialog-change-pass/dialog-change-pass.component';
import { DialogAddPacientComponent } from './components/dialog-add-pacient/dialog-add-pacient.component';
import { FlexModule } from '@angular/flex-layout';
import { DialogMoreInfoPacientComponent } from './components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import {MatListModule} from '@angular/material/list';


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
    
  ],
  imports: [
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
    MatListModule
  ],
  entryComponents: [
    DialogComponent, 
    DialogAddUserComponent,
    DialogDeleteUserComponent,
    DialogResetPassComponent,
     DialogChangePassComponent,
     DialogAddPacientComponent,
     DialogMoreInfoPacientComponent
    ],
    
  providers: [],

  bootstrap: [AppComponent]
})
export class AppModule { }
