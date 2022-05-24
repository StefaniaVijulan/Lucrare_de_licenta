import { BreakpointObserver } from '@angular/cdk/layout';
import { AfterViewInit, ChangeDetectorRef, Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatSidenav } from '@angular/material';
import { Router } from '@angular/router';
import { loadavg } from 'os';
import { retry } from 'rxjs/operators';
import { DialogChangePassComponent } from './components/dialog-change-pass/dialog-change-pass.component';
import { PhotoChangeComponent } from './components/photo-change/photo-change.component';

import { AuthService } from './services/auth/auth.service';
import { DoctorService } from './services/doctor/doctor.service';
import { HematologService } from './services/hematolog/hematolog.service';
import { ImagistService } from './services/imagist/imagist.service';
import { ModeratorService } from './services/moderator/moderator.service';
import { SecretarService } from './services/secretar/secretar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit, OnInit {
  title = 'medical-clinic-app-front';
  nameItem: any;
  roleItem: any;
  @ViewChild(MatSidenav,  {static: false})
  sidenav!: MatSidenav;
  imageItem: any;
  loading = false;
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
    public _hematolog: HematologService,
    public _doctor: DoctorService,
    public _imagist: ImagistService,
    public _router: Router){

  }
  ngOnInit(): void {
    this.nume()
    this.role()
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
        this._router.navigate(['/moderator/appointments'])
        else if(localStorage.getItem("role")=="SECRETAR"){
          this._router.navigate(['/secretar'])
        }else if(localStorage.getItem("role")=="CARDIOLOG"){
          this._router.navigate(['/doctor/consultatii'])
        } else if(localStorage.getItem("role")=="HEMATOLOG"){
          this._router.navigate(['/hematolog/appointments'])

        }
       
     }
   })
   

 };
 openChangePhotoDialog(){ 
  this.dialog.open(PhotoChangeComponent,{
   width: '30%'
  }).afterClosed().subscribe(val=>{
    location.reload();
 })
 

};
  deleteImg(){
    this.loading =true;
    console.log("intra aici")
    this._service.deleteImg().subscribe((res)=>{
      this.loading = false;
      console.log("in final aici =>", res)
      localStorage.removeItem("image");
      localStorage.setItem("image",res.imageUser)
      this.imageItem = res.imageUser

    })
  }
  userPhoto(){
    this.imageItem = localStorage.getItem("image")
    return localStorage.getItem("image");
  }
  role(){
    this.roleItem = localStorage.getItem("role")
    return localStorage.getItem("role");
  }
  nume(){
   
    this.nameItem = localStorage.getItem("name")
    
    return localStorage.getItem("nume");
  }
}