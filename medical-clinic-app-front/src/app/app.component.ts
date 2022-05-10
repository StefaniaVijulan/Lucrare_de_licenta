import { BreakpointObserver } from '@angular/cdk/layout';
import { AfterViewInit, ChangeDetectorRef, Component, HostListener, ViewChild } from '@angular/core';
import { MatDialog, MatSidenav } from '@angular/material';
import { Router } from '@angular/router';
import { DialogChangePassComponent } from './components/dialog-change-pass/dialog-change-pass.component';

import { AuthService } from './services/auth/auth.service';
import { DoctorService } from './services/doctor/doctor.service';
import { ModeratorService } from './services/moderator/moderator.service';
import { SecretarService } from './services/secretar/secretar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit  {
  title = 'medical-clinic-app-front';

  @ViewChild(MatSidenav,  {static: false})
  sidenav!: MatSidenav;
  @HostListener('window:beforeunload')
  unloadHandler(event) {
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('role')
    sessionStorage.removeItem('user')
    
  }
  constructor(
    private dialog: MatDialog, 
    public _moderator:ModeratorService,
    private observer: BreakpointObserver,
    private cdr: ChangeDetectorRef,
    public _service:AuthService,
    public _secretar: SecretarService,
    public _doctor: DoctorService,
    public _router: Router){

  }
  ngAfterViewInit(){
    this.observer.observe(['(max-width: 2500px']).subscribe((res)=>{
      if(res.matches){
        this.sidenav.mode = 'over';
        this.sidenav.close();
        this.cdr.detectChanges();
      }
      else{
        this.sidenav.mode='side';
        this.sidenav.open();
        this.cdr.detectChanges();
      }
    });
  }
  goDashboard(){
    this._router.navigate(['/dashboard'])
  }
  openDialog(){ 
    this.dialog.open(DialogChangePassComponent,{
     width: '30%'
    }).afterClosed().subscribe(val=>{
     if(val === "change"){
       if(localStorage.getItem("role")=="MODERATOR")
        this._router.navigate(['/moderator'])
        else{
          this._router.navigate(['/secretar'])
        }
       
     }
   })

 };

}