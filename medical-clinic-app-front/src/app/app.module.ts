import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AppRoutingModule } from './app-routing.module';
import { MatRadioModule } from '@angular/material';
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
import { ModeratorComponent } from './pages/moderator/moderator.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { DialogAddUserComponent } from './components/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from './components/dialog-delete-user/dialog-delete-user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent,
    ModeratorComponent,
    DialogComponent,
    DialogAddUserComponent,
    DialogDeleteUserComponent
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
    MatCheckboxModule
  ],
  entryComponents: [DialogComponent, DialogAddUserComponent, DialogDeleteUserComponent],
  providers: [],

  bootstrap: [AppComponent]
})
export class AppModule { }
